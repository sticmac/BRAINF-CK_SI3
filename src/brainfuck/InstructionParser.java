package brainfuck;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.IntStream;

import brainfuck.Instruction;
import brainfuck.InstructionSet;

/**
 * Parses the instruction from either a stream of String for the two text representations or from a stream of int (color code) for images
 *
 * @author Pierre-Emmanuel Novac
 */
public class InstructionParser {
	/**
	 * Generated list of instructions after parsing.
	 */
	private List<Instruction> instructions;

	/**
	 * Instruction set for recognizing the supported instructions.
	 */
	private InstructionSet iset;

	/**
	 * Constructs an InsturctionParser with an empty list of instructions and creates the InstructionSet object.
	 */
	private InstructionParser() {
		instructions = new ArrayList<Instruction>();
		iset = new InstructionSet();
	}

	/**
	 * Constructs an InstructionParser with the given stream of lines and parses it to generate the list of instructions.
	 * Lines can contain either multiple instruction's symbol for short syntax or a single instruction's keyword for long syntax.
	 * Calls onError() upon encountering an invalid instruction.
	 *
	 * @param stream	stream of lines containing instructions symbols and keywords to parse
	 */
	public InstructionParser(Stream<String> stream) {
		this();

		// Does it need a .filter(l -> !"".equals) ?
		stream.forEachOrdered(line -> {
				Instruction instr = iset.getOp(line); // Tries to parse the whole line (ie. long format)
				if (instr != null) {
					instructions.add(instr);
				} else {
					for (int i = 0; i < line.length(); i++) { // Tries to executes the instructions with the short format
						instr = iset.getOp(line.charAt(i));
						if (instr != null) instructions.add(instr);
						else {
							onError();
						}
					}
			}
		});

	}

	/**
	 * Constructs an InstructionParser with the given int stream of color codes and parses it to generate the list of instructions.
	 * Calls onError() upon encountering an invalid instruction.
	 *
	 * @param stream	stream of color codes as integers in the ARVB 32bpp format.
	 */
	public InstructionParser(IntStream stream) {
		this();
		stream.forEachOrdered(colour -> {
			if (colour != 0xFF000000) { // Skip black
				Instruction instr = iset.getOp(colour);
				if (instr != null) instructions.add(instr);
				else {
					onError();
				}
			}
		});
	}

	/**
	 * Returns the list of instructions generated from the data given when constructing this object.
	 * Should be refactored: breaks encapsulation.
	 *
	 * @return the list of parsed instructions.
	 */
	public List<Instruction> get() {
		return instructions;
	}

	/**
	 * Exits with code 38.
	 * Called when an error is encountered while parsing.
	 * Should be refactored: throw an exception instead.
	 */
	private void onError() {
		System.err.println("Invalid instruction");
		System.exit(38);
	}
}
