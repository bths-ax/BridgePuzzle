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

	public Crossing solve() {
		boolean[] left = new boolean[crossingTimes.length];
		boolean[] right = new boolean[crossingTimes.length];
		for (int i = 0; i < crossingTimes.length; i++)
			left[i] = true;

		Crossing root = new Crossing();
		root.person1 = -1;
		root.person2 = -1;

		if (recurse(root, left, right, true) <= maximumTime) {
			return root;
		} else {
			return null;
		}
	}

	public int recurse(Crossing node, boolean[] left, boolean[] right, boolean crossLeft) {
		if (crossLeft) {
			int minTime = Integer.MAX_VALUE;
			int minIndex1 = -1;
			int minIndex2 = -1;

			for (int i = 0; i < crossingTimes.length; i++) if (left[i]) {
				for (int j = i + 1; j < crossingTimes.length; j++) if (left[j]) {
					left[i] = left[j] = false;
					right[i] = right[j] = true;

					Crossing next = new Crossing();
					next.person1 = i;
					next.person2 = j;

					int time = Math.max(crossingTimes[i], crossingTimes[j]);
					int nextTime = recurse(next, left, right, false);
					if (time + nextTime < minTime) {
						minTime = time + nextTime;
						minIndex1 = i;
						minIndex2 = j;
						node.next = next;
					}

					left[i] = left[j] = true;
					right[i] = right[j] = false;
				}
			}

			return minTime;
		} else {
			boolean allCrossed = true;
			for (int i = 0; i < crossingTimes.length; i++)
				if (left[i]) allCrossed = false;
			if (allCrossed) {
				return 0;
			}

			int minTime = Integer.MAX_VALUE;
			int minIndex = -1;

			for (int i = 0; i < crossingTimes.length; i++) if (right[i]) {
				right[i] = false;
				left[i] = true;
				
				Crossing next = new Crossing();
				next.person1 = i;
				next.person2 = -1;

				int time = crossingTimes[i];
				int nextTime = recurse(next, left, right, true);
				if (time + nextTime < minTime) {
					minTime = time + nextTime;
					minIndex = i;
					node.next = next;
				}

				right[i] = true;
				left[i] = false;
			}

			return minTime;
		}
	}
}
