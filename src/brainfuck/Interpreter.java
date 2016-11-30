package brainfuck;

import java.util.List;
import java.io.IOException;

import brainfuck.instructions.Instruction;
import brainfuck.virtualmachine.Machine;
import brainfuck.JumpTable;

/**
 * Reads the instructions from a List and execute them.
 *
 * @author Pierre-Emmanuel Novac
 * @see Machine
 */
public class Interpreter {
	/**
	 * List containing the instructions to execute.
	 */
	private List<Instruction> instructions;

	/**
	 * Jumptable used to associate ConditionalJump instructions
	 */
	private JumpTable jumptable;

	/**
	 * The Logger for the current instance of the program.
	 */
	private Logger logger;

	/**
	 * Constructs an interpreter using the given List of Instruction.
	 *
	 * @param instructions 	List of Instruction containing instructions to execute.
	 * @param jumptable     The jumptable containing conditional jumps positions
	 */
	public Interpreter(List<Instruction> instructions, JumpTable jumptable) {
		this.instructions = instructions;
		this.jumptable = jumptable;
	}

	/**
	 * Set the Logger.
	 *
	 * @param log	Logger to use for this instance of the Interpreter.
	 */
	public void setLogger(Logger log) {
		this.logger = log;
	}

	/**
	 * Executes the instructions from the List and print memory content if the program terminated successfully.
	 * Parses in reverse order when Machine::isReversed returns true.
	 *
	 * @param machine	Virtual machine which executes the instructions.
	 * @throws IOException	if any attempt to write in the log file failed.
	 */
	public void run(Machine machine) throws IOException {
		int i = 0;
		Metrics.PROG_SIZE.set(instructions.size());
		Metrics.EXEC_TIME.start();
		while (i >= 0 && i < instructions.size()) {
			machine.executeOp(instructions.get(i));
			if (logger != null) {
				logger.logStep(i, instructions.get(i), machine);
			}
			if (machine.isJumping()){
				i = jumptable.getJump(i).intValue();
			} else {
				i++;
			}
			Metrics.EXEC_MOVE.incr();
		}
		Metrics.EXEC_TIME.stop();
		System.out.print(machine.dumpMemory());
		System.out.print(Metrics.dump());
	}
}
