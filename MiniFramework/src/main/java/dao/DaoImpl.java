package dao;

import InjectionAnotation.AhlamComponent;

@AhlamComponent
public class DaoImpl implements IDao {
	@Override
	public double getData() {
		return Math.random() * 10;
	}
}
