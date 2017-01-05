package fr.unice.polytech.si3.miaou.brainfuck.codegeneration;

import fr.unice.polytech.si3.miaou.brainfuck.instructions.Instruction;

/**
 * Translates a brainfuck program in Ruby.
 *
 * @author Guillaume Casagrande
 */
class RubyLanguage extends Language {
	/**
	 * Constructs a RubyLanguage object and fills the map of instructions.
	 */
	RubyLanguage() {
		super();
		extension = "rb";
		name = "ruby";

		instructionsTranslation.put(']', "end");
		instructionsTranslation.put('-', "memory[i] -= 1");
		instructionsTranslation.put(',', "memory[i] = finput.getc()");
		instructionsTranslation.put('+', "memory[i] += 1");
		instructionsTranslation.put('[', "while memory[i] != 0");
		instructionsTranslation.put('<', "i -= 1");
		instructionsTranslation.put('.', "foutput.write(memory[i].chr)");
		instructionsTranslation.put('>', "i += 1");
	}

	@Override
	String translateInstruction(Instruction instr) {
		return instructionsTranslation.get(instr.getSymbol());
	}

	@Override
	String buildFront() {
		sb = new StringBuilder();
		sb.append("#!/usr/bin/env ruby\n\n");
		sb.append("memory = Array.new(30000, 0)\n");
		sb.append("i = 0\n");
		return sb.toString();
	}

	@Override
	String io(String in, String out) {
		sb = new StringBuilder();
		if ("System.in".equals(in)) {
			sb.append("finput = $stdin\n");
		}
		else {
			sb.append("finput = File.open(\"").append(in).append("\", 'r')\n");
		}
		if ("System.out".equals(out)) {
			sb.append("foutput = $stdout\n");
		}
		else {
            sb.append("foutput = File.open(\"").append(out).append("\", 'w')\n");
		}
		return sb.toString();
	}

	@Override
	String buildFooter() {
		sb = new StringBuilder();
		sb.append("\nfor i in (0...30000)\n");
		sb.append("    if memory[i] != 0 then\n");
		sb.append("        string = \"\\nC\"+i.to_s+\": \"+memory[i].ord.to_s\n");
		sb.append("        foutput.write(string)\n");
		sb.append("    end\n");
		sb.append("end\nfoutput.write(\"\\n\")");
		return sb.toString();
	}
}
