package brainfuck.virtualmachine;

import java.util.stream.Stream;
import java.util.Arrays;
import java.lang.Character;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import brainfuck.InstructionSet;
import brainfuck.Instruction;
import brainfuck.WriteTextFile;
import brainfuck.instructions.ConditionalJump;
import brainfuck.BracketCounter;

/**
 * Actual virtual machine which processes the instructions and interracts with the memory.
 *
 * @author Pierre-Emmanuel Novac
 * @author Nassim Bounouas
 * @see brainfuck.Instruction
 * @see InstructionSet
 * @see Memory
 */
public class Machine {
	/**
	 * Virtual machine's data memory.
	 */
	private Memory memory;

	/**
	 * Instruction set supported on the virtual machine.
	 */
	private InstructionSet iset;

	/**
	 * Input flux if specified
	 */
	private	List<Character> inputs;

	/**
	 * Output flux if specified
	 */
	private WriteTextFile output;

	/**
	 * Current location in memory.
	 */
	private int location = 0;

	private boolean jumping = false;

	private boolean reversed = false;

	private BracketCounter bracketCounter;

	/**
	 * Constructs a new virtual machine, initialize its Memory.
	 */
	public Machine() {
		memory = new Memory();
		iset = new InstructionSet();
		bracketCounter = new BracketCounter() {
			@Override protected void onMatch() {
				Machine.this.setJumping(false);
				Machine.this.setReversed(false);
				this.reset();
			}
		};
	}

	/**
	 * Write a value at the current memory location.
	 *
	 * @param value	value to write.
	 */
	public void writeMemory(byte value) {
		memory.set(location, value);
	}

	/**
	 * Read the value at the current memory location.
	 *
	 * @return value read.
	 */
	public byte readMemory() {
		return memory.get(location);
	}

	/**
	 * Fetch an instruction by its keyword and execute it.
	 *
	 * @param name	Instruction's keyword
	 * @return false if the instruction is invalid true otherwise.
	 */
	public boolean executeOp(String name) {
		Instruction instr = iset.getOp(name);
		if (instr == null) return false;
		if (!jumping) instr.accept(this);
		return true;
	}

	/**
	 * Fetch an instruction by its symbol and execute it.
	 *
	 * @param symbol	Instruction's symbol
	 * @return false if the instruction is invalid, true otherwise.
	 */
	public boolean executeOp(char symbol) {
		Instruction instr = iset.getOp(symbol);
		if (instr == null) return false;
		if (!jumping) instr.accept(this);
		return true;
	}

	public void executeOp(Instruction instr) {
		if (!jumping) instr.accept(this); // Jumping may be modified in there
		if (jumping && instr instanceof ConditionalJump) {
			((ConditionalJump) instr).incr(bracketCounter);
		}

	}

	/**
	 * @return current {@link Machine#location} in memory.
	 */
	public int getLocation() {
		return location;
	}

	/**
	 * Change the location in memory.
	 *
	 * @param i	new {@link Machine#location}.
	 */
	public void setLocation(int i) {
		memory.checkBounds(i);
		location = i;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setReversed(boolean r) {
		this.reversed = r;
	}

	public boolean isReversed() {
		return reversed;
	}

	/**
	 * Store an input flux in the machine 
         *
	 * @param ss	Input flux to load in the machine
	 */
	public void setInputFlux(Stream<String> ss){
		Object[] obArr = ss.toArray();
		String[] arr = Arrays.copyOf(obArr, obArr.length, String[].class);

		inputs = new ArrayList<Character>();

		for(String s : arr)
		{
			for(char c : s.toCharArray())
			{
				inputs.add(new Character(c));
			}
		}
	}

	public void setOutputFlux(WriteTextFile wtf){
		this.output = wtf;
	}

	/**
 	 * Return the next input value read in the file
	 *
	 * @return The next inputted character
	 */
	public Character getInputFlux(){
		if(this.inputs == null){
			Scanner reader = new Scanner(System.in);
			char c = reader.next().charAt(0);
			return c;
		}else{
			System.out.println(this.inputs.isEmpty());
			for(int i = 0; i < this.inputs.size(); i++)
			{
				System.out.println(this.inputs.get(i));
			}
			if(this.inputs.isEmpty()){
				System.exit(42);
			}
			return this.inputs.remove(0);	
		}
	}	

	public void useOutputFlux(String str){
		if(this.output == null){
			System.out.println(str);
		}else{
			this.output.write(str);
		}
	}

	/**
	 * @return Memory's content.
	 */
	public String dumpMemory() {
		return memory.toString();
	}
}
