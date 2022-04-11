package dao;

import org.springframework.stereotype.Component;

@Component("dao")
public class DaoImpl implements IDao {

	public double getData() {
		System.out.println("version base de données");
		double temp=Math.random()*40;
		return temp;
	}

}
