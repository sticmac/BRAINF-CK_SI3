package brainfuck;

import java.util.Map;
import java.util.HashMap;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Iterator;


import brainfuck.instructions.Instruction;
import brainfuck.instructions.Jump;
import brainfuck.instructions.Back;
import brainfuck.instructions.ConditionalJump;
import brainfuck.exceptions.InvalidInstructionException;

/**
 * instructions from a List and execute them.
 *
 * @author Nassim Bounouas 
 * @see Instruction
 */
public class JumpTable {

	/**
	 * Hashmap from Jump to Back instructions
	 */
	private Map<Integer, Integer> mapJumpToBack;
	
	/**
	 * Hashmap from Back to Jump instructions
	 */
	private Map<Integer, Integer> mapBackToJump;

	/**
	 * Stack used as a buffer to store a Jump waiting its Back instruction
	 */
	private Deque<Integer> intermediateStack;

	/**
	 * Constructs an interpreter using the given List of Instruction.
	 *
	 * @param instructions 	List of Instruction containing instructions to execute.
	 */
	public JumpTable() {
		this.mapJumpToBack = new HashMap<>();
		this.mapBackToJump = new HashMap<>();
		this.intermediateStack = new ArrayDeque<>();
	}

	/**
	 * Add and associate a Jump/Back instruction to its ?
	 *
	 * @param i The instruction to add in the Jumptable
	 * @param index The instruction index
	 */
	public void bind(Instruction i, int index) {
		index--; // Instruction list is counting from 1 to n and Machine from 0 to n
		if (i instanceof Jump) {
			this.intermediateStack.push(new Integer(index));
		}else if (i instanceof Back) {
			Integer jumpIndex = this.intermediateStack.pop();
			this.mapJumpToBack.put(jumpIndex, new Integer(index));
			this.mapBackToJump.put(new Integer(index), jumpIndex);
		}
	}

	/**
	 * Return the index of the associated conditionnal Jump.
	 *
	 * @param instr The instrution from where we are jumping
	 * @param index THe index from where we are jumping
	 */
	public Integer getJump(Instruction instr, int index) {
		if (instr instanceof Jump) {
			return this.mapJumpToBack.get(new Integer(index));
		} else if(instr instanceof Back) {
			return this.mapBackToJump.get(new Integer(index));
		} else {
			throw new InvalidInstructionException(-1);
		}
	}

	/**
	 * Return a String which display the jumptable content.
	 */
	@Override
	public String toString() {
		Iterator it = this.mapJumpToBack.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			System.out.println(pair.getKey() + " = " + pair.getValue());
		}
		System.out.println("Size : " + this.mapJumpToBack.size());
	}
}
