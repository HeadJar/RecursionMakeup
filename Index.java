/**
 * This is a simple class for me to hold a element of the 2d array without
 * breaking apart the row and column
 * It will hold the row, column, and value of the element
 * @author Jarhead
 *
 */
public class Index {

	private int row;
	private int col;
	private int value;

	public Index(int row, int col, int value) {
		this.row = row;
		this.col = col;
		this.value = value;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public int getValue() {
		return value;
	}

	public void setRow(int row) {
		this.row = row;

	}

	public void setCol(int col) {
		this.col = col;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
