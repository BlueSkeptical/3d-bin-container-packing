package com.github.skjolber.packing.test;

import static com.google.common.truth.Truth.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import edu.umd.cs.mtc.MultithreadedTestCase;
import edu.umd.cs.mtc.TestFramework;

public class BouwkampCodeDirectoryTestMultithreadedTC extends MultithreadedTestCase {

	public void thread1() throws IOException {
		//System.out.println(Thread.currentThread().getId());
		BouwkampCodeDirectory parser = BouwkampCodeDirectory.getInstance();
		
		assertThat(parser.getSimpleImperfectSquaredRectangles().size()).isEqualTo(3);
		assertThat(parser.getSimpleImperfectSquaredSquares().size()).isEqualTo(3);
		assertThat(parser.getSimplePerfectSquaredRectangles().size()).isEqualTo(5);
		
		assertThat(parser.codesForCount(13).size()).isEqualTo(3);
	}
	
	public void thread2() throws IOException {
		BouwkampCodeDirectory parser = BouwkampCodeDirectory.getInstance();
		
		assertThat(parser.getSimpleImperfectSquaredRectangles().size()).isEqualTo(3);
		assertThat(parser.getSimpleImperfectSquaredSquares().size()).isEqualTo(3);
		assertThat(parser.getSimplePerfectSquaredRectangles().size()).isEqualTo(5);
		
		assertThat(parser.codesForCount(13).size()).isEqualTo(3);
	}
	
	@Test
    public void testParser() throws Throwable {
    	TestFramework.runManyTimes(new BouwkampCodeDirectoryTestMultithreadedTC(), 100);
    }
}
