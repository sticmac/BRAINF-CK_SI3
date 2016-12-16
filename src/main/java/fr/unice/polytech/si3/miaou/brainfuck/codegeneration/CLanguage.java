package fr.unice.polytech.si3.miaou.brainfuck.codegeneration;

import java.util.Map;
import java.util.HashMap;

import fr.unice.polytech.si3.miaou.brainfuck.instructions.Instruction;

/**
 * Translates a brainfuck program in C.
 *
 * @author Guillaume Casagrande
 */
class CLanguage extends Language {
	/**
	 * Constructs a CLanguage object and fills the map of instructions.
	 */
	CLanguage() {
		super();
		extension = "c";
		name = "c";

		instructionsTranslation.put(']', "}");
		instructionsTranslation.put('-', "memory[i]--;");
		instructionsTranslation.put(',', "memory[i] = getchar();");
		instructionsTranslation.put('+', "memory[i]++;");
		instructionsTranslation.put('[', "while (memory[i] != 0) {");
		instructionsTranslation.put('<', "i--;");
		instructionsTranslation.put('.', "putchar(memory[i]);");
		instructionsTranslation.put('>', "i++;");
	}

	@Override
	String translateInstruction(Instruction instr) {
		return "    "+instructionsTranslation.get(instr.getSymbol());
	}

	@Override
	String buildFront() {
		sb = new StringBuilder();
		sb.append("#include <stdio.h>\n");
		sb.append("#include <stdlib.h>\n");
		sb.append("#include <string.h>\n\n");

		sb.append("#define SIZE_MEMORY 30000\n\n");

		sb.append("int main() {\n");
		sb.append("    char memory[SIZE_MEMORY] = {};\n");
		sb.append("    int i = 0;\n");

		return sb.toString();
	}

	@Override
	String buildFooter() {
		sb = new StringBuilder();
		sb.append("\n    for (i = 0; i < SIZE_MEMORY; i++) {\n");
		sb.append("        if (memory[i] != 0) { printf(\"C%d: %d\\n\", i, memory[i]); }\n    }\n");
		sb.append("    return 0;\n}");

		return sb.toString();
	}
}
