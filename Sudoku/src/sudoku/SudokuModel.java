package sudoku;

public class SudokuModel {
	
	private int[][] matrix;
	
	public SudokuModel() {
		this.matrix = new int[9][9];
	}
	
	public void set(int i, int j, int value) {
		matrix[i][j] = value;
	}
	
	public int get(int i, int j) {
		return matrix[i][j];
	}
	
	public boolean solve() {
		boolean ok;
		try {
			ok = solve(0, 0);
		} catch(IllegalArgumentException e) {
			System.out.println(e.toString());
			ok = false;
		}
		for(int i = 0; i < 9; i++) {
			System.out.print("[");
			for(int j = 0; j < 9; j++) {
				System.out.print(" " + matrix[i][j]);
			}
			System.out.print(" ]" + '\n');
		}
		System.out.println();
		return ok;
	}
	
	private boolean solve(int i, int j) {
		if(matrix[i][j] == 0)	//if not predefined by user
		{
			for(int val = 1; val < 10; val++) {
				if(isPossible(i, j, val)) {
					matrix[i][j] = val;		//mark with value
					if( (i+j == 16) || solve((i + 1) % 9, j + ((i + 1) / 9)) )	//if last square or recursive succesfull
						return true;
					else
						matrix[i][j] = 0;	//set as unmarked as recursion told this value is not possible
				}
			}
		}
		else {	//if predefined by user
			if(isPossible(i, j, matrix[i][j])) {
				if( (i+j == 16) || solve((i + 1) % 9, j + ((i + 1) / 9)) )
					return true;
			}
			else {
				throw new IllegalArgumentException("Illegal values given by user");
			}
		}
		return false;
	}
	
	private boolean isPossible(int i, int j, int value) {
		for(int k = 0; k < 9; k++) {
			if((matrix[i][k] == value) && (k != j))		
				return false;							//value already in column
			if((matrix[k][j] == value) && (k != i))		
				return false;							//value already in row

			//checking correct 3x3 sub-matrix
			if((matrix[(i/3)*3 + (k%3)][(j/3)*3 + (k/3)] == value) &&	//using properties of int-division
					(((i/3)*3 + (k%3) != i) && ((j/3)*3 + (k/3) != j)))
				return false;							//value already in sub-matrix
		}
		return true;
	}

}
