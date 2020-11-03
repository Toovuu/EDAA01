package bst;

import java.util.TreeSet;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
    int size;
    
	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {
		
	}
	
	public static void main(String[] args) {
		
		BinarySearchTree<Integer> bin = new BinarySearchTree<Integer>();
		bin.add(0);
		bin.add(-7);
		bin.add(-4);
		bin.add(-1);
		bin.add(-3);
		bin.add(-2);
		bin.add(-5);
		bin.add(3);

		bin.rebuild();

		BSTVisualizer vis = new BSTVisualizer("BinarySearchTree", 500, 500);
		vis.drawTree(bin);
		bin.printTree();
		
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if(root == null) {
			root = new BinaryNode<E>(x);
			size = 1;
			return true;
		}
		
		return add(root, x);
	}
	
	//recursive method
	private boolean add(BinaryNode<E> node, E x) {
		int c = x.compareTo(node.element);
		if(c == 0)
			return false;
		else if(c < 0) {
			if(node.left == null) {
				node.left = new BinaryNode<E>(x);
				size++;
				return true;
			}
			else
				return add(node.left, x);
		}
		else {
			if(node.right == null) {
				node.right = new BinaryNode<E>(x);
				size++;
				return true;
			}
			else
				return add(node.right, x);
		}
	}
	
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}
	
	//recursive method.
	private int height(BinaryNode<E> node) {
		if(node == null)
			return 0;
		
		return Math.max(height(node.left), height(node.right)) + 1;
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		printTree(root);
	}
	
	private void printTree(BinaryNode<E> node) {
		if(node != null) {
			printTree(node.left);
			System.out.print(node.element + " ");
			printTree(node.right);
		}
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		E[] a = (E[]) new Comparable[size];
		toArray(root, a, 0);
		root = buildTree(a, 0, size-1);
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index].
	 * Returns the index of the last inserted element + 1 (the first empty
	 * position in a).
	 */
	private int toArray(BinaryNode<E> n, E[] a, int index) {
		if(n != null) {
			int i = toArray(n.left, a, index);	//go through left sub-tree

			a[i] = n.element;	//add node element
			i++;				//increse temp index since new element added
			
			i = toArray(n.right, a, i);			//go through right sub-tree
			
			return i;
			
		}
		else
			return index;	//if node is null
	}
	
	/*
	 * Builds a complete tree from the elements a[first]..a[last].
	 * Elements in the array a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {
		if(first > last)
			return null;
		else {
			int mid = first + ((last - first) / 2);
			BinaryNode<E> root = new BinaryNode<E>(a[mid]);
			root.left = buildTree(a, first, mid-1);
			root.right = buildTree(a, mid+1, last);
			return root;
		}
		
	}
	


	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
	
}
