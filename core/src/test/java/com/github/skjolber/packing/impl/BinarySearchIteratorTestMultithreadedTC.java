package com.github.skjolber.packing.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import edu.umd.cs.mtc.MultithreadedTestCase;
import edu.umd.cs.mtc.TestFramework;

class BinarySearchIteratorTestMultithreadedTC extends MultithreadedTestCase {

	void thread1() {
		
		BinarySearchIterator iterator = new BinarySearchIterator(0, 15);
		
		int mid = -1;
		
		while(iterator.hasNext()) {
			
			int next = iterator.next();
			
			Assert.assertTrue(next > mid);
			
			mid = next;
			
			iterator.higher();
		}
		
		Assert.assertEquals(15, mid);
	}
	
	void thread2() {
		
		BinarySearchIterator iterator = new BinarySearchIterator(0, 15);
		
		int mid = -1;
		
		while(iterator.hasNext()) {
			
			int next = iterator.next();
			
			Assert.assertTrue(next > mid);
			
			mid = next;
			
			iterator.higher();
		}
		
		Assert.assertEquals(15, mid);
	}
	
	@Test
    public void testHigher() throws Throwable {
    	TestFramework.runManyTimes(new BinarySearchIteratorTestMultithreadedTC(), 100);
    }
	
}
