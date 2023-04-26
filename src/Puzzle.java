import java.util.ArrayList;

class Crossing {
	public int person1;
	public int person2;
	public Crossing next;
}

public class Puzzle {
	private int[] crossingTimes;
	private int maximumTime;

	public Puzzle(int[] crossingTimes, int maximumTime) {
		this.crossingTimes = crossingTimes;
		this.maximumTime = maximumTime;
	}

	public boolean solve() {
		boolean[] left = new boolean[crossingTimes.length];
		boolean[] right = new boolean[crossingTimes.length];
		for (int i = 0; i < crossingTimes.length; i++)
			left[i] = true;

		return recurse(left, right, true);
	}

	public int recurse(boolean[] left, boolean[] right, boolean crossLeft) {
		int minTime = Integer.MAX_INT;

		if (crossLeft) {
			// cross 2 people
		} else {
			// only cross 1 person with the min time
		}

		return minTime;
	}
}
