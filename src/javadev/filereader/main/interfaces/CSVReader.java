package javadev.filereader.main.interfaces;

import java.io.IOException;
import java.util.List;

import javadev.filereader.parsers.exceptions.FieldParsingException;


public interface CSVReader<T> {
	List<T> getResultList() throws IOException, InstantiationException, IllegalAccessException, FieldParsingException;
}
