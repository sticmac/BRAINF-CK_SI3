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
	/**
	 * Lists the equivalent code of each instruction.
	 */
	protected Map<Character, String> instructionsTranslation;

	/**
	 * Reprensents the extension of the file according to the language.
	 */
	protected String extension;

	/**
	 * The name of the language.
	 */
	protected String name;

	/**
	 * Used to create a string packaging several lines of code.
	 */
	protected StringBuilder sb;

	/**
	 * Constructs a Language object and create the map of instructions.
	 */
	Language() {
		instructionsTranslation = new HashMap<>();
	}

	/**
	 * Picks an instruction and gives its equivalent in another language.
	 */
	abstract String translateInstruction(Instruction instr);

	/**
	 * Writes the front of the file.
	 */
	abstract String buildFront();

	/**
	 * Writes the footer of the file.
	 */
	abstract String buildFooter();

	/**
	 * Creates the io files.
	 */
	abstract String io(String in, String out);

	/**
	 * Returns the extension of the file.
	 */
	String getExtension() {
		return extension;
	}

	/**
	 * Returns the name of the language.
	 */
	String getName() {
		return name;
	}
}
