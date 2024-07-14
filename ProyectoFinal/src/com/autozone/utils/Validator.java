package com.autozone.utils;

import java.lang.reflect.Field;
import com.autozone.annotations.NotNull;
import com.autozone.annotations.ValidAction;


public class Validator {
	
	public static void validate(Object obj) throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = obj.getClass().getDeclaredFields();
		for(Field field : fields) {
			field.setAccessible(true);
			
			//NotNull validation
			if(field.isAnnotationPresent(NotNull.class)) {
				Object value = field.get(obj);
				
				if(value == null) {
					throw new IllegalArgumentException(field.getName() + " no puede ser nulo.");
					
				}
			}
			
			if(field.isAnnotationPresent(ValidAction.class)) {
				String value = field.get(obj).toString().toUpperCase().trim();
				
				if(!value.equals("A") && !value.equals("U") && !value.equals("D") && !value.equals("S") && !value.equals("I")) {
					throw new IllegalArgumentException(value + " no es una accion válida.");
				}
			}
			
		}
	}
	
	
}


