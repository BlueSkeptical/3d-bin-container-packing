package com.github.skjolber.packing;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;

import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;

public class DimensionTestTempusFugit {
	@Rule
	public ConcurrentRule concurrently = new ConcurrentRule();
	@Rule
	public RepeatingRule rule = new RepeatingRule();

	@Test
	@Concurrent(count = 2)
	@Repeating(repetition = 100)
	public void testEncodeDecode() {
		//System.out.println(Thread.currentThread().getId());
		Dimension d = new Dimension(1, 2, 3);
		
		String encode = d.encode();
		
		Dimension decoded = Dimension.decode(encode);
		
		assertEquals(d.getWidth(), decoded.getWidth());
		assertEquals(d.getDepth(), decoded.getDepth());
		assertEquals(d.getHeight(), decoded.getHeight());
	}

}
