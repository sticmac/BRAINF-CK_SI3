package brainfuck;

public class BracketCounter {
	private int left = 0;
	private int right = 0;

	public BracketCounter() {
	}

	protected void reset() {
		left = 0;
		right = 0;
	}

	protected void onMatch() {
	}

	protected void onMismatch() {
	}

	public void checkMatching() {
		if (left != right) onMismatch();
	}

	public int getLeft() {
		return left;
	}

	public int getRight() {
		return right;
	}

	public void incrLeft() {
		left++;
		if (left == right) onMatch();
	}

	public void incrRight() {
		right++;
		if (left == right) onMatch();
		else if (right > left) onMismatch();
	}
}
