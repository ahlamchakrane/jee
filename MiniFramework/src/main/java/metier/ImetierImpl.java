package metier;

import InjectionAnotation.AhlamAutowired;
import InjectionAnotation.AhlamComponent;
import dao.IDao;

@AhlamComponent
public class ImetierImpl implements Imetier {
	@AhlamAutowired
	private IDao dao;

	@Override
	public double calcule() {
		return dao.getData();
	}
	public void setDao(IDao dao) {
		this.dao = dao;
	}
}
