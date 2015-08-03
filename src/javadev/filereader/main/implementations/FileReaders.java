package javadev.filereader.main.implementations;

import java.io.InputStream;

import javadev.filereader.main.interfaces.CSVFileReader;

public class FileReaders {
	public static<T> CSVFileReader<T> getSimpleCSVReader(Class<T> target, String separator, InputStream file){
		return new SimpleCSVFileReader<T>(target, separator, file);
	}
}
