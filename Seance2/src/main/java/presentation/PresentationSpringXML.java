package presentation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import metier.IMetier;

public class PresentationSpringXML {
	public static void main(String[] args) {
		ApplicationContext context= 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		IMetier metier= (IMetier) context.getBean("metier"); //donne moi un bean qui s'appel metier
		System.out.println("Resultat=>"+metier.calcule());
		
	}
}
