package com.github.skjolber.packing.test;

import static com.google.common.truth.Truth.assertThat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.umd.cs.mtc.MultithreadedTestCase;
import edu.umd.cs.mtc.TestFramework;

public class BouwkampCodeParserTestMultithreadedTC extends MultithreadedTestCase {

	public void thread1() throws IOException {
		BouwkampCodeParser parser = new BouwkampCodeParser();
		
		List<BouwkampCode> codes = parser.parse(getClass().getResourceAsStream("/simplePerfectSquaredRectangles/o12spsr.bkp"), StandardCharsets.UTF_8);

		assertThat(codes.size()).isEqualTo(67);
		
		for(BouwkampCode code : codes) {
			assertThat(code.getSquare().size()).isEqualTo(12);
		}
	}
	
	public void thread2() throws IOException {
		BouwkampCodeParser parser = new BouwkampCodeParser();
		
		List<BouwkampCode> codes = parser.parse(getClass().getResourceAsStream("/simplePerfectSquaredRectangles/o12spsr.bkp"), StandardCharsets.UTF_8);

		assertThat(codes.size()).isEqualTo(67);
		
		for(BouwkampCode code : codes) {
			assertThat(code.getSquare().size()).isEqualTo(12);
		}
	}
	
	@Test
    public void testParser() throws Throwable {
    	TestFramework.runManyTimes(new BouwkampCodeParserTestMultithreadedTC(), 100);
    }
}
