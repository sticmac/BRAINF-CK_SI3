package fr.unice.polytech.si3.miaou.brainfuck.codegeneration;

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
		instructionsTranslation.put(',', "memory[i] = ord(finput.read(1))");
		instructionsTranslation.put('+', "memory[i] += 1");
		instructionsTranslation.put('[', "while not memory[i] == 0:");
		instructionsTranslation.put('<', "i -= 1");
		instructionsTranslation.put('.', "foutput.write(chr(memory[i]))");
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
		sb.append("#!/usr/bin/env python\n");
		sb.append("#coding: latin-1\n\n");
		sb.append("import os\n");
		sb.append("import sys\n\n");
		sb.append("size_memory = 30000\n");
		sb.append("memory = [0] * size_memory\n");
		sb.append("i = 0\n");
		return sb.toString();
	}

	@Override
	String io(String in, String out) {
		sb = new StringBuilder();
		if ("System.in".equals(in)) {
			sb.append("finput = sys.stdin\n");
		}
		else {
			sb.append("finput = open(\"").append(in).append("\", \"rb\")\n");
		}
		if ("System.out".equals(out)) {
			sb.append("foutput = sys.stdout\n");
		}
		else {
			sb.append("foutput = open(\"").append(out).append("\", \"wb\")\n");
		}
		return sb.toString();
	}

	@Override
	String buildFooter() {
		sb = new StringBuilder();
		sb.append("\nfor j in range(0, size_memory):\n");
		sb.append("    if memory[j] != 0:\n");
		sb.append("        string = \"\\nC\"+str(j)+\": \"+str(memory[j])\n");
		sb.append("        foutput.write(string)\n");
		sb.append("foutput.write(\"\\n\")");

		return sb.toString();
	}
}
