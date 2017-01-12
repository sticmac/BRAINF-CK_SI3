package fr.unice.polytech.si3.miaou.brainfuck;

import java.util.List;
import java.util.ArrayList;

import fr.unice.polytech.si3.miaou.brainfuck.instructions.Instruction;

/**
 * Translate an instruction in an other form.
 *
 * @author Guillaume Casagrande
 * @see Instruction
 */
public class Translator {
	/**
	 * Write the instruction with the long syntax.
	 *
	 * @param instr	instruction to translate.
	 */
	public void toLongSyntax(Instruction instr) {
		System.out.println(instr.getName());
	}

	/**
	 * Write the instructions with the long syntax.
	 *
	 * @param instructions list of instructions to translate.
	 */
	public void toLongSyntax(List<Instruction> instructions) {
		for (int i = 0; i < instructions.size(); i++) {
			toLongSyntax(instructions.get(i));
		}
	}

	/**
	 * Write the instruction in the short syntax.
	 *
	 * @param instr instruction to translate.
	 */
	public void toShortSyntax(Instruction instr) {
		System.out.print(instr.getSymbol());
	}

	/**
	 * Write the instructions in the short syntax.
	 *
	 * @param instructions list of instructions to translate.
	 */
	public void toShortSyntax(List<Instruction> instructions) {
		for (int i = 0; i < instructions.size(); i++) {
			toShortSyntax(instructions.get(i));
		}
	}

	/**
	 * Write the instruction in a sequence of hexadecimal numbers.
	 *
	 * @param instr instruction to translate.
	 * @return instruction's color code.
	 */
	public int toColor(Instruction instr) {
		return instr.getColor();
	}

	/**
	 * Write the instructions in a sequence of hexadecimal numbers.
	 *
	 * @param instructions list of instructions to translate.
	 * @return list of instruction's color code.
	 */
	public List<Integer> toColor(List<Instruction> instructions) {
		List<Integer> colors = new ArrayList<>();
		for (int i = 0; i < instructions.size(); i++) {
			colors.add(toColor(instructions.get(i)));
		}
		return colors;
	}
}
