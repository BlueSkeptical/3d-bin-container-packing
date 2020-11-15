package com.github.skjolber.packing;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Rule;
import org.junit.Test;

import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;


public class LevelTestTempusFugit {
	@Rule
	public ConcurrentRule concurrently = new ConcurrentRule();
	@Rule
	public RepeatingRule rule = new RepeatingRule();

	@Test
	@Concurrent(count = 2)
	@Repeating(repetition = 100)
	public void testValidator() {
		//System.out.println(Thread.currentThread().getId());
		Level level = new Level();
		Placement a = new Placement(new Space(1, 1, 1, 0, 0, 0), new Box(1, 1, 1, 0));
		level.add(a);

		Placement b = new Placement(new Space(1, 1, 1, 1, 0, 0), new Box(1, 1, 1, 0));
		level.add(b);

		level.validate();
	}
}
