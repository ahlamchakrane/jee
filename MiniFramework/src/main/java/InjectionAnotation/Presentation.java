package InjectionAnotation;

import metier.Imetier;

public class Presentation {
	public static void main(String[] args) throws Exception {
		ConfigurationAnotattion configurationAnotattion = new ConfigurationAnotattion();
		configurationAnotattion.getClasses("dao", "metier");
		Imetier imetier = (Imetier) configurationAnotattion.getInstances().get(Imetier.class);
		System.out.println(imetier.calcule());
	}
}
