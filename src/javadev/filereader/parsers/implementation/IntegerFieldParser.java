package javadev.filereader.parsers.implementation;

import java.lang.reflect.Field;

import javadev.filereader.parsers.exceptions.FieldParsingException;
import javadev.filereader.parsers.interfaces.TypeParser;


public class IntegerFieldParser implements TypeParser<Integer> {

	@Override
	public Integer parse(Field field, String fieldValue) throws FieldParsingException {
		try {
			return Integer.parseInt(fieldValue);
		} catch (NumberFormatException e) {
			throw new FieldParsingException("Valor " + field + " não é um número válido");
		}
	}

}
