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
		instructionsTranslation.put('-', "(*memory)--;");
		instructionsTranslation.put(',', "(*memory) = getchar();");
		instructionsTranslation.put('+', "(*memory)++;");
		instructionsTranslation.put('[', "while (*memory) {");
		instructionsTranslation.put('<', "memory--;");
		instructionsTranslation.put('.', "putchar(*memory);");
		instructionsTranslation.put('>', "memory++;");
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
		sb.append("    char *memory = calloc(SIZE_MEMORY, sizeof(char));\n");
		sb.append("    char *p = memory;\n");

		return sb.toString();
	}

	@Override
	String buildFooter() {
		sb = new StringBuilder();
		sb.append("\n    for (int i = 0; i < SIZE_MEMORY; i++, *p++) {\n");
		sb.append("        if (*p) { printf(\"C%d: %d\\n\", i, *p); }\n    }\n");
		sb.append("    return 0;\n}");

		return sb.toString();
	}
}
