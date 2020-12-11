package utece;

import com.github.skjolber.packing.Space;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.ZZ_Result;

//@JCStressTest
// Outline the outcomes here. The default outcome is provided, you need to remove it:
@Outcome(id = "true, true", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@State
public class SpaceTest {

	private Boolean ifEqualActor1, ifEqualActor2;

	public SpaceTest() {
		this.ifEqualActor1 = true;
		this.ifEqualActor2 = true;
	}

	@Actor
	public void actor1() {
		if (!new Space(1, 2, 3, 4, 5, 6).equals(new Space(1, 2, 3, 4, 5, 6))) {
			ifEqualActor1 = true;
		}
	}

	@Actor
	public void actor2() {
		if (!new Space(1, 2, 3, 4, 5, 6).equals(new Space(1, 2, 3, 4, 5, 6))) {
			ifEqualActor2 = true;
		}
	}

	@Arbiter
	public void arbiter(ZZ_Result r) {
		r.r1 = ifEqualActor1;
		r.r2 = ifEqualActor2;
	}
}
