package presentation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import metier.IMetier;

public class PresentationSpringAnnotations {
	public static void main(String[] args) {
		//si toutes les pack commence par ma on mit entre "" que ma 
		//dao et metier sont les nom des package qu'il faut analyser
		ApplicationContext context = new AnnotationConfigApplicationContext("dao","metier");
		IMetier metier = context.getBean(IMetier.class); //demander un bean qui imlemente l'interfaec IMetier
		System.out.println(metier.calcule());
		
	}
}
