package com.github.skjolber.packing;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.umd.cs.mtc.MultithreadedTestCase;
import edu.umd.cs.mtc.TestFramework;


class LevelTestMultithreadedTC extends MultithreadedTestCase {

	void thread1() {
		//System.out.println(Thread.currentThread().getId());
		Level level = new Level();
		Placement a = new Placement(new Space(1, 1, 1, 0, 0, 0), new Box(1, 1, 1, 0));
		level.add(a);

		Placement b = new Placement(new Space(1, 1, 1, 1, 0, 0), new Box(1, 1, 1, 0));
		level.add(b);

		level.validate();
	}
	
	void thread2() {
		Level level = new Level();
		Placement a = new Placement(new Space(1, 1, 1, 0, 0, 0), new Box(1, 1, 1, 0));
		level.add(a);

		Placement b = new Placement(new Space(1, 1, 1, 1, 0, 0), new Box(1, 1, 1, 0));
		level.add(b);

		level.validate();
	}
	
	@Test
    public void testValidator() throws Throwable {
    	TestFramework.runManyTimes(new LevelTestMultithreadedTC(), 100);
    }
}
