package com.github.skjolber.packing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Assert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import edu.umd.cs.mtc.MultithreadedTestCase;
import edu.umd.cs.mtc.TestFramework;

public class ParallelBruteForcePackagerTestMultithreadedTC extends MultithreadedTestCase  {

	void thread1() {

		final ExecutorService pool = Executors.newFixedThreadPool(1);
		
		List<Container> containers = new ArrayList<>();
		containers.add(new ValidatingContainer("container1", 10, 10, 1, 0));
		BruteForcePackager packager = new ParallelBruteForcePackager(containers, pool, 1, true ,true, 1);

		List<BoxItem> products = new ArrayList<>();

		products.add(new BoxItem(new Box("E", 5, 10, 1, 0), 1));
		products.add(new BoxItem(new Box("F", 5, 10, 1, 0), 1));

		Container fits = packager.pack(products);
		Assert.assertNotNull(fits);
		Assert.assertEquals(fits.getLevels().size(), 1);
	}
	
	void thread2() {

		final ExecutorService pool = Executors.newFixedThreadPool(1);
		
		List<Container> containers = new ArrayList<>();
		containers.add(new ValidatingContainer("container1", 10, 10, 1, 0));
		BruteForcePackager packager = new ParallelBruteForcePackager(containers, pool, 1, true ,true, 1);

		List<BoxItem> products = new ArrayList<>();

		products.add(new BoxItem(new Box("E", 5, 10, 1, 0), 1));
		products.add(new BoxItem(new Box("F", 5, 10, 1, 0), 1));

		Container fits = packager.pack(products);
		Assert.assertNotNull(fits);
		Assert.assertEquals(fits.getLevels().size(), 1);
	}
	
	@Test
    public void testStackingRectanglesOnSquare() throws Throwable {
    	TestFramework.runManyTimes(new ParallelBruteForcePackagerTestMultithreadedTC(), 100);
    }
	
}
