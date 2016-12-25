package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import fr.unice.polytech.si3.miaou.brainfuck.exceptions.FunctionUsageException;
import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

/**
 * Instruction called for each procedure call.
 *
 * @author Julien Lemaire
 */
public class Procedure extends Instruction {
	/**
	 * Position where the Procedure's instructions are placed in the Instructions' memory.
 	 */
	private int position;

	/**
	 * Map between parameters name and Optional of their values.
	 */
	private Map<String, Optional<Integer>> parameters;

	/**
	 * Main constructor of Procedure.
	 *
	 * @param name name of the Procedure.
	 * @param position position where the Procedure's instructions are placed in the Instructions' memory.
	 */
	public Procedure(String name, int position, String... parameters) {
		super(name, '\0', 0);
		this.position = position;
		this.parameters = new HashMap<>();
		for (String p : parameters) {
			this.parameters.put(p, Optional.empty());
		}
	}

	public void setParametersValues(int[] parametersValues) {
		int i = 0;
		int size = parametersValues.length;
		if (parameters.entrySet().size() != size) {
			throw new FunctionUsageException("Needs "+size+" parameter values.");
		}
		for (Iterator<Map.Entry<String, Optional<Integer>>> it = parameters.entrySet().iterator() ; it.hasNext() ; ) {
			if (i < size) {
				it.next().setValue(Optional.of(parametersValues[i]));
				i++;
			}
		}
	}

	/**
	 * Action performed by the instruction: move the instruction pointer to the Procedure's location.
	 * Overrides <a href="https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html">Consumer</a>'s method.
	 *
	 * @param machine	Virtual Machine whose state will be altered
	 */
	@Override
	public void accept(Machine machine) {
		machine.saveReturnAddress();
		machine.setInstrPointer(position-1); //-1, because the instruction pointer is incremented right after the execution of the Procedure instruction.
	}
}
