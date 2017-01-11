package fr.unice.polytech.si3.miaou.brainfuck.codegeneration;

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

		instructionsTranslation.put(iset.getOp("BACK"), "}");
		instructionsTranslation.put(iset.getOp("DECR"), "(*memory)--;");
		instructionsTranslation.put(iset.getOp("IN"), "(*memory) = fgetc(finput);");
		instructionsTranslation.put(iset.getOp("INCR"), "(*memory)++;");
		instructionsTranslation.put(iset.getOp("JUMP"), "while (*memory) {");
		instructionsTranslation.put(iset.getOp("LEFT"), "memory--;");
		instructionsTranslation.put(iset.getOp("OUT"), "fputc(*memory, foutput);");
		instructionsTranslation.put(iset.getOp("RIGHT"), "memory++;");
		instructionsTranslation.put(iset.getOp("RET"), "}");
	}

	@Override
	String translateInstruction(Instruction instr) {
		sb = new StringBuilder();
		if (counter == 42) {
			sb.append("    ").append(createProcedure("nameProc")).append("\n");
		}
		counter++;
		sb.append("    ").append(instructionsTranslation.get(instr.getName()));
		return sb.toString();
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
	String io(String in, String out) {
		sb = new StringBuilder();
		sb.append("    FILE *finput;\n");
		sb.append("    FILE *foutput;\n");
		if ("System.in".equals(in)) {
			sb.append("    finput = stdin;\n");
		}
		else {
            sb.append("    finput = fopen(\"").append(in).append("\", \"r\");\n");
		}
		if ("System.out".equals(out)) {
			sb.append("    foutput = stdout;\n");
		}
		else {
            sb.append("    foutput = fopen(\"").append(out).append("\", \"w\");\n");
		}
		return sb.toString();
	}

	@Override
	String buildFooter() {
		sb = new StringBuilder();
		sb.append("\n    for (int i = 0; i < SIZE_MEMORY; i++, *p++) {\n");
		sb.append("        if (*p) { fprintf(foutput, \"\\nC%d: %d\", i, *p); }\n    }\n");
		sb.append("    fprintf(foutput, \"\\n\");\n");
		sb.append("    return 0;\n}");

		return sb.toString();
	}

	@Override
	String createProcedure(String name) {
		instructionsTranslation.put(iset.getProc(name), name+"();");
		return "void "+name+"() {";
	}
}
