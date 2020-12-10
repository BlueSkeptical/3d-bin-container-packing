package com.github.skjolber.packing.test;

import static com.google.common.truth.Truth.assertThat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;

import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;

public class BouwkampCodeParserTestTempusFugit {
	@Rule
	public ConcurrentRule concurrently = new ConcurrentRule();
	@Rule
	public RepeatingRule rule = new RepeatingRule();

	@Test
	@Concurrent(count = 2)
	@Repeating(repetition = 100)
	public void testParser() throws IOException {
		BouwkampCodeParser parser = new BouwkampCodeParser();
		
		List<BouwkampCode> codes = parser.parse(getClass().getResourceAsStream("/simplePerfectSquaredRectangles/o12spsr.bkp"), StandardCharsets.UTF_8);

		assertThat(codes.size()).isEqualTo(67);
		
		for(BouwkampCode code : codes) {
			assertThat(code.getSquare().size()).isEqualTo(12);
		}
	}
}
