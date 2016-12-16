package fr.unice.polytech.si3.miaou.brainfuck.codegeneration;

import java.util.Map;
import java.util.HashMap;

import fr.unice.polytech.si3.miaou.brainfuck.instructions.Instruction;

/**
 * Translates a brainfuck program in Python.
 *
 * @author Guillaume Casagrande
 */
class PythonLanguage extends Language {
	/**
	 * Counts the number of tabulations to add before the writing of an instruction.
	 */
	private int spaces;

	/**
	 * Constructs a PythonLanguage object and fills the map of instructions.
	 */
	PythonLanguage() {
		super();
		extension = "py";
		name = "python";
		spaces = 0;

		instructionsTranslation.put(']', "");
		instructionsTranslation.put('-', "memory[i] -= 1");
		instructionsTranslation.put(',', "memory[i] = ord(input())");
		instructionsTranslation.put('+', "memory[i] += 1");
		instructionsTranslation.put('[', "while not memory[i] == 0:");
		instructionsTranslation.put('<', "i -= 1");
		instructionsTranslation.put('.', "print(chr(memory[i]), end=\"\")");
		instructionsTranslation.put('>', "i += 1");
	}

	@Override
	String translateInstruction(Instruction instr) {
		sb = new StringBuilder();
		for (int i = spaces; i > 0; i--) {
			sb.append("    ");
		}
		if ('[' == (instr.getSymbol())) { spaces++; }
		else if (']' == (instr.getSymbol())) { spaces--; }

		sb.append(instructionsTranslation.get(instr.getSymbol()));
		return sb.toString();
	}

	@Override
	String buildFront() {
		sb = new StringBuilder();
		sb.append("#!/usr/bin/python\n");
		sb.append("#coding: latin-1\n\n");
		sb.append("size_memory = 30000\n");
		sb.append("memory = [0] * size_memory\n");
		sb.append("i = 0\n");
		return sb.toString();
	}

	@Override
	String buildFooter() {
		sb = new StringBuilder();
		sb.append("\nfor j in range(0, size_memory):\n");
		sb.append("    if memory[j] != 0:\n");
		sb.append("        print(\"C\", j,\": \", memory[j], sep=\"\")\n");

		return sb.toString();
	}
}
