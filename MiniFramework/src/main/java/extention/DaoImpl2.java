package extention;

import dao.IDao;

public class DaoImpl2 implements IDao {
	@Override
	public double getData() {
		return Math.random() * 2000;
	}
}
