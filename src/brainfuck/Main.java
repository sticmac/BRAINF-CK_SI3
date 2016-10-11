package brainfuck;

import java.io.IOException;
import brainfuck.virtualmachine.Machine;

public class Main {
	public static void main(String[] args) throws SyntaxException, IOException {
		ArgParser argp = new ArgParser(args);

		ReadFile file = new ReadFile(argp.getFilename());

		InstructionParser ip = new InstructionParser(file.getLines());

		Machine machine = new Machine();

		Checker checker = new Checker(ip.get());

		checker.check();

		Interpreter interpreter = new Interpreter(ip.get());

		interpreter.run(machine);
	}
}
