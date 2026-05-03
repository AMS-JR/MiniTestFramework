package framework;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

public class FWContext {

	private static List<Object> objectMap = new ArrayList<>();
	private static Map<Class<?>, Object> serviceMap = new HashMap<>();

	public FWContext() {
		try {
			Reflections reflections = new Reflections("");

			// Test classes
			Set<Class<?>> testClasses = reflections.getTypesAnnotatedWith(TestClass.class);
			for (Class<?> clazz : testClasses) {
				objectMap.add(clazz.newInstance());
			}

			// Service classes
			Set<Class<?>> services = reflections.getTypesAnnotatedWith(Service.class);
			for (Class<?> clazz : services) {
				Object instance = clazz.newInstance();
				serviceMap.put(clazz.getInterfaces()[0], instance); // map interface -> impl
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void start() {
		try {
			for (Object theTestClass : objectMap) {

				// inject dependencies
				for (Field field : theTestClass.getClass().getDeclaredFields()) {
					if (field.isAnnotationPresent(Inject.class)) {
						field.setAccessible(true);
						Object service = serviceMap.get(field.getType());
						field.set(theTestClass, service);
					}
				}

				Method beforeMethod = null;

				for (Method method : theTestClass.getClass().getDeclaredMethods()) {
					if (method.isAnnotationPresent(Before.class)) {
						beforeMethod = method;
					}
				}

				for (Method method : theTestClass.getClass().getDeclaredMethods()) {
					if (method.isAnnotationPresent(Test.class)) {

						if (beforeMethod != null) {
							beforeMethod.invoke(theTestClass);
						}

						method.invoke(theTestClass);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
