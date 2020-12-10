package com.github.skjolber.packing.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;

import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;

public class BinarySearchIteratorTestTempusFugit {
	@Rule
	public ConcurrentRule concurrently = new ConcurrentRule();
	@Rule
	public RepeatingRule rule = new RepeatingRule();

	@Test
	@Concurrent(count = 2)
	@Repeating(repetition = 100)
	public void testHigher() {
		
		BinarySearchIterator iterator = new BinarySearchIterator(0, 15);
		
		int mid = -1;
		
		while(iterator.hasNext()) {
			
			int next = iterator.next();
			
			assertTrue(next > mid);
			
			mid = next;
			
			iterator.higher();
		}
		
		assertEquals(15, mid);
	}

}
