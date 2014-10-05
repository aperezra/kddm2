package kddm2TextMining;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


// TODO: Auto-generated Javadoc
/**
 * The Class ThresholdCalculator.
 *
 * @author Alvaro Garcia Hortelano & Alvaro Perez Ramon
 * @version 1/6/2014
 * 
 * Calculate the thresholds that will be used to decide in task2.
 */
public class ThresholdCalculator{

	/** The greeting receiver. */
	public int greetingReceiver;
	
	/** The farewell receiver. */
	public int farewellReceiver;
	
	/** The body receiver. */
	public int bodyReceiver;
	
	/** The question receiver. */
	public int questionReceiver;
	
	/** The order receiver. */
	public int orderReceiver;
	
	/** The suggestion receiver. */
	public int suggestionReceiver;
	
	/** The apology receiver. */
	public int apologyReceiver;
	
	/** The attach receiver. */
	public int attachReceiver;
	
	/** The info about date receiver. */
	public int infoAboutDateReceiver;
	
	/** The info about location receiver. */
	public int infoAboutLocationReceiver;
	
	/** The info about money receiver. */
	public int infoAboutMoneyReceiver;
	
	/** The enumerate receiver. */
	public int enumerateReceiver;
	
	/** The greeting sender. */
	public int greetingSender;
	
	/** The farewell sender. */
	public int farewellSender;
	
	/** The body sender. */
	public int bodySender;
	
	/** The question sender. */
	public int questionSender;
	
	/** The order sender. */
	public int orderSender;
	
	/** The suggestion sender. */
	public int suggestionSender;
	
	/** The apology sender. */
	public int apologySender;
	
	/** The attach sender. */
	public int attachSender;
	
	/** The info about date sender. */
	public int infoAboutDateSender;
	
	/** The info about location sender. */
	public int infoAboutLocationSender;
	
	/** The info about money sender. */
	public int infoAboutMoneySender;
	
	/** The enumerate sender. */
	public int enumerateSender;
	
	/** The num sentences sender. */
	public int numSentencesSender;
	
	/** The num sentences receiver. */
	public int numSentencesReceiver;
	
	/** The num tokens sender. */
	public int numTokensSender;
	
	/** The gratitude sender. */
	public int gratitudeSender;
	
	/** The gratitude receiver. */
	public int gratitudeReceiver;
	
	/** The num tokens receiver. */
	public int numTokensReceiver;

	
	
	
	
	
	/**
	 * Instantiates a new threshold calculator.
	 */
	public ThresholdCalculator() {
		super();
		this.greetingReceiver = 0;
		this.farewellReceiver = 0;
		this.bodyReceiver = 0;
		this.questionReceiver = 0;
		this.orderReceiver = 0;
		this.suggestionReceiver = 0;
		this.apologyReceiver = 0;
		this.attachReceiver = 0;
		this.infoAboutDateReceiver = 0;
		this.infoAboutLocationReceiver = 0;
		this.infoAboutMoneyReceiver = 0;
		this.enumerateReceiver = 0;
		this.greetingSender = 0;
		this.farewellSender = 0;
		this.bodySender = 0;
		this.questionSender = 0;
		this.orderSender = 0;
		this.suggestionSender = 0;
		this.apologySender = 0;
		this.attachSender = 0;
		this.infoAboutDateSender = 0;
		this.infoAboutLocationSender = 0;
		this.infoAboutMoneySender = 0;
		this.enumerateSender = 0;
		this.numSentencesSender = 0;
		this.numSentencesReceiver = 0;
		this.numTokensSender = 0;
		this.gratitudeSender = 0;
		this.gratitudeReceiver = 0;
		this.numTokensReceiver = 0;
	}

	



	/**
	 * Listar directorios. List the directories in a folder
	 *
	 * @param File with the path of the directory.
	 * @return array with all the files in the directory of the param
	 */
	private File[] listarDirectorios(File file){
		File[] ficheros = file.listFiles();
		return ficheros;	
	}



	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main (String[] args) throws IOException{
		FileWriter file = new FileWriter("/Users/alvaro/Documents/TUGraz/KDDM2/InfoTask2/info");
		PrintWriter pw =  new PrintWriter(file);
		try
		{	
			ThresholdCalculator thresholds = new ThresholdCalculator();
			//Training set with Senders' bodies
			File senderfile = new File ("/Users/alvaro/Documents/TUGraz/KDDM2/Senders");
			//Training set with Receivers' bodies
			File receiverfile = new File ("/Users/alvaro/Documents/TUGraz/KDDM2/Receivers");
			File[] receiverficheros = thresholds.listarDirectorios(receiverfile);
			File[] senderficheros = thresholds.listarDirectorios(senderfile);
			//For each file of Receivers training set we execute Task1
			for (File f: receiverficheros){
				if(!f.getName().equals(".DS_Store")){
					Task1 task1 = new Task1();
					String mail = task1.readEmail(f);
					String[] sentences = task1.Task1Deteccion(mail);
					thresholds.setNumSentencesReceiver(thresholds.getNumSentencesReceiver()+sentences.length);

					for (String b: sentences){
						task1.decide( b, b.length());
						//Calculating how many of each category each file has.
						thresholds.setGreetingReceiver(thresholds.getGreetingReceiver()+task1.getGreeting());
						thresholds.setFarewellReceiver(thresholds.getFarewellReceiver()+task1.getFarewell());
						thresholds.setBodyReceiver(thresholds.getBodyReceiver()+task1.getBody());
						thresholds.setQuestionReceiver(thresholds.getQuestionReceiver() + task1.getQuestion());
						thresholds.setOrderReceiver(thresholds.getOrderReceiver() + task1.getOrder());
						thresholds.setSuggestionReceiver(thresholds.getSuggestionReceiver() + task1.getSuggestion());
						thresholds.setApologyReceiver(thresholds.getApologyReceiver() + task1.getApology());
						thresholds.setAttachReceiver(thresholds.getAttachReceiver() + task1.getAttach());
						thresholds.setInfoAboutDateReceiver(thresholds.getInfoAboutDateReceiver() + task1.getInfoAboutDate());
						thresholds.setInfoAboutLocationReceiver(thresholds.getInfoAboutLocationReceiver() + task1.getInfoAboutLocation());
						thresholds.setInfoAboutMoneyReceiver(thresholds.getInfoAboutMoneyReceiver() + task1.getInfoAboutMoney());
						thresholds.setEnumerateReceiver(thresholds.getEnumerateReceiver() + task1.getEnumerate());
						thresholds.setNumTokensReceiver(thresholds.getNumTokensReceiver()+task1.getNumTokens());
						thresholds.setGratitudeReceiver(thresholds.getGratitudeReceiver()+task1.getGratitude());

					}
				}

			}
			//For each file of Senders training set we execute Task1
			for (File f: senderficheros){
				if(!f.getName().equals(".DS_Store")){
					Task1 task1 = new Task1();
					String mail = task1.readEmail(f);
					String[] sentences = task1.Task1Deteccion(mail);
					//int numSentences = sentences.length;
					thresholds.setNumSentencesSender(thresholds.getNumSentencesSender()+sentences.length);
					for (String b: sentences){
						task1.decide( b, b.length());
						//Calculating how many of each category each file has.
						thresholds.setGreetingSender(thresholds.getGreetingSender()+task1.getGreeting());
						thresholds.setFarewellSender(thresholds.getFarewellSender()+task1.getFarewell());
						thresholds.setBodySender(thresholds.getBodySender()+task1.getBody());
						thresholds.setQuestionSender(thresholds.getQuestionSender() + task1.getQuestion());
						thresholds.setOrderSender(thresholds.getOrderSender() + task1.getOrder());
						thresholds.setSuggestionSender(thresholds.getSuggestionSender() + task1.getSuggestion());
						thresholds.setApologySender(thresholds.getApologySender() + task1.getApology());
						thresholds.setAttachSender(thresholds.getAttachSender() + task1.getAttach());
						thresholds.setInfoAboutDateSender(thresholds.getInfoAboutDateSender() + task1.getInfoAboutDate());
						thresholds.setInfoAboutLocationSender(thresholds.getInfoAboutLocationSender() + task1.getInfoAboutLocation());
						thresholds.setInfoAboutMoneySender(thresholds.getInfoAboutMoneySender() + task1.getInfoAboutMoney());
						thresholds.setEnumerateSender(thresholds.getEnumerateSender() + task1.getEnumerate());
						thresholds.setNumTokensSender(thresholds.getNumTokensSender()+task1.getNumTokens());
						thresholds.setGratitudeSender(thresholds.getGratitudeSender()+task1.getGratitude());

					}
				}

			}

			//Calculating the mean value dividing by the total number of files
			thresholds.setGreetingReceiver(thresholds.getGreetingReceiver()/(receiverficheros.length-1));
			thresholds.setFarewellReceiver(thresholds.getFarewellReceiver()/(receiverficheros.length-1));
			thresholds.setBodyReceiver(thresholds.getBodyReceiver()/(receiverficheros.length-1));
			thresholds.setQuestionReceiver(thresholds.getQuestionReceiver()/(receiverficheros.length-1));
			thresholds.setOrderReceiver(thresholds.getOrderReceiver()/(receiverficheros.length-1));
			thresholds.setSuggestionReceiver(thresholds.getSuggestionReceiver()/(receiverficheros.length-1));
			thresholds.setApologyReceiver(thresholds.getApologyReceiver()/(receiverficheros.length-1));
			thresholds.setAttachReceiver(thresholds.getAttachReceiver()/(receiverficheros.length-1));
			thresholds.setInfoAboutDateReceiver(thresholds.getInfoAboutDateReceiver()/(receiverficheros.length-1));
			thresholds.setInfoAboutLocationReceiver(thresholds.getInfoAboutLocationReceiver()/(receiverficheros.length-1));
			thresholds.setInfoAboutMoneyReceiver(thresholds.getInfoAboutMoneyReceiver()/(receiverficheros.length-1));
			thresholds.setEnumerateReceiver(thresholds.getEnumerateReceiver()/(receiverficheros.length-1));
			thresholds.setNumSentencesReceiver(thresholds.getNumSentencesReceiver()/(receiverficheros.length-1));
			thresholds.setNumTokensReceiver(thresholds.getNumTokensReceiver()/(receiverficheros.length-1));
			thresholds.setGratitudeReceiver(thresholds.getGratitudeReceiver()/(receiverficheros.length-1));

			//Calculating the mean value dividing by the total number of files
			thresholds.setGreetingSender(thresholds.getGreetingSender()/(senderficheros.length-1));
			thresholds.setFarewellSender(thresholds.getFarewellSender()/(senderficheros.length-1));
			thresholds.setBodySender(thresholds.getBodySender()/(senderficheros.length-1));
			thresholds.setQuestionSender(thresholds.getQuestionSender()/(senderficheros.length-1));
			thresholds.setOrderSender(thresholds.getOrderSender()/(senderficheros.length-1));
			thresholds.setSuggestionSender(thresholds.getSuggestionSender()/(senderficheros.length-1));
			thresholds.setApologySender(thresholds.getApologySender()/(senderficheros.length-1));
			thresholds.setAttachSender(thresholds.getAttachSender()/(senderficheros.length-1));
			thresholds.setInfoAboutDateSender(thresholds.getInfoAboutDateSender()/(senderficheros.length-1));
			thresholds.setInfoAboutLocationSender(thresholds.getInfoAboutLocationSender()/(senderficheros.length-1));
			thresholds.setInfoAboutMoneySender(thresholds.getInfoAboutMoneySender()/(senderficheros.length-1));
			thresholds.setEnumerateSender(thresholds.getEnumerateSender()/(senderficheros.length-1));
			thresholds.setNumSentencesSender(thresholds.getNumSentencesSender()/(senderficheros.length-1));
			thresholds.setNumTokensSender(thresholds.getNumTokensSender()/(senderficheros.length-1));
			thresholds.setGratitudeSender(thresholds.getGratitudeSender()/(senderficheros.length-1));

			//Printing mean values in a file, what will be usefull for calculating the thresholds in thresholds.
			pw.println("Greetings: SENDER: " +thresholds.getGreetingSender() + " RECEIVER: " + thresholds.getGreetingReceiver());
			pw.println("Farewells: SENDER: " +thresholds.getFarewellSender() + " RECEIVER: " + thresholds.getFarewellReceiver());
			pw.println("Body: SENDER: " +thresholds.getBodySender() + " RECEIVER: " + thresholds.getBodyReceiver());
			pw.println("Question: SENDER: " +thresholds.getQuestionSender() + " RECEIVER: " + thresholds.getQuestionReceiver());
			pw.println("Order: SENDER: " +thresholds.getOrderSender() + " RECEIVER: " +thresholds.getOrderReceiver());
			pw.println("Suggestion: SENDER: " +thresholds.getSuggestionSender() + " RECEIVER: " + thresholds.getSuggestionReceiver());
			pw.println("Apology: SENDER: " +thresholds.getApologySender() + " RECEIVER: " + thresholds.getApologyReceiver());
			pw.println("Attach: SENDER: " +thresholds.getAttachSender() + " RECEIVER: " + thresholds.getAttachReceiver());
			pw.println("Info About Date: SENDER: " +thresholds.getInfoAboutDateSender() + " RECEIVER: " + thresholds.getInfoAboutDateReceiver());
			pw.println("Info About Location: SENDER: " +thresholds.getInfoAboutLocationSender() + " RECEIVER: " + thresholds.getInfoAboutLocationReceiver());
			pw.println("Info About Money: SENDER: " +thresholds.getInfoAboutMoneySender() + " RECEIVER: " + thresholds.getInfoAboutMoneyReceiver());
			pw.println("Enumeration: SENDER: " +thresholds.getEnumerateSender() + " RECEIVER: " + thresholds.getEnumerateReceiver());
			pw.println("Num sentences: SENDER: " +thresholds.getNumSentencesSender() + " RECEIVER: " + thresholds.getNumSentencesReceiver());
			pw.println("Num tokens: SENDER: " +thresholds.getNumTokensSender() + " RECEIVER: " + thresholds.getNumTokensReceiver());
			pw.println("Gratitude: SENDER: "+thresholds.getGratitudeSender()+ " RECEIVER: " + thresholds.getGratitudeReceiver());

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
	
	
	
	
	//Getters and Setters for each field.
	
	/**
	 * Gets the gratitude sender.
	 *
	 * @return the gratitude sender
	 */
	public int getGratitudeSender() {
		return gratitudeSender;
	}

	/**
	 * Sets the gratitude sender.
	 *
	 * @param gratitudeSender the new gratitude sender
	 */
	public void setGratitudeSender(int gratitudeSender) {
		this.gratitudeSender = gratitudeSender;
	}

	/**
	 * Gets the gratitude receiver.
	 *
	 * @return the gratitude receiver
	 */
	public int getGratitudeReceiver() {
		return gratitudeReceiver;
	}

	/**
	 * Sets the gratitude receiver.
	 *
	 * @param gratitudeReceiver the new gratitude receiver
	 */
	public void setGratitudeReceiver(int gratitudeReceiver) {
		this.gratitudeReceiver = gratitudeReceiver;
	}

	/**
	 * Gets the num tokens sender.
	 *
	 * @return the num tokens sender
	 */
	public int getNumTokensSender() {
		return numTokensSender;
	}

	/**
	 * Sets the num tokens sender.
	 *
	 * @param numTokensSender the new num tokens sender
	 */
	public void setNumTokensSender(int numTokensSender) {
		this.numTokensSender = numTokensSender;
	}

	/**
	 * Gets the num tokens receiver.
	 *
	 * @return the num tokens receiver
	 */
	public int getNumTokensReceiver() {
		return numTokensReceiver;
	}

	/**
	 * Sets the num tokens receiver.
	 *
	 * @param numTokensReceiver the new num tokens receiver
	 */
	public void setNumTokensReceiver(int numTokensReceiver) {
		this.numTokensReceiver = numTokensReceiver;
	}

	/**
	 * Gets the num sentences sender.
	 *
	 * @return the num sentences sender
	 */
	public int getNumSentencesSender() {
		return numSentencesSender;
	}

	/**
	 * Sets the num sentences sender.
	 *
	 * @param d the new num sentences sender
	 */
	public void setNumSentencesSender(int d) {
		this.numSentencesSender = d;
	}

	/**
	 * Gets the num sentences receiver.
	 *
	 * @return the num sentences receiver
	 */
	public int getNumSentencesReceiver() {
		return numSentencesReceiver;
	}

	/**
	 * Sets the num sentences receiver.
	 *
	 * @param d the new num sentences receiver
	 */
	public void setNumSentencesReceiver(int d) {
		this.numSentencesReceiver = d;
	}

	/**
	 * Gets the greeting receiver.
	 *
	 * @return the greeting receiver
	 */
	public int getGreetingReceiver() {
		return greetingReceiver;
	}

	/**
	 * Sets the greeting receiver.
	 *
	 * @param greetingReceiver the new greeting receiver
	 */
	public void setGreetingReceiver(int greetingReceiver) {
		this.greetingReceiver = greetingReceiver;
	}

	/**
	 * Gets the farewell receiver.
	 *
	 * @return the farewell receiver
	 */
	public int getFarewellReceiver() {
		return farewellReceiver;
	}

	/**
	 * Sets the farewell receiver.
	 *
	 * @param farewellReceiver the new farewell receiver
	 */
	public void setFarewellReceiver(int farewellReceiver) {
		this.farewellReceiver = farewellReceiver;
	}

	/**
	 * Gets the body receiver.
	 *
	 * @return the body receiver
	 */
	public int getBodyReceiver() {
		return bodyReceiver;
	}

	/**
	 * Sets the body receiver.
	 *
	 * @param bodyReceiver the new body receiver
	 */
	public void setBodyReceiver(int bodyReceiver) {
		this.bodyReceiver = bodyReceiver;
	}

	/**
	 * Gets the question receiver.
	 *
	 * @return the question receiver
	 */
	public int getQuestionReceiver() {
		return questionReceiver;
	}

	/**
	 * Sets the question receiver.
	 *
	 * @param questionReceiver the new question receiver
	 */
	public void setQuestionReceiver(int questionReceiver) {
		this.questionReceiver = questionReceiver;
	}

	/**
	 * Gets the order receiver.
	 *
	 * @return the order receiver
	 */
	public int getOrderReceiver() {
		return orderReceiver;
	}

	/**
	 * Sets the order receiver.
	 *
	 * @param orderReceiver the new order receiver
	 */
	public void setOrderReceiver(int orderReceiver) {
		this.orderReceiver = orderReceiver;
	}

	/**
	 * Gets the suggestion receiver.
	 *
	 * @return the suggestion receiver
	 */
	public int getSuggestionReceiver() {
		return suggestionReceiver;
	}

	/**
	 * Sets the suggestion receiver.
	 *
	 * @param suggestionReceiver the new suggestion receiver
	 */
	public void setSuggestionReceiver(int suggestionReceiver) {
		this.suggestionReceiver = suggestionReceiver;
	}

	/**
	 * Gets the apology receiver.
	 *
	 * @return the apology receiver
	 */
	public int getApologyReceiver() {
		return apologyReceiver;
	}

	/**
	 * Sets the apology receiver.
	 *
	 * @param apologyReceiver the new apology receiver
	 */
	public void setApologyReceiver(int apologyReceiver) {
		this.apologyReceiver = apologyReceiver;
	}

	/**
	 * Gets the attach receiver.
	 *
	 * @return the attach receiver
	 */
	public int getAttachReceiver() {
		return attachReceiver;
	}

	/**
	 * Sets the attach receiver.
	 *
	 * @param attachReceiver the new attach receiver
	 */
	public void setAttachReceiver(int attachReceiver) {
		this.attachReceiver = attachReceiver;
	}

	/**
	 * Gets the info about date receiver.
	 *
	 * @return the info about date receiver
	 */
	public int getInfoAboutDateReceiver() {
		return infoAboutDateReceiver;
	}

	/**
	 * Sets the info about date receiver.
	 *
	 * @param infoAboutDateReceiver the new info about date receiver
	 */
	public void setInfoAboutDateReceiver(int infoAboutDateReceiver) {
		this.infoAboutDateReceiver = infoAboutDateReceiver;
	}

	/**
	 * Gets the info about location receiver.
	 *
	 * @return the info about location receiver
	 */
	public int getInfoAboutLocationReceiver() {
		return infoAboutLocationReceiver;
	}

	/**
	 * Sets the info about location receiver.
	 *
	 * @param infoAboutLocationReceiver the new info about location receiver
	 */
	public void setInfoAboutLocationReceiver(int infoAboutLocationReceiver) {
		this.infoAboutLocationReceiver = infoAboutLocationReceiver;
	}

	/**
	 * Gets the info about money receiver.
	 *
	 * @return the info about money receiver
	 */
	public int getInfoAboutMoneyReceiver() {
		return infoAboutMoneyReceiver;
	}

	/**
	 * Sets the info about money receiver.
	 *
	 * @param infoAboutMoneyReceiver the new info about money receiver
	 */
	public void setInfoAboutMoneyReceiver(int infoAboutMoneyReceiver) {
		this.infoAboutMoneyReceiver = infoAboutMoneyReceiver;
	}

	/**
	 * Gets the enumerate receiver.
	 *
	 * @return the enumerate receiver
	 */
	public int getEnumerateReceiver() {
		return enumerateReceiver;
	}

	/**
	 * Sets the enumerate receiver.
	 *
	 * @param enumerateReceiver the new enumerate receiver
	 */
	public void setEnumerateReceiver(int enumerateReceiver) {
		this.enumerateReceiver = enumerateReceiver;
	}

	/**
	 * Gets the greeting sender.
	 *
	 * @return the greeting sender
	 */
	public int getGreetingSender() {
		return greetingSender;
	}

	/**
	 * Sets the greeting sender.
	 *
	 * @param greetingSender the new greeting sender
	 */
	public void setGreetingSender(int greetingSender) {
		this.greetingSender = greetingSender;
	}

	/**
	 * Gets the farewell sender.
	 *
	 * @return the farewell sender
	 */
	public int getFarewellSender() {
		return farewellSender;
	}

	/**
	 * Sets the farewell sender.
	 *
	 * @param farewellSender the new farewell sender
	 */
	public void setFarewellSender(int farewellSender) {
		this.farewellSender = farewellSender;
	}

	/**
	 * Gets the body sender.
	 *
	 * @return the body sender
	 */
	public int getBodySender() {
		return bodySender;
	}

	/**
	 * Sets the body sender.
	 *
	 * @param bodySender the new body sender
	 */
	public void setBodySender(int bodySender) {
		this.bodySender = bodySender;
	}

	/**
	 * Gets the question sender.
	 *
	 * @return the question sender
	 */
	public int getQuestionSender() {
		return questionSender;
	}

	/**
	 * Sets the question sender.
	 *
	 * @param questionSender the new question sender
	 */
	public void setQuestionSender(int questionSender) {
		this.questionSender = questionSender;
	}

	/**
	 * Gets the order sender.
	 *
	 * @return the order sender
	 */
	public int getOrderSender() {
		return orderSender;
	}

	/**
	 * Sets the order sender.
	 *
	 * @param orderSender the new order sender
	 */
	public void setOrderSender(int orderSender) {
		this.orderSender = orderSender;
	}

	/**
	 * Gets the suggestion sender.
	 *
	 * @return the suggestion sender
	 */
	public int getSuggestionSender() {
		return suggestionSender;
	}

	/**
	 * Sets the suggestion sender.
	 *
	 * @param suggestionSender the new suggestion sender
	 */
	public void setSuggestionSender(int suggestionSender) {
		this.suggestionSender = suggestionSender;
	}

	/**
	 * Gets the apology sender.
	 *
	 * @return the apology sender
	 */
	public int getApologySender() {
		return apologySender;
	}

	/**
	 * Sets the apology sender.
	 *
	 * @param apologySender the new apology sender
	 */
	public void setApologySender(int apologySender) {
		this.apologySender = apologySender;
	}

	/**
	 * Gets the attach sender.
	 *
	 * @return the attach sender
	 */
	public int getAttachSender() {
		return attachSender;
	}

	/**
	 * Sets the attach sender.
	 *
	 * @param attachSender the new attach sender
	 */
	public void setAttachSender(int attachSender) {
		this.attachSender = attachSender;
	}

	/**
	 * Gets the info about date sender.
	 *
	 * @return the info about date sender
	 */
	public int getInfoAboutDateSender() {
		return infoAboutDateSender;
	}

	/**
	 * Sets the info about date sender.
	 *
	 * @param infoAboutDateSender the new info about date sender
	 */
	public void setInfoAboutDateSender(int infoAboutDateSender) {
		this.infoAboutDateSender = infoAboutDateSender;
	}

	/**
	 * Gets the info about location sender.
	 *
	 * @return the info about location sender
	 */
	public int getInfoAboutLocationSender() {
		return infoAboutLocationSender;
	}

	/**
	 * Sets the info about location sender.
	 *
	 * @param infoAboutLocationSender the new info about location sender
	 */
	public void setInfoAboutLocationSender(int infoAboutLocationSender) {
		this.infoAboutLocationSender = infoAboutLocationSender;
	}

	/**
	 * Gets the info about money sender.
	 *
	 * @return the info about money sender
	 */
	public int getInfoAboutMoneySender() {
		return infoAboutMoneySender;
	}

	/**
	 * Sets the info about money sender.
	 *
	 * @param infoAboutMoneySender the new info about money sender
	 */
	public void setInfoAboutMoneySender(int infoAboutMoneySender) {
		this.infoAboutMoneySender = infoAboutMoneySender;
	}

	/**
	 * Gets the enumerate sender.
	 *
	 * @return the enumerate sender
	 */
	public int getEnumerateSender() {
		return enumerateSender;
	}

	/**
	 * Sets the enumerate sender.
	 *
	 * @param enumerateSender the new enumerate sender
	 */
	public void setEnumerateSender(int enumerateSender) {
		this.enumerateSender = enumerateSender;
	}


}



