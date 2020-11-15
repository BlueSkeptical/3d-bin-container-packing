package com.github.skjolber.packing;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;

import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;

public class BruteForcePackagerBuilderTestTempusFugit {

	@Rule
	public ConcurrentRule concurrently = new ConcurrentRule();
	@Rule
	public RepeatingRule rule = new RepeatingRule();
	
	@Test
	@Concurrent(count = 2)
	@Repeating(repetition = 100)
	public void testBuilder() {
		//System.out.println(Thread.currentThread().getId());
		List<Container> containers = new ArrayList<>();
		Container container = new Container("X", 100, 36, 5, 1000);
		containers.add(container);

		assertNotNull(BruteForcePackager.newBuilder().withContainers(containers).build());
	}
}
