package brainfuck.virtualmachine;

/**
 * Memory container which is basically an interface for an array of bytes.
 *
 * @author Pierre-Emmanuel Novac
 * @see OutOfMemoryException
 */
public class Memory {
	/**
	 * Default memory size, 30,000 bytes.
	 */
	private static final int DEFAULT_SIZE = 30000;

	/**
	 * Offset allowing to obtain values from 0 to 255.
	 */
        public static final int OFFSET = 128;

	/**
	 * Memory block allocated for the virtual machine.
	 */
	private byte[] memory;

	/**
	 * Constructs a new Memory with given size.
	 *
	 * @param size	wanted memory size.
	 */
	public Memory(int size) {
		memory = new byte[size];
		for (int i = 0; i < memory.length; i++) {
			memory[i] = Byte.MIN_VALUE;
		}
	}

	/**
	 * Constructs a new Memory with the default size.
	 */
	public Memory() {
		this(DEFAULT_SIZE);
	}

	/**
	 * Exit the program with code 2 if the given index is outside the memory block.
	 *
	 * @param i	index to check.
	 */
	public void checkBounds(int i) {
		if (i < 0 || i >= memory.length) {
			System.err.println("Error: This data is out of memory");
			System.exit(2);
		}
	}

	/**
	 * Returns the value in memory at the specified index.
	 *
	 * @param i	index in memory.
	 * @return current value at index i.
	 */
	public byte get(int i) {
		checkBounds(i);
		return memory[i];
	}

	/**
	 * Change the value in memory at the specified index.
	 *
	 * @param i	index in memory.
	 * @param value	new value.
	 */
	public void set(int i, byte value) {
		checkBounds(i);
		memory[i] = value;
	}

	/**
	 * Returns the memory content in a String.
	 * Each memory cell whose content is greater than 0 is added to the String on a new line.
	 * Format is: "C&lt;cell_number&gt;: &lt;value&gt;".
	 *
	 * @return memory content as a String.
	 */
	@Override
	public String toString() {
		StringBuilder tmp = new StringBuilder();
		for (int i = 0; i < memory.length; i++) {
			if (memory[i] != Byte.MIN_VALUE) {
				tmp.append("C" + i + ": " + (memory[i] + OFFSET) + "\n");
			}
		}
		return tmp.toString();
	}
}
