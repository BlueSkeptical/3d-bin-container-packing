package com.github.skjolber.packing;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import edu.umd.cs.mtc.MultithreadedTestCase;
import edu.umd.cs.mtc.TestFramework;

class DimensionTestMultithreadedTC extends MultithreadedTestCase {

	void thread1() {
		
		Dimension d = new Dimension(1, 2, 3);
		
		String encode = d.encode();
		
		Dimension decoded = Dimension.decode(encode);
		
		Assert.assertEquals(d.getWidth(), decoded.getWidth());
		Assert.assertEquals(d.getDepth(), decoded.getDepth());
		Assert.assertEquals(d.getHeight(), decoded.getHeight());
	}
	
	void thread2() {
		
		Dimension d = new Dimension(1, 2, 3);
		
		String encode = d.encode();
		
		Dimension decoded = Dimension.decode(encode);
		
		Assert.assertEquals(d.getWidth(), decoded.getWidth());
		Assert.assertEquals(d.getDepth(), decoded.getDepth());
		Assert.assertEquals(d.getHeight(), decoded.getHeight());
	}
	
	@Test
    public void testEncodeDecode() throws Throwable {
    	TestFramework.runManyTimes(new DimensionTestMultithreadedTC(), 100);
    }
}
