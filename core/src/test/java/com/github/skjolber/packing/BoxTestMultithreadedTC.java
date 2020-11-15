package com.github.skjolber.packing;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import edu.umd.cs.mtc.MultithreadedTestCase;
import edu.umd.cs.mtc.TestFramework;

class BoxTestMultithreadedTC extends MultithreadedTestCase {

	void thread1() {
		//System.out.println(Thread.currentThread().getId());
		Box box = new Box(350, 150, 400, 0);
		Assert.assertTrue(box.canHold3D(350, 50, 400));
		Assert.assertTrue(box.canHold3D(50, 400, 350));
		Assert.assertTrue(box.canHold3D(400, 350, 50));
		
		Assert.assertTrue(box.canHold3D(50, 350, 400));
		Assert.assertTrue(box.canHold3D(400, 50, 350));
		Assert.assertTrue(box.canHold3D(350, 400, 50));
	}
	
	void thread2() {
		
		Box box = new Box(350, 150, 400, 0);
		Assert.assertTrue(box.canHold3D(350, 50, 400));
		Assert.assertTrue(box.canHold3D(50, 400, 350));
		Assert.assertTrue(box.canHold3D(400, 350, 50));
		
		Assert.assertTrue(box.canHold3D(50, 350, 400));
		Assert.assertTrue(box.canHold3D(400, 50, 350));
		Assert.assertTrue(box.canHold3D(350, 400, 50));
	}

	@Test
    public void testCanHold() throws Throwable {
    	TestFramework.runManyTimes(new BoxTestMultithreadedTC(), 100);
    }
	
}
