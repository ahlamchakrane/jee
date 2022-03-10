package metier;

import org.junit.Assert;
import org.junit.Test;

public class ClaculTest {
	private Calcule calcule;
	@Test
	public void testSomme() {
		calcule= new Calcule();
		double a=6;
		double b=6;
		double expected=12;
		double resulat= calcule.somme(a, b);
		Assert.assertTrue(resulat==expected);
	}
	
}
