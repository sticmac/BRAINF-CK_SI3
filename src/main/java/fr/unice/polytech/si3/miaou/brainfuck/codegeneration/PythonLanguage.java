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

		instructionsTranslation.put(iset.getOp("BACK"), "");
		instructionsTranslation.put(iset.getOp("DECR"), "memory[i] -= 1");
		instructionsTranslation.put(iset.getOp("IN"), "memory[i] = ord(finput.read(1))");
		instructionsTranslation.put(iset.getOp("INCR"), "memory[i] += 1");
		instructionsTranslation.put(iset.getOp("JUMP"), "while not memory[i] == 0:");
		instructionsTranslation.put(iset.getOp("LEFT"), "i -= 1");
		instructionsTranslation.put(iset.getOp("OUT"), "foutput.write(chr(memory[i]))");
		instructionsTranslation.put(iset.getOp("RIGHT"), "i += 1");
		instructionsTranslation.put(iset.getOp("RET"), "");
	}

	@Override
	String translateInstruction(Instruction instr) {
		sb = new StringBuilder();
		for (int i = spaces; i > 0; i--) {
			sb.append("    ");
		}
		if ("JUMP".equals(instr.getName())) { spaces++; }
		else if ("BACK".equals(instr.getName()) || "RET".equals(instr.getName())) { spaces--; }

		if (counter == 42) {
			sb.append(createProcedure("nameProc")).append("\n    ");
			spaces++;
		}
		counter++;

		sb.append(instructionsTranslation.get(instr.getName()));
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

	@Override
	String createProcedure(String name) {
		instructionsTranslation.put(iset.getProc(name), name+"()");
		return "def "+name+"():";
	}
}
