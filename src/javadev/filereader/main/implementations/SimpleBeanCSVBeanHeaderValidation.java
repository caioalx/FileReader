package javadev.filereader.main.implementations;

import java.lang.reflect.Field;

import javadev.filereader.annotations.FileHeader;
import javadev.filereader.main.interfaces.FileBeanValidation;

class SimpleBeanCSVBeanHeaderValidation extends SimpleCSVBeanValidation implements FileBeanValidation {
	
	@Override
	public void validate(String datum, Field field) {
		this.datum = datum;
		this.field = field;
		validateExistingColumnName();
		super.validate();
	}
	
	private void validateExistingColumnName(){
		String name = field.getName().toUpperCase();
		if(field.isAnnotationPresent(FileHeader.class)){
			FileHeader fileField = field.getAnnotation(FileHeader.class);
			name = fileField.value().toUpperCase();
		}
		if(datum == null) throw new IllegalArgumentException("The following column "+ name +" wasn't found");
	}	

}
