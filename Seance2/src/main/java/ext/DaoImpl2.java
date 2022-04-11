package ext;

import dao.IDao;

public class DaoImpl2 implements IDao {

	public double getData() {
		System.out.println("Dao Impl 2");
		double data=77;
		return data;
	}

}
