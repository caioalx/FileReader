package javadev.filereader.parsers.implementation;

import java.lang.reflect.Field;

import javadev.filereader.parsers.exceptions.FieldParsingException;
import javadev.filereader.parsers.interfaces.TypeParser;


public class StringFieldParser implements TypeParser<String> {

	@Override
	public String parse(Field field, String fieldValue) throws FieldParsingException {
		try {
			return fieldValue;
		} catch (Exception e) {
			throw new FieldParsingException("Valor " + field + " não é uma string válida");
		}
	}
}
