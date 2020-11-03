package testqueue;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import queue_singlelinkedlist.FifoQueue;

public class TestAppendFifoQueue {
	
	private FifoQueue<Integer> myIntQueue1;
	private FifoQueue<Integer> myIntQueue2;
	
	@Before
	public void setUp() throws Exception {
		myIntQueue1 = new FifoQueue<Integer>();
		myIntQueue2 = new FifoQueue<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		myIntQueue1 = null;
		myIntQueue2 = null;
	}

	@Test
	public void testAppendTwoEmpty() {
		myIntQueue1.append(myIntQueue2);
		assertEquals("the stacked queue is not empty", 0, myIntQueue1.size());
		assertEquals("the stacked queue's last is not null", null, myIntQueue1.peek());
		assertEquals("the second queue is not empty", 0, myIntQueue2.size());
		assertEquals("the second queue's last is not null", null, myIntQueue2.peek());
		
	}
	
	@Test
	public void testAppendFirstEmpty() {
		ArrayList<Integer> l2 = new ArrayList<Integer>();
		ArrayList<Integer> lc = new ArrayList<Integer>();	//concated
		myIntQueue2.offer(1);
		myIntQueue2.offer(2);
		myIntQueue2.offer(3);
		
		for(int i = 1; i < 4; i++)
			l2.add(i);
		
		myIntQueue1.append(myIntQueue2);
		
		for(Integer e : myIntQueue1)
			lc.add(e);
		
		assertEquals("the stacked queue is not equal to the second queue", lc, l2);
		assertEquals("the second queue is not empty", 0, myIntQueue2.size());
		assertEquals("the second queue's last is not null", null, myIntQueue2.peek());
	}
	
	@Test
	public void testAppendSecondEmpty() {
		ArrayList<Integer> l1 = new ArrayList<Integer>();
		ArrayList<Integer> lc = new ArrayList<Integer>();	//concated
		myIntQueue1.offer(1);
		myIntQueue1.offer(2);
		myIntQueue1.offer(3);
		
		for(int i = 1; i < 4; i++)
			l1.add(i);
		
		myIntQueue1.append(myIntQueue2);
		
		for(Integer e : myIntQueue1)
			lc.add(e);
		
		assertEquals("the stacked queue is not equal to the first queue", lc, l1);
		assertEquals("the second queue is not empty", 0, myIntQueue2.size());
		assertEquals("the second queue's last is not null", null, myIntQueue2.peek());
	}

	@Test
	public void testAppend() {
		ArrayList<Integer> lc = new ArrayList<Integer>();	//concated
		
		myIntQueue1.offer(1);
		myIntQueue1.offer(2);
		myIntQueue1.offer(3);
		myIntQueue2.offer(4);
		myIntQueue2.offer(5);
		myIntQueue2.offer(6);
		
		myIntQueue1.append(myIntQueue2);
		for(Integer e : myIntQueue1)
			lc.add(e);
		
		ArrayList<Integer> lTrue = new ArrayList<Integer>();
		for(int i = 1; i < 7; i++)
			lTrue.add(i);

		assertEquals("the stacked queue is not correct", lTrue, lc);
		assertEquals("the second queue is not empty", 0, myIntQueue2.size());
		assertEquals("the second queue's last is not null", null, myIntQueue2.peek());
	}
	
	@Test
	public void testAppendSelf() {
		myIntQueue1.offer(1);
		myIntQueue1.offer(2);
		myIntQueue1.offer(3);
		try {
			myIntQueue1.append(myIntQueue1);
			fail("Should raise IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// successful test
		}
	}
	

}
