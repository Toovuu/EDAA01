package testbst;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bst.BinarySearchTree;

class TestBinarySearchTree {
	
	BinarySearchTree<Integer> myBST;

	@BeforeEach
	void setUp() throws Exception {
		myBST = new BinarySearchTree<Integer>();
	}

	@AfterEach
	void tearDown() throws Exception {
		myBST = null;
	}

	@Test
	void testAdd() {
		assertTrue("new object not added", myBST.add(1));
		assertTrue("new object not added", myBST.add(3));
		assertTrue("new object not added", myBST.add(-1));
		assertTrue("duplicate added", !myBST.add(3));
		assertTrue("new object not added", myBST.add(0));
	}

	@Test
	void testHeight() {
		assertEquals("height not zero in empty tree", 0, myBST.height());
		myBST.add(0);
		assertEquals("height should be one", 1, myBST.height());
		myBST.add(1);
		assertEquals("height should be two", 2, myBST.height());
		myBST.add(-1);
		assertEquals("height should be two", 2, myBST.height());
		myBST.add(3);
		assertEquals("height should be three", 3, myBST.height());
	}

	@Test
	void testSize() {
		assertEquals("size not zero in empty tree", 0, myBST.size());
		myBST.add(0);
		assertEquals("size should be one", 1, myBST.size());
		myBST.add(1);
		assertEquals("size should be two", 2, myBST.size());
		myBST.add(-1);
		assertEquals("size should be two", 3, myBST.size());
	}

}
