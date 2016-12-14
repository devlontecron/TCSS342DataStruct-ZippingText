/*
 * TCSS 342 - Spring 2016
 * Assignment 5 - MazeGenerator
 */

/**
 * Main driver Class.
 * 
 * @author Samantha Ong - sjfoh and Devin Durham - d1duram
 * @version 3 May 2016
 *
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		long start = System.nanoTime();
		final StringBuilder sb = new StringBuilder();
		CodingTree myTree = new CodingTree();
		Scanner sc;
//-------
		BufferedReader bf = new BufferedReader(new FileReader("./WarAndPeace.txt"));
		int curr = -1;
		if(bf.ready()){
			curr = 0;
		}
		while(curr !=-1){
			curr=bf.read();
			sb.append((char)curr);
		}
		myTree.CodingTree(sb.toString());
//---- Received help from classmate (Joseph) for reading in file char by char via BufferReader
	
		/*
		try {
			sc = new Scanner(new File("./WarAndPeace.txt")); 
			while (sc.hasNext()) {
				
				String line = sc.n .next();
				sb.append(line);	
			}
			myTree.CodingTree(sb.toString());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		*/

		FileOutputStream file = new FileOutputStream("./MyBin.bin");
	    String data = myTree.bits;
	    int x = 0;
	    byte ByteArr[] = new byte[(data.length()/8)+1];
	    for(x= 0; (x*8)+7<data.length(); x++){
	    	String temp = data.substring((8*x), (x*8)+7);
	    	ByteArr[x] = Byte.parseByte(temp,2);  	    	
	    }
	    String temp = data.substring(8*x, data.length());
	    ByteArr[x] = Byte.parseByte(temp, 2);
	    
	    file.write(ByteArr);
	    file.close();
	 	
	    File fileCodes = new File("./MyCodes.txt");
	    FileOutputStream foss = new FileOutputStream(fileCodes);
	    String Codedata = myTree.sbCodes.toString();
	    foss.write(Codedata.getBytes());
	    foss.close();
	    
		File fileIn = new File("./WarAndPeace.txt");
		double fileSize = fileIn.length();
		double fileSizeKB = fileSize / 1024;
		
		File fileOut = new File("./MyBin.bin");
		double fileSizeOut = fileOut.length();
		double fileSizeOutKB = fileSizeOut / 1024;
		
		double ratio = (fileSizeOutKB/fileSizeKB)*100 ;
		
		long stop = System.nanoTime();
		long timeN =(stop-start);
		double timeS = timeN/1000000000.0;
		
		System.out.println("Origonal Size(in Kilobytes): " + fileSizeKB );
		//System.out.println("Origonal Size(in bytes): " + fileSizeOut);
		System.out.println("Compresion Size(in Kilobytes): " + fileSizeOutKB);
		System.out.println("Compresion Ratio(%): " + (100-ratio));
		System.out.println("RunTime(in seconds): " + timeS);
		//System.out.println("RunTime(in nano-seconds): " + timeN );
		
		//testCodingTree();
		
	}
	
	private static void testCodingTree(){
		CodingTree testTree = new CodingTree();
		testTree.CodingTree("hannah banana wears bandanas");
		System.out.println("hannah banana wears bandanas");  
		System.out.println(testTree.sbCodes.toString());
		System.out.println(testTree.bits);  
	}
	
	
	

}
