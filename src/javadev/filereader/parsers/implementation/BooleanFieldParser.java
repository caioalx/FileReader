package javadev.filereader.parsers.implementation;

import java.lang.reflect.Field;

import javadev.filereader.annotations.BooleanConverter;
import javadev.filereader.parsers.exceptions.FieldParsingException;
import javadev.filereader.parsers.interfaces.TypeParser;

public class BooleanFieldParser implements TypeParser<Boolean> {

	@Override
	public Boolean parse(Field field, String fieldValue) throws FieldParsingException {
		if(!field.isAnnotationPresent(BooleanConverter.class)) 
			throw new FieldParsingException("A anotação @BooleanConverter não encontra-se presente para realizar a conversão");
		
		BooleanConverter booleanConverter = field.getAnnotation(BooleanConverter.class);
		String trueValue = booleanConverter.trueValue();
		String falseValue = booleanConverter.falseValue();
		
		if(!fieldValue.equalsIgnoreCase(trueValue) && !fieldValue.equalsIgnoreCase(falseValue))
			throw new FieldParsingException("Os valor "+ fieldValue + " não é compatível com os valores configurados na anotação @BooleanConverter(trueValue="+trueValue+",falseValue="+falseValue+")");
		
		return fieldValue.equalsIgnoreCase(trueValue);
	}

}
