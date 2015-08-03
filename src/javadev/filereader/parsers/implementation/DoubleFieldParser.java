package javadev.filereader.parsers.implementation;

import java.lang.reflect.Field;

import javadev.filereader.parsers.exceptions.FieldParsingException;
import javadev.filereader.parsers.interfaces.TypeParser;


public class DoubleFieldParser implements TypeParser<Double> {

	@Override
	public Double parse(Field field, String fieldValue) throws FieldParsingException {
		try {
			return Double.parseDouble(fieldValue);
		} catch (NumberFormatException e) {
			throw new FieldParsingException("Valor " + field + " não é um número decimal válido");
		}
	}

}
