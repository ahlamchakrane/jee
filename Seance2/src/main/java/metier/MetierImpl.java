package metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import dao.IDao;
@Component("metier")
public class MetierImpl implements IMetier {
	
	@Autowired //au mement ou spring vas instancier la classe metier implementation il cherche un objet de type IDaoet il vas l'injecter dans la variable dao
	@Qualifier("dao") //injecter une instance precise lorsque on a plusieurs
	private IDao dao; //couplage faible pas de new ( couplage fort)
	
	public IDao getDao() {
		return dao;
	}
	
	
//	public MetierImpl(IDao dao) {
//		this.dao = dao;
//	}


	//injecter dans la variable dao un objet d'une classe qui implemte l'interface IDao
	public void setDao(IDao dao) {
		this.dao = dao;
	}
	public double calcule() {
		double tmp = dao.getData();
		double res = tmp*540/Math.cos(tmp*Math.PI);
		return res;
	}

	
	

}
