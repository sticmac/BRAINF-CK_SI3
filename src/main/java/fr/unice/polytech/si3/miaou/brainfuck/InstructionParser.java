package fr.unice.polytech.si3.miaou.brainfuck;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.IntStream;

import fr.unice.polytech.si3.miaou.brainfuck.instructions.Instruction;
import fr.unice.polytech.si3.miaou.brainfuck.InstructionSet;
import fr.unice.polytech.si3.miaou.brainfuck.JumpTable;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.InvalidInstructionException;

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
	 * Jumptable used to bind Jump and Back instructions
	 */
	private JumpTable jumptable;

	/**
	 * Constructs an InsturctionParser with an empty list of instructions and creates the InstructionSet object.
	 */
	private InstructionParser() {
		instructions = new ArrayList<Instruction>();
		iset = new InstructionSet();
		jumptable = new JumpTable();
	}

	/**
	 * Constructs an InstructionParser with the given stream of lines and parses it to generate the list of instructions.
	 * Lines can contain either multiple instruction's symbol for short syntax or a single instruction's keyword for long syntax.
	 *
	 * @param stream	stream of lines containing instructions symbols and keywords to parse.
	 * @throws InvalidInstructionException  when an invalid instruction is encountered.
	 */
	public InstructionParser(Stream<String> stream) {
		this();

		stream = (new MacroParser(stream)).parse();

		stream.forEachOrdered(line -> {
			if (line.startsWith("#")) return;

			int posCom = line.indexOf("#");

			if (posCom != -1) {
				line = line.substring(0, posCom);
			}

			line = line.trim();

			if (line.isEmpty()) return;

			Instruction instr = iset.getOp(line); // Tries to parse the whole line (ie. long format)

			if (instr != null) {
				instructions.add(instr);
				jumptable.bind(instr, instructions.size());
			} else {
				for (int i = 0; i < line.length(); i++) { // Tries to executes the instructions with the short format
					char c = line.charAt(i);
					if (c == ' ' || c == '\t') continue;

					instr = iset.getOp(c);
					if (instr == null) 	throw new InvalidInstructionException(c);
					instructions.add(instr);
					jumptable.bind(instr, instructions.size());
				}
			}
			//jumptable.showTable();
		});
	}

	/**
	 * Constructs an InstructionParser with the given int stream of color codes and parses it to generate the list of instructions.
	 *
	 * @param stream	stream of color codes as integers in the ARVB 32bpp format.
	 * @throws InvalidInstructionException	when an invalid instruction is encountered.
	 */
	public InstructionParser(IntStream stream) {
		this();
		stream.forEachOrdered(colour -> {
			if (colour != 0xFF000000) { // Skip black
				Instruction instr = iset.getOp(colour);
				if (instr != null) instructions.add(instr);
				else {
					throw new InvalidInstructionException(colour);
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
	 * Return the jumptable associated to the instructions list
	 *
	 * @return the jumptable
	 */
	public JumpTable getJumpTable() {
		return this.jumptable;
	}
}
