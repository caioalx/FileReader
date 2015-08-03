package javadev.filereader.main.implementations;

import java.lang.reflect.Field;

import javadev.filereader.annotations.FilePosition;
import javadev.filereader.main.interfaces.FileBeanValidation;

public class SimpleCSVBeanPositionValidation extends SimpleCSVBeanValidation implements FileBeanValidation{

	@Override
	public void validate(String datum, Field field) {
		this.datum = datum;
		this.field = field;
		validateExistingColumnPosition();
		super.validate();
	}
	
	private void validateExistingColumnPosition(){
		if(field.isAnnotationPresent(FilePosition.class)){
			if(datum == null) throw new IllegalArgumentException("The following position "+ field.getAnnotation(FilePosition.class).value() +" wasn't found");
		}
	}

}
