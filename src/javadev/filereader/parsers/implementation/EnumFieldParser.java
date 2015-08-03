package javadev.filereader.parsers.implementation;

import java.lang.reflect.Field;

import javadev.filereader.parsers.exceptions.FieldParsingException;
import javadev.filereader.parsers.interfaces.TypeParser;

public class EnumFieldParser implements TypeParser<Enum<?>> {

	@Override
	public Enum<?> parse(Field field, String fieldValue) throws FieldParsingException {
		Class<Enum> enumClass = (Class<Enum>) field.getType();
		return Enum.valueOf(enumClass, fieldValue);
	}

}
