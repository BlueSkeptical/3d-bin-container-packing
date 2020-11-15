package com.github.skjolber.packing.test;

import static com.google.common.truth.Truth.assertThat;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;

import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;

public class BouwkampCodeDirectoryTestTempusFugit {
	@Rule
	public ConcurrentRule concurrently = new ConcurrentRule();
	@Rule
	public RepeatingRule rule = new RepeatingRule();

	@Test
	@Concurrent(count = 2)
	@Repeating(repetition = 100)
	public void testParser() throws IOException {
		//System.out.println(Thread.currentThread().getId());
		BouwkampCodeDirectory parser = BouwkampCodeDirectory.getInstance();
		
		assertThat(parser.getSimpleImperfectSquaredRectangles().size()).isEqualTo(3);
		assertThat(parser.getSimpleImperfectSquaredSquares().size()).isEqualTo(3);
		assertThat(parser.getSimplePerfectSquaredRectangles().size()).isEqualTo(5);
		
		assertThat(parser.codesForCount(13).size()).isEqualTo(3);
	}
}
