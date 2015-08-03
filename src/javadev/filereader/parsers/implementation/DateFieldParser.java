package javadev.filereader.parsers.implementation;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

import javadev.filereader.parsers.exceptions.FieldParsingException;
import javadev.filereader.parsers.interfaces.TypeParser;


public class DateFieldParser implements TypeParser<Date> {

	@Override
	public Date parse(Field field, String fieldValue) throws FieldParsingException {
		if (fieldValue.isEmpty()) return null;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return format.parse(fieldValue);
		} catch (Exception e) {
			throw new FieldParsingException("Valor " + fieldValue + " não é uma data válida. Formato correto: 01/01/2000");
		}
	}

}
