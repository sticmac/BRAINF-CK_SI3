package fr.unice.polytech.si3.miaou.brainfuck;

import java.util.Map;
import java.util.HashMap;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.si3.miaou.brainfuck.exceptions.SyntaxMacroException;

/**
 * Parsing <code>Macro</code>s (while creating them and using them).
 *
 * @author Pierre-Emmanuel Novac
 * @author Julien Lemaire
 */
public class MacroParser {
	/**
	 * Different states of the <code>Macro</code> parsing process.
	 *
	 * @author Pierre-Emmanuel Novac
	 */
	private enum State {
		/**
		 * Not defining a <code>Macro</code>.
		 */
		NO_MACRO,
		/**
		 * Next line defines the name of the <code>Macro</code>.
		 */
		MACRO_NAME,
		/**
		 * Next lines define the content of the <code>Macro</code>.
		 */
		MACRO_BODY
	}

	/**
	 * Defining the content of a <code>Macro</code>.
	 *
	 * @author Pierre-Emmanuel Novac
	 */
	private class Macro {
		/**
		 * Contains <code>Macro</code>'s content.
		 */
		private List<String> body;

		/**
		 * Main constructor of class <code>Macro</code>.
		 */
		Macro() {
			body = new ArrayList<>();
		}

		/**
		 * Adds content to the <code>Macro</code>'s body.
		 *
		 * @param line Content to add to <code>Macro</code>'s body.
		 */
		void addToBody(String line) {
			body.add(line);
		}

		/**
		 * Returns the <code>Macro</code>'s body as a stream.
		 *
		 * @return the <code>Macro</code>'s body as a stream.
		 */
		Stream<String> getBody() {
			return body.stream();
		}
	}

	/**
	 * Map containing the different <code>Macro</code>s linked to their names.
	 */
	private Map<String, Macro> macros;
	/**
	 * Original content of the program, with <code>Macro</code> syntax.
	 */
	private Stream<String> prog;
	/**
	 * Current state of the finite state machine.
	 */
	private State state = State.NO_MACRO;

	/**
	 * Stores the name of the <code>Macro</code> being defined.
	 */
	private String macroName;
	/**
	 * Content of the <code>Macro</code> being defined.
	 */
	private Macro macro;

	/**
	 * Main constructor of <code>MacroParser</code>.
	 *
	 * @param stream stream of lines containing instructions symbols and keywords to parse.
	 */
	public MacroParser(Stream<String> stream) {
		this.macros = new HashMap<>();
		this.prog = stream;
	}

	/**
	 * Expand the macro if the line contains a macro call, otherwise returns the line itself.
	 *
	 * @param line	line which may contain a macro call to expand.
	 * @return a stream containing the expanded macro if there was a macro call or the original line.
	 */
	private Stream<String> parseLine(String line) {
		String[] params = line.split(" ");

		if (macros.containsKey(params[0])) { // We've got a macro right there, try to replace it. Naive check so you can be evil and declare a "+" macro.
			return writeMacroBody(params);
		} else {
			return Stream.of(line);
		}
	}

	/**
	 * Returns the expanded macro recursively with the given params.
	 * params[0] is the macro name, params[1] is the number of repetition.
	 *
	 * @param params	parameters for macro expansion: params[0] is the macro name, params[1] is the repetition count
	 * @return macro recursively expanded (optionally multiple times)
	 */
	private Stream<String> writeMacroBody(String[] params) {
		String name = params[0];
		int repeat = 1;
		if (params.length > 1) repeat = Integer.parseInt(params[1]); // convert the second param to the repetition count as an int

		Macro macro = macros.get(name);
		return IntStream.range(0, repeat) // generate a Stream<Integer> of repeat integers
			.mapToObj(i -> // map it to the String<Stream> macro body
				macro.getBody() // fetch the Stream<String> macro body
				.flatMap(this::parseLine) // recursive expand the macro content using parseLine which returns a Stream<String> for each of the macro line and flatten it to a Stream<String>
			).flatMap(t->t); // flatten the Stream of repeated macro bodies from Stream<Stream<String>> to Stream<String> using identity
	}

	/**
	 * Parses the stream given in constructor and returns it without <code>Macro</code>s definitions and usage.
	 *
	 * @return The stream of lines given in constructor without <code>Macro</code>s definitions and usage.
	 */
	public Stream<String> parse() {
		return prog.flatMap(line -> {
			State prev_state = state;

			if ("DEFINE".equals(line)) {
				if (state == State.NO_MACRO) {
					state = State.MACRO_NAME;
					macro = new Macro();
					return Stream.empty();
				} else {
					throw new SyntaxMacroException("Defining a Macro while not finishing defining a previous one.");
				}
			} else if ("AS".equals(line)) {
				if (state == State.MACRO_NAME) {
					state = State.MACRO_BODY;
					return Stream.empty();
				} else {
					throw new SyntaxMacroException("AS without a previous DEFINE.");
				}
			} else if ("END".equals(line)) {
				if (state == State.MACRO_BODY) {
					state = State.NO_MACRO;
					macros.put(macroName, macro);
					return Stream.empty();
				} else {
					throw new SyntaxMacroException("END without a previous AS.");
				}
			}

			if (state == State.MACRO_NAME) macroName = line;

			if (state == State.MACRO_BODY) macro.addToBody(line);

			if (state == State.NO_MACRO) return parseLine(line);
			return Stream.empty();
		});
	}
}
