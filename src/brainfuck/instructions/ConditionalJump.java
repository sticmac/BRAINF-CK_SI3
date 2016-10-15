package brainfuck.instructions;

import brainfuck.BracketCounter;

/**
 * Interface which describes a conditional jump instruction (Jump or Back).
 */
public interface ConditionalJump {
	/**
	 * Called when we need to increment a value in the BracketCounter.
	 *
	 * @param bc	BracketCounter whose counter is to be incremented.
	 */
	void incr(BracketCounter bc);
}
