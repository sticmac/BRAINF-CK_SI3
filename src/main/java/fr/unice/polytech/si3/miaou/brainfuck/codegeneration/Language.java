package fr.unice.polytech.si3.miaou.brainfuck.codegeneration;

import fr.unice.polytech.si3.miaou.brainfuck.instructions.Instruction;

import java.util.Map;
import java.util.HashMap;

/**
 * Defines the behaviour of the languages.
 *
 * @author Guillaume Casagrande
 */
abstract class Language {
	Map<Character, String> instructionsTranslation;
	String extension;
	StringBuilder sb;

	Language() {
		instructionsTranslation = new HashMap<>();
	}

	abstract String translateInstruction(Instruction instr);

	abstract String buildFront();

	abstract String buildFooter();

	String getExtension() {
		return extension;
	}
}
