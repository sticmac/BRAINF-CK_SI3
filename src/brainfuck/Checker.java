package brainfuck;

import java.util.List;
import brainfuck.instructions.ConditionalJump;

public class Checker {
	private List<Instruction> instructions;
	private BracketCounter bracketCounter;

	public Checker(List<Instruction> instructions) {
		this.instructions = instructions;
		bracketCounter = new BracketCounter() {
			@Override protected void onMismatch() {
				System.err.println("Left and right bracket count not matching");
				System.exit(4);
			}
		};
	}

	public void check() {
		instructions.forEach(i -> {
			if (i instanceof ConditionalJump) {
				((ConditionalJump) i).incr(bracketCounter);
			}
		});
		bracketCounter.checkMatching();
	}
}
