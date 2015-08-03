package javadev.filereader.main.implementations;
import java.lang.reflect.Field;
import java.util.Map;

import javadev.filereader.annotations.Size;
import javadev.filereader.parsers.interfaces.TypeParser;
import javadev.filereader.parsers.utils.ParsersUtils;

abstract class SimpleCSVBeanValidation {
	protected String datum;
	protected Field field;
	protected Map<Class<?>, TypeParser<?>> parsers = ParsersUtils.getParsers();
	
	public void validate(){
		validateSize();
		validateParse();
	}
	
	private void validateSize(){
		if(!field.isAnnotationPresent(Size.class)) return;
		Size sizeAnnotation = field.getAnnotation(Size.class);
		Integer size = sizeAnnotation.value();
		if(size != null && (datum.length() > size)){
			throw new IllegalStateException("The following data "+field.getName()+":"+ datum + " has incompatible data size. Maximum: "+ size + ", data size: " + datum.length());
		}
	}
	
	private void validateParse(){
		if(field.getType().isEnum()) return;
		TypeParser<?> parser = parsers.get(field.getType());
		if(parser == null ) throw new IllegalStateException("The following parse "+ field.getType() +" actually doesn't exist. Use " + parsers.keySet());
	}
	
	
}
