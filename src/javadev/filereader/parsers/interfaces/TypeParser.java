package javadev.filereader.parsers.interfaces;

import java.lang.reflect.Field;

import javadev.filereader.parsers.exceptions.FieldParsingException;

/**
 * @author viniciusbm
 *
 */
public interface TypeParser<T> {
	T parse(Field field, String fieldValue) throws FieldParsingException;
}
