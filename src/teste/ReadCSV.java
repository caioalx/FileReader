package teste;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javadev.filereader.main.implementations.FileReaders;
import javadev.filereader.parsers.exceptions.FieldParsingException;

public class ReadCSV {
	  public static void main(String[] args) throws InstantiationException, IllegalAccessException, FieldParsingException {
		  
		  ReadCSV obj = new ReadCSV();
			obj.run();
		 
		  }
		 
		  public void run() throws InstantiationException, IllegalAccessException, FieldParsingException {
			String csvFile = "C:\\Users\\t0448135\\Downloads\\NAO APAGAR Cadastro Aprovados - Edital 02-2011 - Operacional e Adm.csv";
			//String csvFile = "C:\\Users\\t0448135\\Downloads\\NAO APAGAR - SEM Cabeça Cadastro Aprovados - Edital 02-2011 - Operacional e Adm.csv";
			try {
				
				FileInputStream fileInputStream=null;
				fileInputStream = new FileInputStream(csvFile);
				
				List<Classificado> classificados = 
						FileReaders.getSimpleCSVReader(Classificado.class, ";", fileInputStream).withHeader().getResultList();
			
				fileInputStream.close();
				
				for (Classificado classificado : classificados) {
					System.out.println(classificado);
				}
				System.out.println("Tamanho: " + classificados.size());
				
		 
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			System.out.println("Done");
		  }
}
