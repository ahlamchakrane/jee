package presentation;

import dao.DaoImpl;
import ext.DaoImpl2;
import metier.MetierImpl;

public class Presentation {
	public static void main(String[] args) {
//		injection des dependances par instanciation statique => new => couplage fort  => vrai prob de maintenance 
		
//		DaoImpl dao = new DaoImpl(); 
//		MetierImpl metier = new MetierImpl(); 
//		metier.setDao(dao);
//		System.out.println(metier.calcule());
		
		DaoImpl2 dao = new DaoImpl2(); //couplage fort 
		//MetierImpl metier = new MetierImpl(dao); //couplage fort et injection par constructeur
		//metier.setDao(dao);
		//System.out.println(metier.calcule());
	}
}
