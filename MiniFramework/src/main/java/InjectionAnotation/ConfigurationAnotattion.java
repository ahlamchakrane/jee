package InjectionAnotation;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ConfigurationAnotattion {
	@SuppressWarnings("rawtypes")
	HashMap<Class, Object> instances = new HashMap<Class, Object>();

	public void getClasses(String... packages) throws Exception {
		@SuppressWarnings("rawtypes")
		ArrayList<Class> classes = new ArrayList<Class>();
		Set<Class<?>> type = null;
		for (String packageName : packages) {
			Reflections reflections = new Reflections(
					new ConfigurationBuilder().setScanners(new SubTypesScanner(false), new ResourcesScanner())
							.addUrls(ClasspathHelper.forJavaClassPath())
							.filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(packageName))));

			type = reflections.getSubTypesOf(Object.class);
			for (Class<?> myClass : type) {
				if (myClass.toString().contains("class")) {
					@SuppressWarnings("deprecation")
					Object object = myClass.newInstance();
					instances.put(myClass.getInterfaces()[0], object);
					classes.add(myClass);
				}
			}

		}
		for (Class<?> myClass : classes) {
			if (myClass.getAnnotations()[0].toString().contains("AhlamComponent")
					&& myClass.getDeclaredFields().length > 0) {
				Field[] fields = myClass.getDeclaredFields();
				for (Field field : fields) {
					if (field.getAnnotations()[0].toString().contains("AhlamAutowired")) {
						Method method = myClass.getMethod("setDao", field.getType());
						method.invoke(instances.get(myClass.getInterfaces()[0]), instances.get(field.getType()));
					}
				}
			}
		}

	}
	@SuppressWarnings("rawtypes")
	public HashMap<Class, Object> getInstances() {
		return instances;
	}
}
