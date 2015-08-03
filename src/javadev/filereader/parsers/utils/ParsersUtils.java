package javadev.filereader.parsers.utils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javadev.filereader.parsers.implementation.BigDecimalFieldParser;
import javadev.filereader.parsers.implementation.BooleanFieldParser;
import javadev.filereader.parsers.implementation.DateFieldParser;
import javadev.filereader.parsers.implementation.DoubleFieldParser;
import javadev.filereader.parsers.implementation.EnumFieldParser;
import javadev.filereader.parsers.implementation.IntegerFieldParser;
import javadev.filereader.parsers.implementation.LongFieldParser;
import javadev.filereader.parsers.implementation.StringFieldParser;
import javadev.filereader.parsers.interfaces.TypeParser;

public class ParsersUtils {

	public static Map<Class<?>, TypeParser<?>> getParsers(){
		Map<Class<?>, TypeParser<?>> parsers = new HashMap<Class<?>, TypeParser<?>>();
		parsers.put(Long.class, new LongFieldParser());
		parsers.put(Integer.class, new IntegerFieldParser());
		parsers.put(Double.class, new DoubleFieldParser());
		parsers.put(Date.class, new DateFieldParser());
		parsers.put(String.class, new StringFieldParser());
		parsers.put(BigDecimal.class, new BigDecimalFieldParser());
		parsers.put(Boolean.class, new BooleanFieldParser());
		parsers.put(Enum.class, new EnumFieldParser());
		return parsers;
	}
}
