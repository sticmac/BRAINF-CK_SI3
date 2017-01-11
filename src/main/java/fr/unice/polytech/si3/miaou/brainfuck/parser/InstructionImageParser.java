package fr.unice.polytech.si3.miaou.brainfuck.parser;

import fr.unice.polytech.si3.miaou.brainfuck.InstructionSet;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.InvalidInstructionException;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Instruction;

import java.util.function.IntConsumer;

/**
 * Parsing instructions from text.
 * Inherits of java IntConsumer's interface.
 *
 * @author Julien Lemaire
 */
class InstructionImageParser implements IntConsumer {
	private InstructionParser ip;
	private InstructionSet iset;

	public InstructionImageParser(InstructionParser ip, InstructionSet iset) {
		this.ip = ip;
		this.iset = iset;
	}

	@Override
	public void accept(int colour) {
		if (colour != 0xFF000000) { // Skip black
			Instruction instr = iset.getOp(colour);
			if (instr != null) {
				ip.addInstruction(instr);
			} else {
				throw new InvalidInstructionException(colour);
			}
		}
	}
}
