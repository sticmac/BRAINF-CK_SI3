package fr.unice.polytech.si3.miaou.brainfuck;

import java.util.Map;
import java.util.HashMap;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Iterator;


import fr.unice.polytech.si3.miaou.brainfuck.instructions.Instruction;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Jump;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.Back;
import fr.unice.polytech.si3.miaou.brainfuck.instructions.ConditionalJump;
import fr.unice.polytech.si3.miaou.brainfuck.exceptions.InvalidInstructionException;

/**
 * instructions from a List and execute them.
 *
 * @author Nassim Bounouas 
 * @see Instruction
 */
public class JumpTable {

	/**
	 * Hashmap containing conditionnal jump instructions indexes
	 */
	private Map<Integer, Integer> conditionnalJumpMap;
	
	/**
	 * Stack used as a buffer to store a Jump waiting its Back instruction
	 */
	private Deque<Integer> intermediateStack;

	/**
	 * Constructs an interpreter using the given List of Instruction.
	 */
	public JumpTable() {
		this.conditionnalJumpMap = new HashMap<>();
		this.intermediateStack = new ArrayDeque<>();
	}

	/**
	 * Add and associate a Jump/Back instruction to its corresponding instruction 
	 *
	 * @param i The instruction to add in the Jumptable
	 * @param index The instruction index
	 */
	public void bind(Instruction i, int index) {
		index--; // Instruction list is counting from 1 to n and Machine from 0 to n
		if (i instanceof Jump) {
			this.intermediateStack.push(new Integer(index));
		} else if (i instanceof Back) {
			Integer jumpIndex = this.intermediateStack.pop();
			Integer backIndex = index;
			this.conditionnalJumpMap.put(jumpIndex, backIndex);
			this.conditionnalJumpMap.put(backIndex, jumpIndex);
		}
	}

	/**
	 * Return the index of the associated conditionnal Jump.
	 *
	 * @param index The index from where we are jumping
	 * @return The target index
	 */
	public Integer getJump(int index) {
			return this.conditionnalJumpMap.get(new Integer(index));
	}

	/**
	 * Return a String which display the jumptable content.
	 */
	@Override
	public String toString() {
		String str = "";
		Iterator it = this.conditionnalJumpMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			str += pair.getKey() + " = " + pair.getValue() + "\n";
		}
		str += "Size : " + this.conditionnalJumpMap.size() + "\n";
		return str;
	}
}
