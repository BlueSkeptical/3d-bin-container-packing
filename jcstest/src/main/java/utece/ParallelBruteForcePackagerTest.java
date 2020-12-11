package utece;

import com.github.skjolber.packing.*;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.ZZ_Result;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//@JCStressTest
// Outline the outcomes here. The default outcome is provided, you need to remove it:
@Outcome(id = "true, true", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@State
public class ParallelBruteForcePackagerTest {

	private Boolean ifEqualActor1, ifEqualActor2;
	public ParallelBruteForcePackagerTest () {
		this.ifEqualActor1 = true;
		this.ifEqualActor2 = true;
	}

	@Actor
	public void actor1() {
		final ExecutorService pool = Executors.newFixedThreadPool(1);
		List<Container> containers = new ArrayList<>();
		containers.add(new ValidatingContainer("container1", 10, 10, 1, 0));
		BruteForcePackager packager = new ParallelBruteForcePackager(containers, pool, 1, true ,true, 1);
		List<BoxItem> products = new ArrayList<>();
		products.add(new BoxItem(new Box("E", 5, 10, 1, 0), 1));
		products.add(new BoxItem(new Box("F", 5, 10, 1, 0), 1));
		Container fits = packager.pack(products);
		if (fits == null || fits.getLevels().size() != 1) {
			ifEqualActor1 = false;
		}
	}

	@Actor
	public void actor2() {
		final ExecutorService pool = Executors.newFixedThreadPool(1);
		List<Container> containers = new ArrayList<>();
		containers.add(new ValidatingContainer("container1", 10, 10, 1, 0));
		BruteForcePackager packager = new ParallelBruteForcePackager(containers, pool, 1, true ,true, 1);
		List<BoxItem> products = new ArrayList<>();
		products.add(new BoxItem(new Box("E", 5, 10, 1, 0), 1));
		products.add(new BoxItem(new Box("F", 5, 10, 1, 0), 1));
		Container fits = packager.pack(products);
		if (fits == null || fits.getLevels().size() != 1) {
			ifEqualActor2 = false;
		}
	}

	@Arbiter
	public void arbiter(ZZ_Result r) {
		r.r1 = ifEqualActor1;
		r.r2 = ifEqualActor2;
	}
}

class ValidatingContainer extends Container {
	public ValidatingContainer(Container container) {
		super(container);
	}
	public ValidatingContainer(Dimension dimension, int weight) {
		super(dimension, weight);
	}
	public ValidatingContainer(int w, int d, int h, int weight) {
		super(w, d, h, weight);
	}
	public ValidatingContainer(String name, int w, int d, int h, int weight) {
		super(name, w, d, h, weight);
	}

	@Override
	public void add(Placement placement) {
		super.add(placement);
		levels.get(levels.size() - 1).validate();
	}
}
