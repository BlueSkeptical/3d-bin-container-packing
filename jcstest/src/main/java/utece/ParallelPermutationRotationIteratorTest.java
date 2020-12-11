/*
 * Copyright (c) 2017, Red Hat Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
package utece;
import com.github.skjolber.packing.*;
import com.github.skjolber.packing.impl.DefaultPermutationRotationIterator;
import com.github.skjolber.packing.impl.ParallelPermutationRotationIterator;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.ZZ_Result;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//@JCStressTest
@Outcome(id = "true, true", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@State
public class ParallelPermutationRotationIteratorTest {

	private Boolean ifEqualActor1, ifEqualActor2;

	public ParallelPermutationRotationIteratorTest() {
		this.ifEqualActor1 = true;
		this.ifEqualActor2 = true;
	}

	@Actor
	public void actor1() {
		Box container = new Box(9, 1, 1, 0);

		List<BoxItem> products = new ArrayList<>();

		products.add(new BoxItem(new Box("0", 1, 1, 3, 0), 1));
		products.add(new BoxItem(new Box("1", 1, 1, 3, 0), 1));
		products.add(new BoxItem(new Box("2", 1, 1, 3, 0), 1));
		products.add(new BoxItem(new Box("3", 1, 1, 3, 0), 1));
		products.add(new BoxItem(new Box("4", 1, 1, 3, 0), 1));

		DefaultPermutationRotationIterator iterator = new DefaultPermutationRotationIterator(products, container, true);

		ParallelPermutationRotationIterator nthIterator = new ParallelPermutationRotationIterator(products, container, true, 1);
		if (iterator.countPermutations() != nthIterator.countPermutations()) {
			this.ifEqualActor1 = false;
		}
		int count = 0;
		do {
			if (!Arrays.equals(nthIterator.getPermutations(0), iterator.getPermutations())) {
				this.ifEqualActor1 = false;
			}
			count++;
		} while(nthIterator.nextPermutation(0) != -1 && iterator.nextPermutation() != -1);
		if (count != 5 * 4 * 3 * 2 * 1) {
			this.ifEqualActor1 = false;
		}
		if (nthIterator.nextPermutation(0) != -1) {
			this.ifEqualActor1 = false;
		}
	}

	@Actor
	public void actor2() {
				Box container = new Box(9, 1, 1, 0);

		List<BoxItem> products = new ArrayList<>();

		products.add(new BoxItem(new Box("0", 1, 1, 3, 0), 1));
		products.add(new BoxItem(new Box("1", 1, 1, 3, 0), 1));
		products.add(new BoxItem(new Box("2", 1, 1, 3, 0), 1));
		products.add(new BoxItem(new Box("3", 1, 1, 3, 0), 1));
		products.add(new BoxItem(new Box("4", 1, 1, 3, 0), 1));

		DefaultPermutationRotationIterator iterator = new DefaultPermutationRotationIterator(products, container, true);

		ParallelPermutationRotationIterator nthIterator = new ParallelPermutationRotationIterator(products, container, true, 1);
		if (iterator.countPermutations() != nthIterator.countPermutations()) {
			this.ifEqualActor2 = false;
		}
		int count = 0;
		do {
			if (!Arrays.equals(nthIterator.getPermutations(0), iterator.getPermutations())) {
				this.ifEqualActor2 = false;
			}
			count++;
		} while(nthIterator.nextPermutation(0) != -1 && iterator.nextPermutation() != -1);
		if (count != 5 * 4 * 3 * 2 * 1) {
			this.ifEqualActor2 = false;
		}
		if (nthIterator.nextPermutation(0) != -1) {
			this.ifEqualActor2 = false;
		}
	}

	@Arbiter
	public void arbiter(ZZ_Result r) {
		r.r1 = ifEqualActor1;
		r.r2 = ifEqualActor2;
	}
}