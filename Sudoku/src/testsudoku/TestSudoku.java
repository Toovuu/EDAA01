package testsudoku;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sudoku.SudokuModel;

class TestSudoku {
	
	SudokuModel mySudoku;

	@BeforeEach
	void setUp() throws Exception {
		mySudoku = new SudokuModel();
	}

	@AfterEach
	void tearDown() throws Exception {
		mySudoku = null;
	}

	@Test
	void testSolveEmpty() {
		assertTrue("empty not solved although solvable", mySudoku.solve());
	}
	
	@Test
	void testSolveSolvable() {
		mySudoku.set(2, 0, 8);
		mySudoku.set(5, 0, 9);
		mySudoku.set(7, 0, 6);
		mySudoku.set(8, 0, 2);
		
		mySudoku.set(8, 1, 5);
		
		mySudoku.set(0, 2, 1);
		mySudoku.set(2, 2, 2);
		mySudoku.set(3, 2, 5);
		
		mySudoku.set(3, 3, 2);
		mySudoku.set(4, 3, 1);
		mySudoku.set(7, 3, 9);
		
		mySudoku.set(1, 4, 5);
		mySudoku.set(6, 4, 6);
		
		mySudoku.set(0, 5, 6);
		mySudoku.set(7, 5, 2);
		mySudoku.set(8, 5, 8);
		
		mySudoku.set(0, 6, 4);
		mySudoku.set(1, 6, 1);
		mySudoku.set(3, 6, 6);
		mySudoku.set(5, 6, 8);
		
		mySudoku.set(0, 7, 8);
		mySudoku.set(1, 7, 6);
		mySudoku.set(4, 7, 3);
		mySudoku.set(6, 7, 1);
		
		mySudoku.set(6, 8, 4);
		
		assertTrue("not solved although solvable", mySudoku.solve());
	}
	
	@Test
	void testUnsolvable() {
		mySudoku.set(1, 0, 2);
		mySudoku.set(2, 0, 3);
		mySudoku.set(3, 0, 4);
		mySudoku.set(4, 0, 5);
		mySudoku.set(6, 0, 7);
		mySudoku.set(7, 0, 8);
		mySudoku.set(8, 0, 9);

		mySudoku.set(0, 2, 6);
		mySudoku.set(5, 1, 6);

		assertTrue("solved although not solvable", !mySudoku.solve());
	}

	@Test
	void testUnsolvable2() {
		mySudoku.set(1, 3, 6);
		mySudoku.set(2, 4, 6);

		assertTrue("solved although not solvable", !mySudoku.solve());
	}



}
