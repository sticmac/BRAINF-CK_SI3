package brainfuck;

import java.io.IOException;
import brainfuck.virtualmachine.Machine;

public class Main {
	public static void main(String[] args) throws SyntaxException, IOException {
		ArgParser argp = new ArgParser(args);

		ReadFile file = new ReadFile(argp.getFilename());

		Machine machine = new Machine();

		Interpreter interpreter = new Interpreter(file.getLines());

		interpreter.run(machine);
	}
}
