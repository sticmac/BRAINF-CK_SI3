package fr.unice.polytech.si3.miaou.brainfuck.parser;

import fr.unice.polytech.si3.miaou.brainfuck.InstructionSet;
import fr.unice.polytech.si3.miaou.brainfuck.JumpTable;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.InvalidInstructionException;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Instruction;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Procedure;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Parsing instructions from text.
 * Inherits of java Consumer's interface.
 *
 * @author Julien Lemaire
 */
class InstructionTextParser implements Consumer<String> {
	private List<Instruction> instructions;
	private JumpTable jumptable;
	private InstructionSet iset;

	public InstructionTextParser(List<Instruction> instructions, JumpTable jumptable, InstructionSet iset) {
		this.instructions = instructions;
		this.jumptable = jumptable;
		this.iset = iset;
	}

	@Override
	public void accept(String line) {
		Instruction instr = iset.getOp(line); // Tries to parse the whole line (ie. long format)
		String[] split = line.split(" ");

		if (instr != null) {
			instructions.add(instr);
			jumptable.bind(instr, instructions.size());
		} else if (iset.getProc(split[0]) != null) {
			Procedure proc = iset.getProc(split[0]);
			instructions.add(proc);
		} else {
			for (int i = 0; i < line.length(); i++) { // Tries to executes the instructions with the short format
				char c = line.charAt(i);
				if (c == ' ' || c == '\t') continue;

				instr = iset.getOp(c);

				if (instr == null) throw new InvalidInstructionException(c);
				instructions.add(instr);
				jumptable.bind(instr, instructions.size());
			}
		}
	}
}
