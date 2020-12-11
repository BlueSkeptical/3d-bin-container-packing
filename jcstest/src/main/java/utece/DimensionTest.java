package utece;
import com.github.skjolber.packing.Dimension;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.ZZ_Result;
@JCStressTest
// Outline the outcomes here. The default outcome is provided, you need to remove it:
@Outcome(id = "true, true", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@State
public class DimensionTest {

	private Boolean ifEqualActor1, ifEqualActor2;

	public DimensionTest () {
		this.ifEqualActor1 = true;
		this.ifEqualActor2 = true;
	}

	@Actor
	public void actor1() {
		Dimension d = new Dimension(1, 2, 3);
		String encode = d.encode();
		Dimension decoded = Dimension.decode(encode);
		if (d.getWidth() != decoded.getWidth() || d.getDepth() != decoded.getDepth() || d.getHeight() != decoded.getHeight()) {
			ifEqualActor1 = false;
		}
	}

	@Actor
	public void actor2() {
		Dimension d = new Dimension(1, 2, 3);
		String encode = d.encode();
		Dimension decoded = Dimension.decode(encode);
		if (d.getWidth() != decoded.getWidth() || d.getDepth() != decoded.getDepth() || d.getHeight() != decoded.getHeight()) {
			ifEqualActor2 = false;
		}
	}

	@Arbiter
	public void arbiter(ZZ_Result r) {
		r.r1 = ifEqualActor1;
		r.r2 = ifEqualActor2;
	}
}
