package com.github.skjolber.packing.impl;

import static com.github.skjolber.packing.impl.PermutationRotationIteratorTest.cloneArray;
import static com.github.skjolber.packing.impl.PermutationRotationIteratorTest.firstDiffIndex;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;

import com.github.skjolber.packing.Box;
import com.github.skjolber.packing.BoxItem;
import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;

public class ParallelPermutationRotationIteratorTestTempusFugit {
	@Rule
	public ConcurrentRule concurrently = new ConcurrentRule();
	@Rule
	public RepeatingRule rule = new RepeatingRule();

	@Test
	@Concurrent(count = 2)
	@Repeating(repetition = 100)
	public void testPermutationsSingleWorkUnit() {
		//System.out.println(Thread.currentThread().getId());
		Box container = new Box(9, 1, 1, 0);

		List<BoxItem> products = new ArrayList<>();

		products.add(new BoxItem(new Box("0", 1, 1, 3, 0), 1));
		products.add(new BoxItem(new Box("1", 1, 1, 3, 0), 1));
		products.add(new BoxItem(new Box("2", 1, 1, 3, 0), 1));
		products.add(new BoxItem(new Box("3", 1, 1, 3, 0), 1));
		products.add(new BoxItem(new Box("4", 1, 1, 3, 0), 1));

		DefaultPermutationRotationIterator iterator = new DefaultPermutationRotationIterator(products, container, true);

		ParallelPermutationRotationIterator nthIterator = new ParallelPermutationRotationIterator(products, container, true, 1);

		assertEquals(iterator.countPermutations(), nthIterator.countPermutations());

		int count = 0;
		do {
			assertThat(nthIterator.getPermutations(0)).isEqualTo(iterator.getPermutations());

			count++;
		} while(nthIterator.nextPermutation(0) != -1 && iterator.nextPermutation() != -1);

		assertEquals( 5 * 4 * 3 * 2 * 1, count);
		assertThat(nthIterator.nextPermutation(0)).isEqualTo(-1);
	}

}
