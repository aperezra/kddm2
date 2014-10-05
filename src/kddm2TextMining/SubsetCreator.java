package kddm2TextMining;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;



/**
 * The Class SubsetCreator.
 *
 * @author Alvaro Garcia Hortelano & Alvaro Perez Ramon
 * @version 9/04/2014
 */
public class SubsetCreator{

	
	/** The files. */
	public ArrayList<File> files = new ArrayList<File>();
	
	
	
	/**
	 * Instantiates a new subset creator.
	 */
	public SubsetCreator() {
		super();
		this.files = new ArrayList<File>() ;
	}

	/**
	 * Parse the text just getting the bodies of the email and ignoring the rest
	 *
	 * @param file the file
	 * @return the array list
	 */
	public static ArrayList<String> parser(File file){

		String paragraph = null;
		FileReader fr = null;
		BufferedReader br = null;
		ArrayList<String> body = new ArrayList<String>();
		String[] par = null;
		try{
			fr = new FileReader (file);
			br = new BufferedReader(fr);

			// Reading the file
			String line;


			String pattern = "(?<=(rn|r|n))([ \t]*$)+";
			while(((line=br.readLine())!=null)){
				if(!line.contains("Message-ID:")&&!line.contains("From:")&&!line.contains("Date:")&&!line.contains("To:")&&!line.contains("Subject:")&&!line.contains("Mime-Version:")&&!line.contains("Content-Transfer-Encoding:") &&!line.contains("Content-Type:")&&!line.contains("X-From:")&&!line.contains("X-To:")&&!line.contains("X-bcc:")&&!line.contains("X-cc:")&&!line.contains("X-Folder:")&&!line.contains("X-Origin:")&&!line.contains("X-FileName:")&& !line.contains("----")&& !line.contains("@") && !line.contains(">")){
					par = Pattern.compile(pattern, Pattern.MULTILINE).split(line);

					for (int i = 0; i < par.length; i++) {

						paragraph = par[i];
						body.add(paragraph);
						//System.out.println(paragraph + "  esta es la linea:  " + i);

					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			// Closing the file, just to ensure that it is closed either there are exceptions or not.
			try{                    
				if( null != fr ){   
					fr.close();     
				}                  
			}catch (Exception e2){ 
				e2.printStackTrace();
			}
		}
		System.out.println(body.size());
		return body;
	}

	/**
	 * Write a file with the already parsed text.
	 *
	 * @param body the body
	 * @param file the file
	 */
	public static void writeFile(ArrayList<String> body, FileWriter file){
		{

			PrintWriter pw = null;
			try
			{	            
				pw = new PrintWriter(file);
			
				for(String val:body){
					pw.println(val);
					System.out.println(val);
				}
				//pw.println("");
			}
			catch (Exception e) {
				e.printStackTrace();
			} 
			finally {
				try {
					// Nuevamente aprovechamos el finally para 
					// asegurarnos que se cierra el fichero.
					if (null != file)
						file.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}

	}

	/**
	 * Listar directorio. List the directories in a folder
	 *
	 * @param File with the path to the directory.
	 */
	private void listarDirectorio(File directorio){
		File[] ficheros = directorio.listFiles();
		String separador = " ";
		for (int x=0;x<ficheros.length;x++){	
			this.files.add(ficheros[x]);
			System.out.println(separador + ficheros[x].getName());
			if (ficheros[x].isDirectory()){
				listarDirectorio(ficheros[x]);
			}
		
		}



	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main (String[] args) throws IOException{
		SubsetCreator subset = new SubsetCreator();
		File file = new File("/Users/alvaro/Documents/TUGraz/KDDM2/enron_mail_20110402/maildir/");
		subset.listarDirectorio(file);
		for (File f: subset.getFiles()){
			
			FileWriter filewr = new FileWriter("/Users/alvaro/Documents/TUGraz/KDDM2/enron_mail_20110402_subset_3/"+f.getName());
			writeFile(parser(f), filewr);
		}
	}

	/**
	 * Gets the files.
	 *
	 * @return the files
	 */
	public ArrayList<File> getFiles() {
		return files;
	}

	/**
	 * Sets the files.
	 *
	 * @param files the new files
	 */
	public void setFiles(ArrayList<File> files) {
		this.files = files;
	}



}
