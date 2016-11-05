package brainfuck;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import brainfuck.virtualmachine.Machine;
import brainfuck.io.Io;
import brainfuck.exceptions.BrainfuckException;
import brainfuck.io.WriteImage;
import brainfuck.io.ReadTextFile;
import brainfuck.io.ReadImageFile;

/**
 * Entry point for the application.
 *
 * @author Julien Lemaire
 * @author Pierre-Emmanuel Novac
 */
public class Main {
	/**
	 * ArgParser with parsed arguments.
	 */
	private ArgParser argp;

	/**
	 * Constructs a Main with the given ArgParser, ie. arguments parsed from command line parameters.
	 *
	 * @param argp	arguments parsed from main's args.
	 */
	Main(ArgParser argp) {
		this.argp = argp;
	}

	/**
	 * Application entry point.
	 *
	 * @param args array of received command-line arguments.
	 * @throws IOException		in case of IO error on file operation.
	 */
	public static void main(String[] args) throws IOException {
		try {
			Main app = new Main(new ArgParser(args));
			app.run();
		} catch (BrainfuckException e) {
			System.err.println(e);
			System.exit(e.getErrorCode());
		}
	}

	/**
	 * Runs the requested behaviour depending on the command line arguments.
	 *
	 * @throws IOException	in case of IO error on file operation.
	 */
	private void run() throws IOException {
		InstructionParser ip;

		if (argp.getType() == Type.IMAGE) {
			ip = imageRead(argp.getFilename());
		} else {
			ip = textFileRead(argp.getFilename());
		}

		switch(argp.getMode()) {
			case RUN:
				check(ip);
				execute(ip);
				break;
			case REWRITE:
				Translator tr = new Translator();
				tr.toShortSyntax(ip.get());
				break;
			case TRANSLATE:
				if (argp.getType() == Type.TEXT) {
					Translator tra = new Translator();
					WriteImage iw = new WriteImage(tra.toColor(textFileRead(argp.getFilename()).get()));
				} else {
					Files.copy(Paths.get(argp.getFilename()), System.out); //Copy the image file to stdout
				}
				break;
			case CHECK:
				check(ip);
				break;
		}
	}

	/**
	 * Returns an InstructionParser which parses the text file with given filename.
	 *
	 * @param filename	file's name of the text file we should parse.
	 * @return the InstructionParser which parsed the given file.
	 * @throws IOException  if an IO error arised when reading the file.
	 */
	private InstructionParser textFileRead(String filename) throws IOException {
		ReadTextFile file = new ReadTextFile(filename);

		return new InstructionParser(file.getData());
	}

	/**
	 * Returns an InstructionParser which parses the image file with given filename.
	 *
	 * @param filename	file's name of the image file we should parse.
	 * @return the InstructionParser which parsed the given file.
	 * @throws IOException	if an IO error arised when reading the file.
	 */
	private InstructionParser imageRead(String filename) throws IOException {
		ReadImageFile file = new ReadImageFile(filename);

		return new InstructionParser(file.getData());
	}

	/**
	 * Starts the Checker to make sure the program is well-formed.
	 *
	 * @param ip	InstructionParser which previously parsed a file.
	 */
	private void check(InstructionParser ip) {
		Checker checker = new Checker(ip.get());
		checker.check();
	}

	/**
	 * Starts the Interpreter to execute the instructions.
	 *
	 * @param ip	InstructionParser which previously parsed a file.
	 * @throws FileNotFoundException	if creating/opening output file failed.
	 */
	private void execute(InstructionParser ip) throws FileNotFoundException, IOException {
		Machine machine = new Machine();
		machine.setIo(new Io(argp.getInput(),argp.getOutput()));
		Interpreter interpreter = new Interpreter(ip.get());
		if (argp.isTracing()) {
			interpreter.setLogger(new Logger(argp.getFilename().substring(0, argp.getFilename().lastIndexOf("."))+".log"));
		}
		interpreter.run(machine);
	}
}
