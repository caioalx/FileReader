package javadev.filereader.main.interfaces;

import java.io.IOException;


public interface CSVFileReader<T> {
	CSVReader<T> withHeader() throws IOException;
	CSVReader<T> withPosition() throws IOException;
	CSVReader<T> withCustomCSVReader(CSVReader<T> customCSVReader) throws IOException;
}