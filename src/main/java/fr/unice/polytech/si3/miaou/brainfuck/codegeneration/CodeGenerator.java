package fr.unice.polytech.si3.miaou.brainfuck.codegeneration;

import java.io.IOException;
import java.util.List;
import java.io.File;

import fr.unice.polytech.si3.miaou.brainfuck.io.WriteTextFile;
import fr.unice.polytech.si3.miaou.brainfuck.io.ReadTextFile;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Instruction;

public class CodeGenerator {
	/**
	 * The C file.
	 */
	private WriteTextFile wtf;

	/**
	 * A test.
	 */
	private ReadTextFile rtf;

	/**
	 * Main constructor of the <code>CodeGenerator</code> class.
	 *
	 * @param filename the name of the program file.
	 * @throws IOException	if it's impossible to create the log file.
	 */
	public CodeGenerator(String filename) throws IOException {
		filename = filename.substring(0, filename.lastIndexOf("."))+".c";
		File f = new File(filename);
		if (f.exists()) { f.delete(); }
		wtf = new WriteTextFile(filename);
		front();
	}

	/**
	 * Write the equivalent of a call of an instruction in C.
	 */
	public void writeInstructions(List<Instruction> instructions) {
		for (Instruction instr : instructions) {
			wtf.write("    "+instr.getCode());
		}
	}

	public void front() throws IOException {
		rtf = new ReadTextFile("src/main/java/fr/unice/polytech/si3/miaou/brainfuck/codegeneration/front.txt");
		rtf.getData().forEachOrdered(wtf::write);
	}

	public void footer() throws IOException {
		rtf = new ReadTextFile("src/main/java/fr/unice/polytech/si3/miaou/brainfuck/codegeneration/footer.txt");
		rtf.getData().forEachOrdered(wtf::write);
	}
}
