package InjectionXML;

public class presentation {
	public static void main(String[] args) throws Exception {
		ConfigurationXML configurationXML = new ConfigurationXML("config.xml");
		System.out.println(configurationXML.getClasse().calcule());
	}
}
