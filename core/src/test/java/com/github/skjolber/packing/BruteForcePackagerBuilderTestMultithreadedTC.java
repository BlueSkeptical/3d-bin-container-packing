package com.github.skjolber.packing;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import edu.umd.cs.mtc.MultithreadedTestCase;
import edu.umd.cs.mtc.TestFramework;

public class BruteForcePackagerBuilderTestMultithreadedTC extends MultithreadedTestCase {

	public void thread1() {
		//System.out.println(Thread.currentThread().getId());
		List<Container> containers = new ArrayList<>();
		Container container = new Container("X", 100, 36, 5, 1000);
		containers.add(container);

		Assert.assertNotNull(BruteForcePackager.newBuilder().withContainers(containers).build());
	}
	
	public void thread2() {
		List<Container> containers = new ArrayList<>();
		Container container = new Container("X", 100, 36, 5, 1000);
		containers.add(container);

		Assert.assertNotNull(BruteForcePackager.newBuilder().withContainers(containers).build());
	}
	
	@Test
    public void testBuilder() throws Throwable {
    	TestFramework.runManyTimes(new BruteForcePackagerBuilderTestMultithreadedTC(), 100);
    }
}
