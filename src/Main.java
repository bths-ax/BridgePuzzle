import java.util.Scanner;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		ArrayList<Integer> crossingTimes = new ArrayList<Integer>();

		while (true) {
			System.out.print(String.format("Crossing time #%d (-1 to end): ", crossingTimes.size() + 1));
			int crossingTime = scanner.nextInt();
			if (crossingTime == -1) break;
			crossingTimes.add(crossingTime);
		}

		System.out.print("Maximum time allowed: ");
		int maximumTime = scanner.nextInt();

		int[] crossingTimesArr = new int[crossingTimes.size()];
		for (int i = 0; i < crossingTimes.size(); i++)
			crossingTimesArr[i] = crossingTimes.get(i);

		Puzzle puzzle = new Puzzle(crossingTimesArr, maximumTime);
		Crossing node = puzzle.solve();
		if (node == null) {
			System.out.println("No solution found");
			return;
		} else {
			String direction = "=>";
			while (node.next != null) {
				node = node.next;
				if (node.person2 != -1) {
					System.out.println(direction + ": Person " + (node.person1 + 1) + " and " + (node.person2 + 1));
				} else {
					System.out.println(direction + ": Person " + (node.person1 + 1));
				}
				if (direction.equals("=>")) direction = "<=";
				else direction = "=>";
			}
		}
	}
}
