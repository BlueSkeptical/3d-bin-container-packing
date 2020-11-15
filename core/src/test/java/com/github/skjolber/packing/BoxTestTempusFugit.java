package com.github.skjolber.packing;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;

//has to be public class public method
public class BoxTestTempusFugit {
	@Rule
	public ConcurrentRule concurrently = new ConcurrentRule();
	@Rule
	public RepeatingRule rule = new RepeatingRule();

	@Test
	@Concurrent(count = 2)
	@Repeating(repetition = 100)
	public void testCanHold() {
		//System.out.println(Thread.currentThread().getId());
		Box box = new Box(350, 150, 400, 0);
		Assert.assertTrue(box.canHold3D(350, 50, 400));
		Assert.assertTrue(box.canHold3D(50, 400, 350));
		Assert.assertTrue(box.canHold3D(400, 350, 50));
		
		Assert.assertTrue(box.canHold3D(50, 350, 400));
		Assert.assertTrue(box.canHold3D(400, 50, 350));
		Assert.assertTrue(box.canHold3D(350, 400, 50));
	}
	
}
