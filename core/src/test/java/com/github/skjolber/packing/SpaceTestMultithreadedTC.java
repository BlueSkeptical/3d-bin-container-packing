package com.github.skjolber.packing;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import edu.umd.cs.mtc.MultithreadedTestCase;
import edu.umd.cs.mtc.TestFramework;

class SpaceTestMultithreadedTC extends MultithreadedTestCase {

	void thread1() {
		Assert.assertEquals(new Space(1, 2, 3, 4, 5, 6), new Space(1, 2, 3, 4, 5, 6));
	}
	
	void thread2() {
		Assert.assertEquals(new Space(1, 2, 3, 4, 5, 6), new Space(1, 2, 3, 4, 5, 6));
	}
	
	@Test
    public void testEquals() throws Throwable {
    	TestFramework.runManyTimes(new SpaceTestMultithreadedTC(), 100);
    }
}
