package InjectionByConstructors;

import extention.DaoImpl2;
import metier.ImetierImpl;
import java.util.ArrayList;
import java.util.List;

public class Presentation {
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws Exception {
		List<Class> list = new ArrayList<>();
		list.add(ImetierImpl.class);
		list.add(DaoImpl2.class);
		ConfigurionConstructors configurionConstructors = new ConfigurionConstructors(list);
		configurionConstructors.instacierInjection();
		ImetierImpl imtImetierImpl = (ImetierImpl) configurionConstructors.getListClass().get(ImetierImpl.class);
		System.out.println(imtImetierImpl.calcule());
	}
}
