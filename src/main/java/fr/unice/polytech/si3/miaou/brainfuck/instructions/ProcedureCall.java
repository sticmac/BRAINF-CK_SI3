package fr.unice.polytech.si3.miaou.brainfuck.instructions;

import fr.unice.polytech.si3.miaou.brainfuck.Procedure;
import fr.unice.polytech.si3.miaou.brainfuck.virtualmachine.Machine;

import java.util.Optional;

/**
 * Description
 *
 * @author Julien Lemaire
 */
public class ProcedureCall extends Instruction {
	private Procedure proc;
	private Optional<Integer> parameter;

	public ProcedureCall(Procedure proc) {
		super(proc.getName(), '\0', 0);
		this.proc = proc;
		parameter = Optional.empty();
	}

	public ProcedureCall(Procedure proc, int parameter) {
		this(proc);
		this.parameter = Optional.of(parameter);
	}

	@Override
	public void accept(Machine machine) {
		if (parameter.isPresent()) {
			machine.saveMemoryAddress();
			machine.setLocation(parameter.get());
		}
		machine.saveReturnAddress();
		machine.setInstrPointer(proc.getPosition()-1); //-1, because the instruction pointer is incremented right after the execution of the Procedure instruction.
	}
}
