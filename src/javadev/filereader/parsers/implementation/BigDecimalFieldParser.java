package javadev.filereader.parsers.implementation;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import javadev.filereader.parsers.exceptions.FieldParsingException;
import javadev.filereader.parsers.interfaces.TypeParser;

public class BigDecimalFieldParser implements TypeParser<BigDecimal> {

	public BigDecimal parse(Field field, String fieldValue) throws FieldParsingException {
		try {
			DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
			df.setParseBigDecimal(true);
			BigDecimal bd = (BigDecimal) df.parseObject(fieldValue);
			return new BigDecimal(bd.toString());
		} catch (NumberFormatException e) {
			throw new FieldParsingException("Valor " + field + " não é um número válido");
		} catch (ParseException e) {
			throw new FieldParsingException("Valor " + field + " não é um número válido");
		}
	}

}
