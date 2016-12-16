package fr.unice.polytech.si3.miaou.brainfuck.codegeneration;

import java.io.IOException;
import java.util.List;
import java.io.File;

import fr.unice.polytech.si3.miaou.brainfuck.io.WriteTextFile;
import fr.unice.polytech.si3.miaou.brainfuck.io.ReadTextFile;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Instruction;

/**
 * Builds a file that is the translation of the brainfuck program in another language.
 *
 * @author Guillaume Casagrande
 */
public class CodeGenerator {
	/**
	 * The file where the program has to be wrote.
	 */
	private WriteTextFile wtf;

	/**
	 * The Language object used to translate the code in another language.
	 */
	private Language lang;

	/**
	 * Main constructor of the <code>CodeGenerator</code> class.
	 *
	 * @param filename the name of the program file.
	 * @param language the name of the language destination.
	 * @throws IOException	if it's impossible to create the log file.
	 */
	public CodeGenerator(String filename, String language) throws IOException {
		LanguageSet ls = new LanguageSet();
		lang = ls.getLanguage(language);

		filename = filename.substring(0, filename.lastIndexOf("."))+"."+lang.getExtension();
		File f = new File(filename);
		if (f.exists()) { f.delete(); }

		wtf = new WriteTextFile(filename);
		front();
	}

	/**
	 * Writes the equivalent of a call of an instruction in C.
	 */
	public void writeInstructions(List<Instruction> instructions) {
		for (Instruction instr : instructions) {
			wtf.write(lang.translateInstruction(instr));
		}
	}

	/**
	 * Writes the front of the file.
	 */
	private void front() throws IOException {
		wtf.write(lang.buildFront());
	}

	/**
	 * Writes the footer of the file.
	 */
	public void footer() throws IOException {
		wtf.write(lang.buildFooter());
	}
}
