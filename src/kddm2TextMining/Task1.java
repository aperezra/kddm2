package kddm2TextMining;

import java.io.InputStream;
import java.io.IOException;






//import de.l3s.boilerpipe.BoilerpipeProcessingException;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.util.Span;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.Tokenizer;



/**
 * The Class Task1.
 *
 * @author Alvaro Garcia Hortelano & Alvaro Perez Ramon
 * @version 25/5/2014
 * 
 * Find different categories throughout email's bodies.
 */
public class Task1 {

	/** The greetings. */
	String greetings[] = new String[]{"Gentlemen", "Hi", "Hello", "Good Morning", "Good Afternoon","Good Evening","Hey","Dear","How are you", "how are you"};
	
	/** The farewells. */
	String farewells[] = new String[]{"Many thanks","Best ,","Love,","Bye","Regards", "Rgds", "Regards,","Regards ," , "Kind Regards","Kind regards","Kind Regards-","kind regards" , "Best regards","Best Regards-","best regards", "Love," ,"Love ,", "Thanks.","Thanks -", "Thanks .", "Thanks,", "Thanks ," };
	
	/** The questions. */
	String questions[] = new String[]{"Do you","Can you","Please", "pls", "could you", "?"};
	
	/** The orders. */
	String orders[] = new String[]{"We should","we should", "Make sure","Try", "Update", "Please"};
	
	/** The suggestions. */
	String suggestions[] = new String[]{", please","Remember", "Let me","let me","Feel free", "feel free", "please", "call me", "Call me"};
	
	/** The apologies. */
	String apologies[] = new String[]{"Sorry", "Excuse me", "My apologies"};
	
	/** The gratitudes. */
	String gratitudes[] = new String[] {"thanks", "Thanks", "thx", "Thx", "thank", "Thank"};

	//String attaches[] = new String[]{".doc", "http", ".pdf", ".png", ".txt"};
	/** The greeting. */
	public int greeting;
	
	/** The farewell. */
	public int farewell;
	
	/** The body. */
	public int body;
	
	/** The question. */
	public int question;
	
	/** The order. */
	public int order;
	
	/** The suggestion. */
	public int suggestion;
	
	/** The apology. */
	public int apology;
	
	/** The attach. */
	public int attach;
	
	/** The info about date. */
	public int infoAboutDate;
	
	/** The info about location. */
	public int infoAboutLocation;
	
	/** The info about money. */
	public int infoAboutMoney;
	
	/** The enumerate. */
	public int enumerate;
	
	/** The num tokens. */
	public int numTokens;
	
	/** The gratitude. */
	public int gratitude;
	
	
	

	/**
	 * Instantiates a new task1.
	 */
	public Task1() {
		super();
		this.greeting = 0;
		this.farewell = 0;
		this.body = 0;
		this.question = 0;
		this.order = 0;
		this.suggestion = 0;
		this.apology = 0;
		this.attach = 0;
		this.infoAboutDate = 0;
		this.infoAboutLocation = 0;
		this.infoAboutMoney = 0;
		this.enumerate = 0;
		this.numTokens = 0;
		this.gratitude=0;		
	}
	

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args){
		Task1 task1 = new Task1();

		File file = new File("/Users/alvaro/Documents/TUGraz/KDDM2/enron_mail_20110402_subset/1553.");
			String mail = task1.readEmail(file);
			String[] sentences = null;
			sentences = task1.Task1Deteccion(mail);
			//int numSentences = sentences.length;
			for (String b: sentences){
				String label = task1.decide( b, b.length());
				System.out.println( label +"\n"+b);
			} 
		}
	
	/**
	 * Decide.
	 *
	 * @param mail the mail
	 * @param length the length
	 * @return the string
	 */
	public String decide(String mail, int length){

		String etiqueta = "";
		boolean  greet = thereIsGreeting(mail);
		boolean  farewell = thereIsFarewell(mail);

		if (greet && length<30){

			etiqueta = etiqueta + "[GREETING]";
			this.greeting++;
		}else if (farewell ){
			etiqueta = etiqueta + "[FAREWELL]";
			this.farewell++;
		}else {
			etiqueta = "[BODY]" + decideBody(mail);
			this.body++;
		}


		return etiqueta;
	}

	/**
	 * There is name receiver.
	 *
	 * @param tokens the tokens
	 * @return the string
	 */
	public String thereIsNameReceiver(String[] tokens){
		String thereIs = "";
		Span[] names = Task1PersonNameFinder(tokens);
		String[] nombres = new String[10];
		for(@SuppressWarnings("unused") Span s: names){
			//System.out.println(nombres.length);
			nombres = Span.spansToStrings(names,tokens);
		}

		if(nombres!=null){
			if(nombres.length!=0){
				thereIs=nombres[0];

			}
		}
		return thereIs;

	}

	/**
	 * Decide body.
	 *
	 * @param mail the mail
	 * @return the string
	 */
	public String decideBody(String mail){

		String[] tokens = Task1Token(mail);

		this.numTokens = this.numTokens + tokens.length;
		String type = "";
		/**
		 if(mail.contains(thereIsNameReceiver(tokens)+",")|| tokens[0]==thereIsNameReceiver(tokens)){
			 type = type + "[RECEIVER]";
			 System.out.println(thereIsNameReceiver(tokens));
		 }
		 **/
		if(thereIsDate(tokens)==true){
			type = type +"[INFO ABOUT DATE/PHONE NUMBER]";


		}
		if(thereIsLocation(tokens)==true){
			type = type +"[INFO ABOUT LOCATION]";


		}
		if(thereIsMoney(tokens)==true){
			type = type +"[INFO ABOUT MONEY]";


		}

		 if(thereIsInfoAttach(mail)==true){
			 type = type +"[INFO ABOUT ATTACHMENT]";
		 
		 }
		 
		if(thereIsQuestion(tokens)){
			type = type + "[QUESTION]";

		
		}
		
		if(thereIsOrder(tokens)){
			type = type + "[ORDER]";

		}
		if(thereIsSuggestion(tokens)){
			type = type + "[SUGGESTION]";

		}

		if(thereIsApologies(tokens)){
			type = type + "[APOLOGY]";

		}
		if(thereIsEnumeration(mail)){
			type = type + "[ENUMERATION]";

			

		}
		if(thereIsAttached(mail)){
			type = type + "[ATTACHMENT]";


		}
		
		
		 if(thereIsGratitude(tokens)){
			 type = type + "[GRATITUDE]";
		 }
		return type;
	}
	
	/**
	 * There is gratitude.
	 *
	 * @param tokens the tokens
	 * @return true, if successful
	 */
	public boolean thereIsGratitude(String[] tokens){
		 
		 String check= "";
		 boolean thereIs = false;
		 
		 for(int i=0; i<tokens.length; i++){
			 for(int j=0; j < gratitudes.length; j++){
				if(i!=(tokens.length-1)){

					
					check = tokens[i]+" "+tokens[i+1];
					//System.out.println(check+"-----igual?-----"+ greetings[j]);
					
				if(check.equals(gratitudes[j])){
					thereIs = true;
				}
				}if(i==0){
					check = tokens[i];
					//System.out.println(check+"-----igual?-----"+ greetings[j]);
					if(check.equals(gratitudes[j])){
						thereIs = true;
					}
				}
			}
		 }
		 
		 return thereIs;
	 }
	
	/**
	 * There is enumeration.
	 *
	 * @param mail the mail
	 * @return true, if successful
	 */
	public boolean thereIsEnumeration(String mail){
		boolean thereIs = false;
		if(mail.contains("1.")||mail.contains("1)")||mail.contains("2.")||mail.contains("2)")||mail.contains("3.")||mail.contains("3)")||mail.contains("4.")||mail.contains("4)")||mail.contains("a.")||mail.contains("a)")||mail.contains("b.")||mail.contains("b)")){
			thereIs=true;
			this.enumerate++;
		}
		return thereIs;
	}

	/**
	 * There is date.
	 *
	 * @param tokens the tokens
	 * @return true, if successful
	 */
	public boolean thereIsDate(String[] tokens){

		boolean thereIs = false;

		Span[] names = Task1DateFinder(tokens);
		String[] nombres=null;
		for(@SuppressWarnings("unused") Span s: names){
			nombres = Span.spansToStrings(names,tokens);
			//System.out.println(nombres[0]);
		}

		if(nombres==null){

			thereIs=false;
		}else if(nombres.length>0 ){
			this.infoAboutDate++;
			
			thereIs=true;

		}
		return thereIs;

	}

	/**
	 * There is money.
	 *
	 * @param tokens the tokens
	 * @return true, if successful
	 */
	public boolean thereIsMoney(String[] tokens){

		boolean thereIs = false;

	
		Span[] names =Task1MoneyFinder(tokens);
		String[] nombres=null;
		for(@SuppressWarnings("unused") Span s: names){
			nombres = Span.spansToStrings(names,tokens);

		}

		if(nombres==null){

			thereIs=false;
		}else if(nombres.length>0 ){
			this.infoAboutMoney++;
			thereIs=true;

		}
		return thereIs;

	}

	/**
	 * There is location.
	 *
	 * @param tokens the tokens
	 * @return true, if successful
	 */
	public boolean thereIsLocation(String[] tokens){

		boolean thereIs = false;

		Span[] names =Task1LocationNameFinder(tokens);
		String[] nombres=null;
		for(@SuppressWarnings("unused") Span s: names){
			nombres = Span.spansToStrings(names,tokens);
		}

		if(nombres==null){

			thereIs=false;
		}else if(nombres.length>0 ){
			this.infoAboutLocation++;
			//System.out.println("Info about location is : " +this.infoAboutLocation);

			thereIs=true;

		}
		return thereIs;

	}

	/**
	 * There is attached.
	 *
	 * @param mail the mail
	 * @return true, if successful
	 */
	public boolean thereIsAttached(String mail){
		boolean thereIs = false;
		if(mail.contains(".png")||mail.contains("http")||mail.contains(".doc")||mail.contains(".txt")||mail.contains(".pdf")){
			this.attach++;
			thereIs=true;
		}
		return thereIs;
	}

	/**
	 * There is question.
	 *
	 * @param tokens the tokens
	 * @return true, if successful
	 */
	public boolean thereIsQuestion (String[] tokens){

		String check= "";
		boolean thereIs = false;

		for(String b: tokens){

			if(b.matches("\\?")){
				return true;
			}
		}

		for(int i=0; i<tokens.length; i++){
			for(int j=0; j < questions.length; j++){
				if(i!=(tokens.length-1)){


					check = tokens[i]+" "+tokens[i+1];
					//System.out.println(check+"-----igual?-----"+ greetings[j]);

					if(check.equals(questions[j])){
						this.question++;
						thereIs = true;
					}
				}if(i==0){
					check = tokens[i];
					//System.out.println(check+"-----igual?-----"+ greetings[j]);
					if(check.equals(questions[j])){
						this.question++;
						thereIs = true;
					}
				}
			}
		}

		return thereIs;
	}

	/**
	 * There is info attach.
	 *
	 * @param mail the mail
	 * @return true, if successful
	 */
	public boolean thereIsInfoAttach(String mail){
		 boolean thereIs = false;
		 if(mail.contains("attached")||mail.contains("Attached")){
			 thereIs=true;
			 		 }
		 return thereIs;
		 
	 }
	
	/**
	 * There is order.
	 *
	 * @param tokens the tokens
	 * @return true, if successful
	 */
	public boolean thereIsOrder (String[] tokens){

		String check= "";
		boolean thereIs = false;

		for(int i=0; i<tokens.length; i++){
			for(int j=0; j < orders.length; j++){
				if(i!=(tokens.length-1)){


					check = tokens[i]+" "+tokens[i+1];
					//System.out.println(check+"-----igual?-----"+ greetings[j]);

					if(check.equals(orders[j])){
						this.order++;
						thereIs = true;
					}
				}if(i==0){
					check = tokens[i];
					//System.out.println(check+"-----igual?-----"+ greetings[j]);
					if(check.equals(orders[j])){
						this.order++;
						thereIs = true;
					}
				}
			}
		}

		return thereIs;
	}

	/**
	 * There is suggestion.
	 *
	 * @param tokens the tokens
	 * @return true, if successful
	 */
	public boolean thereIsSuggestion (String[] tokens){

		String check= "";
		boolean thereIs = false;

		for(int i=0; i<tokens.length; i++){
			for(int j=0; j < suggestions.length; j++){
				if(i!=(tokens.length-1)){


					check = tokens[i]+" "+tokens[i+1];
					//System.out.println(check+"-----igual?-----"+ greetings[j]);

					if(check.equals(suggestions[j])){
						this.suggestion++;
						thereIs = true;
					}
				}if(i==0){
					check = tokens[i];
					//System.out.println(check+"-----igual?-----"+ greetings[j]);
					if(check.equals(suggestions[j])){
						this.suggestion++;
						thereIs = true;
					}
				}
			}
		}

		return thereIs;
	}

	/**
	 * There is apologies.
	 *
	 * @param tokens the tokens
	 * @return true, if successful
	 */
	public boolean thereIsApologies (String[] tokens){

		String check= "";
		boolean thereIs = false;

		for(int i=0; i<tokens.length; i++){
			for(int j=0; j < apologies.length; j++){
				if(i!=(tokens.length-1)){


					check = tokens[i]+" "+tokens[i+1];
					//System.out.println(check+"-----igual?-----"+ greetings[j]);

					if(check.equals(apologies[j])){
						this.apology++;
						thereIs = true;
					}
				}if(i==0){
					check = tokens[i];
					//System.out.println(check+"-----igual?-----"+ greetings[j]);
					if(check.equals(apologies[j])){
						this.apology++;
						thereIs = true;
					}
				}
			}
		}

		return thereIs;
	}

	/**
	 * There is greeting.
	 *
	 * @param mail the mail
	 * @return true, if successful
	 */
	public boolean thereIsGreeting(String mail){
		String[] tokens = Task1Token(mail);
		this.numTokens = this.numTokens + tokens.length;
		boolean thereIs = false;

		String check= "";		

		for(int i=0; i<tokens.length; i++){

			for(int j=0; j < greetings.length; j++){
				if( i!=(tokens.length-1)){


					check = tokens[i]+" "+tokens[i+1];
					//System.out.println(check+"-----igual?-----"+ greetings[j]);

					if(check.equals(greetings[j])){

						thereIs = true;
					}
				}if(i==0){
					check = tokens[i];
					//System.out.println(check+"-----igual?-----"+ greetings[j]);
					if(check.equals(greetings[j])){

						thereIs = true;
					}
				}
			}
		}
		return thereIs;
	}

	/**
	 * There is farewell.
	 *
	 * @param mail the mail
	 * @return true, if successful
	 */
	public boolean thereIsFarewell(String mail){
		String[] tokens = Task1Token(mail);
		this.numTokens = this.numTokens + tokens.length;



		boolean thereIs = false;

		String check= "";		

		for(int i=0; i < tokens.length; i++){
			for(int j=0; j < farewells.length; j++){
				if( farewells[j].equals(".") || farewells[j].equals(",") || farewells[j].equals(";")|| farewells[j].equals(":")){
					tokens[j]= " ";
				}



				if(i!=(tokens.length-1)){
					check = tokens[i]+" "+tokens[i+1];
					//System.out.println(check+"-----igual?-----"+ farewells[j]);


					if(check.equals(farewells[j])){
						thereIs = true;
					}
				}else{
					check = tokens[i];
					if(check.equals(farewells[j])){
						//System.out.println(check+"-----igual?-----"+ farewells[j]);
						thereIs = true;
					}
				}
			}
		}
		return thereIs;
	}

	/**
	 * Task1 deteccion.
	 *
	 * @param mail the mail
	 * @return the string[]
	 */
	public String[] Task1Deteccion (String mail){
		SentenceDetectorME _sentenceDetector = null;
		InputStream modelIn = null;
		String[] array = null;

		try {
			// Loading sentence detection model
			modelIn = getClass().getResourceAsStream("/en-sent.bin");
			final SentenceModel sentenceModel = new SentenceModel(modelIn);
			modelIn.close();

			_sentenceDetector = new SentenceDetectorME(sentenceModel);
			array = _sentenceDetector.sentDetect(mail);


		}catch (final IOException ioe) {
			ioe.printStackTrace();
		}finally {

			if (modelIn != null) {
				try {
					modelIn.close();
				} catch (final IOException e) {} // oh well!
			}
		}

		return array;
	}

	/**
	 * Task1 date finder.
	 *
	 * @param mail the mail
	 * @return the span[]
	 */
	public Span[] Task1DateFinder (String[] mail){
		InputStream modelIn = null;
		Span[] array = null;

		try {
			// Loading sentence detection model
			modelIn = getClass().getResourceAsStream("/en-ner-date.bin");
			TokenNameFinderModel model = new TokenNameFinderModel(modelIn);
			modelIn.close();

			NameFinderME nameFinder = new NameFinderME(model);

			array = nameFinder.find(mail);


		}catch (final IOException ioe) {
			ioe.printStackTrace();
		}finally {

			if (modelIn != null) {
				try {
					modelIn.close();
				} catch (final IOException e) {} // oh well!
			}
		}

		return array;
	}

	/**
	 * Task1 location name finder.
	 *
	 * @param mail the mail
	 * @return the span[]
	 */
	public Span[] Task1LocationNameFinder (String[] mail){
		InputStream modelIn = null;
		Span[] array = null;

		try {
			// Loading sentence detection model
			modelIn = getClass().getResourceAsStream("/en-ner-location.bin");
			TokenNameFinderModel model = new TokenNameFinderModel(modelIn);
			modelIn.close();

			NameFinderME nameFinder = new NameFinderME(model);

			array = nameFinder.find(mail);


		}catch (final IOException ioe) {
			ioe.printStackTrace();
		}finally {

			if (modelIn != null) {
				try {
					modelIn.close();
				} catch (final IOException e) {} // oh well!
			}
		}

		return array;
	}

	/**
	 * Task1 money finder.
	 *
	 * @param mail the mail
	 * @return the span[]
	 */
	public Span[] Task1MoneyFinder (String[] mail){
		InputStream modelIn = null;
		Span[] array = null;

		try {
			// Loading sentence detection model
			modelIn = getClass().getResourceAsStream("/en-ner-money.bin");
			TokenNameFinderModel model = new TokenNameFinderModel(modelIn);
			modelIn.close();

			NameFinderME nameFinder = new NameFinderME(model);

			array = nameFinder.find(mail);


		}catch (final IOException ioe) {
			ioe.printStackTrace();
		}finally {

			if (modelIn != null) {
				try {
					modelIn.close();
				} catch (final IOException e) {} // oh well!
			}
		}

		return array;
	}

	/**
	 * Task1 person name finder.
	 *
	 * @param mail the mail
	 * @return the span[]
	 */
	public Span[] Task1PersonNameFinder (String[] mail){
		InputStream modelIn = null;
		Span[] array = null;

		try {
			// Loading sentence detection model
			modelIn = getClass().getResourceAsStream("/en-token.bin");
			TokenNameFinderModel model = new TokenNameFinderModel(modelIn);
			modelIn.close();

			NameFinderME nameFinder = new NameFinderME(model);

			array = nameFinder.find(mail);


		}catch (final IOException ioe) {
			ioe.printStackTrace();
		}finally {

			if (modelIn != null) {
				try {
					modelIn.close();
				} catch (final IOException e) {} // oh well!
			}
		}

		return array;
	}

	/**
	 * Task1 token.
	 *
	 * @param mail the mail
	 * @return the string[]
	 */
	public String[] Task1Token (String mail){
		InputStream modelIn = null;
		String[] array = null;

		try {
			// Loading sentence detection model
			modelIn = getClass().getResourceAsStream("/en-token.bin");
			TokenizerModel tokenModel = new TokenizerModel(modelIn);
			modelIn.close();

			Tokenizer tokenizer = new TokenizerME(tokenModel);

			array = tokenizer.tokenize(mail);


		}catch (final IOException ioe) {
			ioe.printStackTrace();
		}finally {

			if (modelIn != null) {
				try {
					modelIn.close();
				} catch (final IOException e) {} // oh well!
			}
		}

		return array;
	}

	/**
	 * Read email.
	 *
	 * @param file the file
	 * @return the string with the text of the email
	 */
	public String readEmail(File file){
		FileReader fr = null;
		BufferedReader br = null;

		//String[] paragraphs = null;
		String line = null;
		String email ="";
		try {
			// Opening the file and creating a BufferedReader that allows to parse all the text included in the file
			fr = new FileReader (file);
			br = new BufferedReader(fr);

			// Reading the file


			while((line=br.readLine())!=null){
				//System.out.println(line);
				if(!line.contains("{")){
					email += "\n" + line;
				}
				//System.out.println(paragraphs);



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
		return email;

	}

	
//Getters and setters for each field.
	
	/**
 * Gets the greeting.
 *
 * @return the greeting
 */
public int getGreeting() {
		return greeting;
	}

	/**
	 * Gets the farewell.
	 *
	 * @return the farewell
	 */
	public int getFarewell() {
		return farewell;
	}

	/**
	 * Gets the body.
	 *
	 * @return the body
	 */
	public int getBody() {
		return body;
	}

	/**
	 * Gets the question.
	 *
	 * @return the question
	 */
	public int getQuestion() {
		return question;
	}

	/**
	 * Gets the order.
	 *
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * Gets the suggestion.
	 *
	 * @return the suggestion
	 */
	public int getSuggestion() {
		return suggestion;
	}

	/**
	 * Gets the apology.
	 *
	 * @return the apology
	 */
	public int getApology() {
		return apology;
	}

	/**
	 * Gets the attach.
	 *
	 * @return the attach
	 */
	public int getAttach() {
		return attach;
	}

	/**
	 * Gets the info about date.
	 *
	 * @return the info about date
	 */
	public int getInfoAboutDate() {
		return infoAboutDate;
	}

	/**
	 * Gets the info about location.
	 *
	 * @return the info about location
	 */
	public int getInfoAboutLocation() {
		return infoAboutLocation;
	}

	/**
	 * Gets the info about money.
	 *
	 * @return the info about money
	 */
	public int getInfoAboutMoney() {
		return infoAboutMoney;
	}

	/**
	 * Gets the enumerate.
	 *
	 * @return the enumerate
	 */
	public int getEnumerate() {
		return enumerate;
	}

	/**
	 * Gets the gratitude.
	 *
	 * @return the gratitude
	 */
	public int getGratitude() {
		return gratitude;
	}

	/**
	 * Sets the gratitude.
	 *
	 * @param gratitude the new gratitude
	 */
	public void setGratitude(int gratitude) {
		this.gratitude = gratitude;
	}

	/**
	 * Gets the num tokens.
	 *
	 * @return the num tokens
	 */
	public int getNumTokens() {
		return numTokens;
	}

	/**
	 * Sets the num tokens.
	 *
	 * @param numTokens the new num tokens
	 */
	public void setNumTokens(int numTokens) {
		this.numTokens = numTokens;
	}
	

	
}




