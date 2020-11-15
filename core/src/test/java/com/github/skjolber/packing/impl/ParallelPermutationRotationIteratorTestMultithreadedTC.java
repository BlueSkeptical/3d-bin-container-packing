package com.github.skjolber.packing.impl;

import static com.github.skjolber.packing.impl.PermutationRotationIteratorTest.cloneArray;
import static com.github.skjolber.packing.impl.PermutationRotationIteratorTest.firstDiffIndex;
import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.github.skjolber.packing.Box;
import com.github.skjolber.packing.BoxItem;

import edu.umd.cs.mtc.MultithreadedTestCase;
import edu.umd.cs.mtc.TestFramework;

public class ParallelPermutationRotationIteratorTestMultithreadedTC extends MultithreadedTestCase {

	void thread1() {
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

		Assert.assertEquals(iterator.countPermutations(), nthIterator.countPermutations());

		int count = 0;
		do {
			assertThat(nthIterator.getPermutations(0)).isEqualTo(iterator.getPermutations());

			count++;
		} while(nthIterator.nextPermutation(0) != -1 && iterator.nextPermutation() != -1);

		Assert.assertEquals( 5 * 4 * 3 * 2 * 1, count);
		assertThat(nthIterator.nextPermutation(0)).isEqualTo(-1);
	}
	
	void thread2() {
		Box container = new Box(9, 1, 1, 0);

		List<BoxItem> products = new ArrayList<>();

		products.add(new BoxItem(new Box("0", 1, 1, 3, 0), 1));
		products.add(new BoxItem(new Box("1", 1, 1, 3, 0), 1));
		products.add(new BoxItem(new Box("2", 1, 1, 3, 0), 1));
		products.add(new BoxItem(new Box("3", 1, 1, 3, 0), 1));
		products.add(new BoxItem(new Box("4", 1, 1, 3, 0), 1));

		DefaultPermutationRotationIterator iterator = new DefaultPermutationRotationIterator(products, container, true);

		ParallelPermutationRotationIterator nthIterator = new ParallelPermutationRotationIterator(products, container, true, 1);

		Assert.assertEquals(iterator.countPermutations(), nthIterator.countPermutations());

		int count = 0;
		do {
			assertThat(nthIterator.getPermutations(0)).isEqualTo(iterator.getPermutations());

			count++;
		} while(nthIterator.nextPermutation(0) != -1 && iterator.nextPermutation() != -1);

		Assert.assertEquals( 5 * 4 * 3 * 2 * 1, count);
		assertThat(nthIterator.nextPermutation(0)).isEqualTo(-1);
	}
	
	@Test
    public void testPermutationsSingleWorkUnit() throws Throwable {
    	TestFramework.runManyTimes(new ParallelPermutationRotationIteratorTestMultithreadedTC(), 100);
    }

}
