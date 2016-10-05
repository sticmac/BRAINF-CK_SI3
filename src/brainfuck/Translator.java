package brainfuck;

import java.util.List;

/**
 * Translate an instruction in an other form.
 *
 * @author MIAOU
 * @see Instruction
 */
public class Translator {
	/**
	 * Write the instruction with the long syntax.
	 *
	 * @param instr	instruction to translate.
	 */
	public void toLongSyntax(Instruction instr) {
		System.out.print(instr.getName());
	}

	/**
	 * Write the instruction with the long syntax.
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
	 * Write the instruction in the short syntax.
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
	 */
	public void toColor(Instruction instr) {
		System.out.print(String.format("%06X", instr.getColor()));
	}

	/**
	 * Write the instruction in a sequence of hexadecimal numbers.
	 *
	 * @param instructions list of instructions to translate.
	 */
	public void toColor(List<Instruction> instructions) {
		for (int i = 0; i < instructions.size(); i++) {
			toColor(instructions.get(i));
		}
	}
}
