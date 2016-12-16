package fr.unice.polytech.si3.miaou.brainfuck.parser;

import fr.unice.polytech.si3.miaou.brainfuck.InstructionSet;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.SyntaxFunctionException;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Procedure;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Parsing Procedure declarations .
 * Implements the function interface Function in order to be used directly in the Stream of line's map().
 *
 * @author Julien Lemaire
 */
class FunctionsParser implements Function<String, Stream<String>> {
	/**
	 * The InstructionSet we're adding procedures to.
	 */
	private InstructionSet iset;

	/**
	 * True if currently defining a function.
	 */
	private boolean defining;

	/**
	 * Name of the currently parsed function.
	 */
	private String name;

	/**
	 * Content of the procedure.
	 */
	private Stream.Builder<String> body;

	/**
	 * Line counter.
	 */
	private int counter;

	/**
	 * Main constructor of FunctionsParser.
	 *
	 * @param iset the InstructionSet the parsed Procedures has to be added to.
	 */
	public FunctionsParser(InstructionSet iset) {
		body = Stream.builder();
		this.iset = iset;
		defining = false;
		name = "";
		counter = 0;
	}

	/**
	 * Parses the given line and deletes every comment and indentation it contains.
	 * Overrides <a href="https://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html">Function</a>'s apply() method.
	 *
	 * @param line the line to parse coming from the calling Stream function.
	 * @return comment and indentation parsing result.
	 */
	@Override
	public Stream<String> apply(String line) {
		if (line.startsWith("FUNC")) {
			if (defining) {
				throw new SyntaxFunctionException("Trying to declare a new function before the end of another one.");
			} else {
				name = line.split(" ")[1];
				body = Stream.builder();
				defining = true;
				return Stream.empty();
			}
		} else if (line.startsWith("RET")) {
			if (!defining) {
				throw new SyntaxFunctionException("Trying to return without declaring a function.");
			} else {
				defining = false;
				body.add(line);
				iset.addProc(new Procedure(name, counter));
				List<String> list = body.build().collect(Collectors.toList());
				counter += list.size()+1;
				return list.stream();
			}
		} else {
			if (defining) {
				body.add(line);
				return Stream.empty();
			} else {
				return Stream.of(line);
			}
		}
	}
}
