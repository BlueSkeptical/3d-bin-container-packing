package utece;
import com.github.skjolber.packing.impl.BinarySearchIterator;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.ZZ_Result;

//@JCStressTest
// Outline the outcomes here. The default outcome is provided, you need to remove it:
@Outcome(id = "true, true", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@State
public class BinarySearchIteratorTest {
	private Boolean ifEqualActor1, ifEqualActor2;
	public BinarySearchIteratorTest () {
		this.ifEqualActor1 = true;
		this.ifEqualActor2 = true;
	}

	@Actor
	public void actor1() {
		BinarySearchIterator iterator = new BinarySearchIterator(0, 15);
		int mid = -1;
		while(iterator.hasNext()) {
			int next = iterator.next();
			if (next <= mid) {
				ifEqualActor1 = false;
			}
			mid = next;
			iterator.higher();
		}
		if (mid != 15) {
			ifEqualActor1 = false;
		}
	}

	@Actor
	public void actor2() {
		BinarySearchIterator iterator = new BinarySearchIterator(0, 15);
		int mid = -1;
		while(iterator.hasNext()) {
			int next = iterator.next();
			if (next <= mid) {
				ifEqualActor2 = false;
			}
			mid = next;
			iterator.higher();
		}
		if (mid != 15) {
			ifEqualActor2 = false;
		}
	}

	@Arbiter
	public void arbiter(ZZ_Result r) {
		r.r1 = ifEqualActor1;
		r.r2 = ifEqualActor2;
	}
}