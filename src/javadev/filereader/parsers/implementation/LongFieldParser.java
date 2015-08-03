package javadev.filereader.parsers.implementation;

import java.lang.reflect.Field;

import javadev.filereader.parsers.exceptions.FieldParsingException;
import javadev.filereader.parsers.interfaces.TypeParser;


public class LongFieldParser implements TypeParser<Long> {

	@Override
	public Long parse(Field field, String fieldValue) throws FieldParsingException {
		try {
			return Long.parseLong(fieldValue);
		} catch (NumberFormatException e) {
			throw new FieldParsingException("Valor " + field + " não é um número válido");
		}
	}

}
