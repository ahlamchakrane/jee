package InjectionByConstructors;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigurionConstructors {

	@SuppressWarnings("rawtypes")
	private Map<Class, Object> listClass = new HashMap<Class, Object>();
	@SuppressWarnings("rawtypes")
	private List<Class> listClasse = new ArrayList<>();

	public ConfigurionConstructors(@SuppressWarnings("rawtypes") List<Class> listClasse) {
		this.listClasse = listClasse;
	}

	public Object getInstance(Class<?> recentClass) throws InstantiationException, IllegalAccessException {

		for (Class<?> myClass : listClass.keySet()) {
			if (myClass.getInterfaces()[0].toString().equals(recentClass.toString())) {
				System.out.println(myClass.getInterfaces()[0].toString());
				return listClass.get(myClass);
			}

		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public void instacierInjection() throws Exception {
		for (Class<?> myClass : listClasse) {
			listClass.put(myClass, myClass.newInstance());
		}
		for (Class<?> myClass : listClasse) {
			if (myClass.getDeclaredFields() != null) {
				for (Field f : myClass.getDeclaredFields()) {
					if (f.getType().toString().contains("i")) {
						String methodName = "setDao";

						Method method = myClass.getMethod(methodName, f.getType());
						method.invoke(listClass.get(myClass), getInstance(f.getType()));

					}

				}
			}

		}

	}

	@SuppressWarnings("rawtypes")
	public Map<Class, Object> getListClass() {
		return listClass;
	}

	@SuppressWarnings("rawtypes")
	public void setListClass(Map<Class, Object> listClass) {
		this.listClass = listClass;
	}

	@SuppressWarnings("rawtypes")
	public List<Class> getListClasse() {
		return listClasse;
	}

	@SuppressWarnings("rawtypes")
	public void setListClasse(List<Class> listClasse) {
		this.listClasse = listClasse;
	}
}
