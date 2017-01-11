package fr.unice.polytech.si3.miaou.brainfuck;

/**
 * Instruction called for each procedure call.
 *
 * @author Julien Lemaire
 */
public class Procedure {
	/**
	 * Position where the Procedure's instructions are placed in the Instructions' memory.
 	 */
	private int position;

	/**
	 * Name of the instruction.
	 */
	private String name;

	/**
	 * Main constructor of Procedure.
	 *
	 * @param name name of the Procedure.
	 * @param position position where the Procedure's instructions are placed in the Instructions' memory.
	 */
	public Procedure(String name, int position) {
		this.name = name;
		this.position = position;
	}

	/**
	 * Return the instruction location of the procedure.
	 *
	 * @return the location of the instructions in the instructions list.
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * Return the name of the procedure.
	 *
	 * @return the name of the procedure.
	 */
	public String getName() {
		return name;
	}
}
