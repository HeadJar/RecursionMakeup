
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * 
 * @author Jarhead This is to get the longest path for right and down for all
 *         directions
 */
public class RecursionMakeup {

	static ArrayList<Index> easyArray = new ArrayList<Index>();
	static ArrayList<Index> hardArray = new ArrayList<Index>();

	/**
	 * This method will fill the 2d array sent in
	 * 
	 * @param map
	 *            map is the 2d array sent in to be filled with random nums from 3
	 *            to 6
	 */
	public static void fillArray(int[][] map) {

		int row = map.length;
		int col = map[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				Random randomNum = new Random();
				int n = randomNum.nextInt(4) + 3;
				map[i][j] = n;
			}
		}

	}

	/**
	 * This will print the array Mostly for testing purposes
	 * 
	 * @param map
	 *            Again this is sent in to print the rows and columns
	 */
	public static void printArray(int[][] map) {

		for (int[] row : map)

			// converting each row as string
			// and then printing in a separate line
			System.out.println(Arrays.toString(row));
	}

	/**
	 * This will choose a random element in the map
	 * 
	 * @param map
	 *            This is the map that will be used
	 * @return returns an Index
	 */
	public static Index chooseElement(int[][] map) {

		int row = map.length;
		int col = map[0].length;

		int randomRow = 0;
		int randomCol = 0;

		randomRow = (int) (Math.random() * row);
		randomCol = (int) (Math.random() * col);

		int value = map[randomRow][randomCol];
		Index element = new Index(randomRow, randomCol, value);

		return element;
	}

	/**
	 * This first checks if the element is at a corner or edge and returns boolean
	 * accordingly Then if it can move up, down, left, right etc. It will add that
	 * element to an array with a size of 8. This is because it is to represent
	 * every number around the first number. Then it gets the number of valid paths,
	 * this means it checks to see if any directions are null. Then from the
	 * remaining it will return the greatest number and return the function so it
	 * will move to the next greatest element.
	 * 
	 * @param map
	 *            This is the element sent in
	 * @param element
	 *            This is the element that is used
	 * @param temp
	 *            This is the arraylist to store the path
	 * @return
	 */
	public static ArrayList<Index> pathHard(int[][] map, Index element, ArrayList<Index> temp) {

		int r = element.getRow();
		int c = element.getCol();
		int value = element.getValue();

		Index[] availablePath = new Index[8];

		boolean validDown = r < (map.length - 1);
		boolean validRight = c < (map[0].length - 1);
		boolean validLeft = c > 0;
		boolean validUp = r > 0;

		boolean validUpDiagonalLeft = (validLeft && validUp);
		boolean validUpDiagonalRight = (validRight && validUp);
		boolean validDownDiagonalLeft = (validLeft && validDown);
		boolean validDownDiagonalRight = (validRight && validDown);

		// checks for initial valid paths
		if (validUp) {
			// if you can go up and the element above has not been used
			// store that element in the array

			Index newElement = new Index(r - 1, c, map[r - 1][c]);
			if (!used(newElement, temp)) {
				availablePath[0] = newElement;
			}
		}
		if (validDown) {
			Index newElement = new Index(r + 1, c, map[r + 1][c]);

			if (!used(newElement, temp)) {
				availablePath[1] = newElement;
			}
		}
		if (validLeft) {
			Index newElement = new Index(r, c - 1, map[r][c - 1]);
			if (!used(newElement, temp)) {
				availablePath[2] = newElement;
			}
		}
		if (validRight) {

			Index newElement = new Index(r, c + 1, map[r][c + 1]);
			if (!used(newElement, temp)) {
				availablePath[3] = newElement;
			}
		}
		if (validUpDiagonalLeft) {
			Index newElement = new Index(r - 1, c - 1, map[r - 1][c - 1]);
			if (!used(newElement, temp)) {
				availablePath[4] = newElement;
			}
		}
		if (validUpDiagonalRight) {
			Index newElement = new Index(r - 1, c + 1, map[r - 1][c + 1]);
			if (!used(newElement, temp)) {
				availablePath[5] = newElement;
			}
		}
		if (validDownDiagonalLeft) {
			Index newElement = new Index(r + 1, c - 1, map[r + 1][c - 1]);
			if (!used(newElement, temp)) {
				availablePath[6] = newElement;
			}
		}
		if (validDownDiagonalRight) {
			Index newElement = new Index(r + 1, c + 1, map[r + 1][c + 1]);
			if (!used(newElement, temp)) {
				availablePath[7] = newElement;
			}
		}
		ArrayList<Index> possiblePaths = possiblePath(availablePath);
		Index greatestElement = maxNum(possiblePaths);
		// check for greatest
		if (greatestElement != null && greatestElement.getValue() >= value) {

			temp.add(greatestElement);
			return pathHard(map, greatestElement, temp);

		}

		return temp;

	}

	/**
	 * This checks for the number of possible paths available
	 * 
	 * @param array
	 *            This is to show how many nums are available
	 * @return returns an arraylist with the numbers
	 */
	// Returns the greatest index of the numbers surrounding the element
	public static ArrayList<Index> possiblePath(Index[] array) {

		ArrayList<Index> numList = new ArrayList<Index>();

		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] != null) {
				numList.add(array[i]);
			}
		}
		return numList;
	}

	/**
	 * This returns the greatest number of those in the arrayList
	 * 
	 * @param numList
	 * @return
	 */
	public static Index maxNum(ArrayList<Index> numList) {
		int num = 0;
		Index element = null;
		for (int j = 0; j < numList.size(); j++) {
			if (num < numList.get(j).getValue()) {
				num = numList.get(j).getValue();
				element = numList.get(j);
			}
		}
		return element;
	}

	/**
	 * This checks if you can go down and right then checks which one is greater
	 * 
	 * @param map
	 *            Map sent int
	 * @param element
	 *            This is the element that is being compared
	 * @param temp
	 *            This is the array to hold the path
	 * @return the completed path
	 */
	public static ArrayList<Index> pathEasy(int[][] map, Index element, ArrayList<Index> temp) {

		int r = element.getRow();
		int c = element.getCol();
		int value = element.getValue();

		boolean validRow = r < (map.length - 1);

		boolean validCol = c < (map[0].length - 1);

		// only go down
		if (validRow && !validCol) {
			if (value <= map[r + 1][c]) {
				Index newElement = new Index(r + 1, c, map[r + 1][c]);
				temp.add(newElement);
				return pathEasy(map, newElement, temp);
			}
			// only go right
		} else if (!validRow && validCol) {
			if (value <= map[r][c + 1]) {
				Index newElement = new Index(r, c + 1, map[r][c + 1]);
				temp.add(newElement);
				return pathEasy(map, newElement, temp);
			}
			// can go right or down
		} else if (validRow && validCol) {
			int num = Math.max(map[r + 1][c], map[r][c + 1]);
			Index newElement;

			if (num == map[r + 1][c]) {
				newElement = new Index(r + 1, c, map[r + 1][c]);
			} else {
				newElement = new Index(r, c + 1, map[r][c + 1]);
			}

			if (value <= map[r + 1][c]) {

				temp.add(newElement);
				return pathEasy(map, newElement, temp);
			} else if (value <= map[r][c + 1]) {

				temp.add(newElement);
				return pathEasy(map, newElement, temp);
			}
		}
		return temp;
	}

	/**
	 * This is to check if an element has been used already prior
	 * 
	 * @param element
	 *            The Index that is sent in
	 * @param temp
	 *            The array to check the Index against
	 * @return returns true if it is in the temp false if not
	 */
	public static boolean used(Index element, ArrayList<Index> temp) {

		for (int i = 0; i < temp.size(); i++) {
			if (element.getRow() == temp.get(i).getRow() && element.getCol() == temp.get(i).getCol()) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Enter enough commands!!");
			System.exit(0);
		}
		int row = Integer.parseInt(args[0]);
		int col = Integer.parseInt(args[1]);

		int[][] map = new int[row][col];

		int[][] map1 = new int[row][col];

		ArrayList<Index> temp = new ArrayList<Index>();
		ArrayList<Index> temp1 = new ArrayList<Index>();

		fillArray(map);
		fillArray(map1);

		Index element = chooseElement(map);
		Index element1 = chooseElement(map1);

		temp.add(element);

		System.out.println("Easy Array");
		printArray(map);
		System.out.println();
		System.out.println("The selected point is (" + element.getRow() + "," + element.getCol() + ") with a value of "
				+ element.getValue());
		easyArray = pathEasy(map, element, temp);
		System.out.println();
		for (int i = 0; i < easyArray.size(); i++) {
			System.out.println("The row is " + easyArray.get(i).getRow());
			System.out.println("The col is " + easyArray.get(i).getCol());
			System.out.println("The value is " + easyArray.get(i).getValue());
			System.out.println();
		}

		System.out.println("The path size is: " + easyArray.size());
		System.out.println();
		System.out.println();
		System.out.println("------------------------------------------------");
		System.out.println();
		System.out.println();

		System.out.println("Hard Array");
		printArray(map1);
		System.out.println();
		System.out.println("The selected point is (" + element1.getRow() + "," + element1.getCol()
				+ ") with a value of " + element1.getValue());
		System.out.println();

		temp1.add(element1);
		hardArray = pathHard(map1, element1, temp1);

		for (int i = 0; i < hardArray.size(); i++) {
			System.out.println("The row is " + hardArray.get(i).getRow());
			System.out.println("The col is " + hardArray.get(i).getCol());
			System.out.println("The value is " + hardArray.get(i).getValue());
			System.out.println();
		}
		System.out.println("The path size is: " + hardArray.size());
		System.out.println();
	}
}
