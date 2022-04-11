package presentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

import dao.IDao;
import metier.IMetier;

public class Presentation2 {
	public static void main(String[] args) throws FileNotFoundException,
												  ClassNotFoundException, 
												  InstantiationException, //pas de constructeur par defaut
												  IllegalAccessException  // unc constructeur privé
, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException
												  // on peut faire directement Throws Exception
	{
		//instanciation dynamique 
		Scanner scanner = new Scanner(new File("config.txt")); 
		String daoClassName= scanner.nextLine(); //savoir le nom de la calsse
		Class cDao= Class.forName(daoClassName); // je deamnde de charger une calsse dans la memoier
		
		IDao dao=(IDao) cDao.newInstance(); //cree un objet metier qui peut etre diaImp ou diaoimpl2... dans on fait un cast
		//tous ca = new IDao()
	    //	System.out.println(dao.getData());
		// si je veut utiliser les capteur 
		//aller au ficheir config et à la 1ere ligne : ext.DaoImpl2
		
		//creer un objet metier
		String metierClassName = scanner.nextLine();
		Class cMetier= Class.forName(metierClassName);
		IMetier metier= (IMetier) cMetier.newInstance(); //cree un objet metier
		//injection
		
		Method method= cMetier.getMethod("setDao", IDao.class); // je cherche une fonction setDao qui a un seul param de type IDao , je peut chercher plus de params
		method.invoke(metier, dao); //metier.setDao(dao)
		System.out.println("Resultat=>"+metier.calcule());
	}
}
