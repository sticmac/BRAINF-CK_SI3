package fr.unice.polytech.si3.miaou.brainfuck.codegeneration;

import java.util.Map;
import java.util.HashMap;

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
		instructionsTranslation.put(',', "user_input = gets.chomp\n    memory[i] = user_input[0,1]");
		instructionsTranslation.put('+', "memory[i] += 1");
		instructionsTranslation.put('[', "while memory[i] != 0");
		instructionsTranslation.put('<', "i -= 1");
		instructionsTranslation.put('.', "print memory[i].chr");
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
	String buildFooter() {
		sb = new StringBuilder();
		sb.append("\nfor i in (0...30000)\n");
		sb.append("    if memory[i] != 0 then\n");
		sb.append("        print \"C\",i,\": \",memory[i],\"\\n\"\n");
		sb.append("    end\nend");
		return sb.toString();
	}
}
