package fr.unice.polytech.si3.miaou.brainfuck.parser;

import fr.unice.polytech.si3.miaou.brainfuck.InstructionSet;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.InvalidInstructionException;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Instruction;

import java.util.function.Consumer;

/**
 * Parsing instructions from text.
 * Inherits of java Consumer's interface.
 *
 * @author Julien Lemaire
 */
class InstructionTextParser implements Consumer<String> {
	private InstructionParser ip;
	private InstructionSet iset;

	public InstructionTextParser(InstructionParser ip, InstructionSet iset) {
		this.ip = ip;
		this.iset = iset;
	}

	@Override
	public void accept(String line) {
		Instruction instr = iset.getOp(line); // Tries to parse the whole line (ie. long format)

		if (instr == null) { // Maybe it is a procedure call?
			String[] split = line.split(" ");
			instr = iset.getProc(split[0]);
		}

		if (instr != null) {
			ip.addInstruction(instr);
		} else {
			for (int i = 0; i < line.length(); i++) { // Tries to executes the instructions with the short format
				char c = line.charAt(i);
				if (c == ' ' || c == '\t')
					continue;

				instr = iset.getOp(c);

				if (instr == null)
					throw new InvalidInstructionException(c);
				ip.addInstruction(instr);
			}
		}
	}
}
