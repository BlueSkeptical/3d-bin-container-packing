package utece;

import com.github.skjolber.packing.test.BouwkampCode;
import com.github.skjolber.packing.test.BouwkampCodeParser;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.ZZ_Result;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

//@JCStressTest
// Outline the outcomes here. The default outcome is provided, you need to remove it:
@Outcome(id = "true, true", expect = Expect.ACCEPTABLE, desc = "Default outcome.")
@State
public class BouwkampCodeParserTest {

	private Boolean ifEqualActor1, ifEqualActor2;

	public BouwkampCodeParserTest () {
		this.ifEqualActor1 = true;
		this.ifEqualActor2 = true;
	}

	@Actor
	public void actor1(){
		BouwkampCodeParser parser = new BouwkampCodeParser();
		List<BouwkampCode> codes = null;
		try {
			 codes = parser.parse(getClass().getResourceAsStream("/simplePerfectSquaredRectangles/o12spsr.bkp"), StandardCharsets.UTF_8);
		} catch (IOException i) {
			ifEqualActor1 = false;
		}
		if (codes.size() != 67) {
			ifEqualActor1 = false;
		}
		for(BouwkampCode code : codes) {
			if (code.getSquare().size() != 12) {
				ifEqualActor1 = false;
			}
		}
	}

	@Actor
	public void actor2() {
		BouwkampCodeParser parser = new BouwkampCodeParser();
		List<BouwkampCode> codes = null;
		try {
			 codes = parser.parse(getClass().getResourceAsStream("/simplePerfectSquaredRectangles/o12spsr.bkp"), StandardCharsets.UTF_8);
		} catch (IOException i) {
			ifEqualActor2 = false;
		}
		if (codes.size() != 67) {
			ifEqualActor2 = false;
		}
		for(BouwkampCode code : codes) {
			if (code.getSquare().size() != 12) {
				ifEqualActor2 = false;
			}
		}
	}

	@Arbiter
	public void arbiter(ZZ_Result r) {
		r.r1 = ifEqualActor1;
		r.r2 = ifEqualActor2;
	}
}
