
package kddm2TextMining;

import java.io.File;


/**
 * @author Alvaro Garcia Hortelano & Alvaro Perez Ramon
 * @version 3/6/2014
 *
 * Compare the number of each category of an analyzed email with the thresholds calculated before 
 * to decide if the writer of the email is either a sender or a receiver.
 */
public class Task2{

	
	/**
	 * 
	 *
	 * @param int tokens - number of tokens of the analyzed email
	 * @return int (from 0 to 10) the probability of being sender or receiver (based on the tokens)
	 */
	public int numTokens(int tokens){
		int result=0;
		//int sender = 858;
		int sender = 86;
		//int receiver = 348;
		int receiver = 35;
		if (tokens>sender){ 
			result=10;
			return result;
		}else if (tokens<receiver){ result=0;
		return result;}
		
		double senderPrev= sender-tokens;
		double receivPrev= tokens-receiver;

		double senderProb= 1-(senderPrev/(sender-receiver));
		double receivProb= 1-(receivPrev/(sender-receiver));


		if(senderProb>receivProb) {
			result = (int) (senderProb*10);}
		else{
			result = (int) ((1-receivProb)*10);}
		
		return result;
	}

	/**
	 * Num body.
	 *
	 * @param int tokens - number of bodies of the analyzed email
	 * @return the probability of being sender or receiver (based on the body)
	 */
	public int numBody(int tokens){
		int result=0;
		int sender = 5;
		int receiver = 2;
		if (tokens>sender){ 
			result=10;
			return result;
		}else if (tokens<receiver){ result=0;
		return result;}
		
		double senderPrev= sender-tokens;
		double receivPrev= tokens-receiver;

		double senderProb= 1-(senderPrev/(sender-receiver));
		double receivProb= 1-(receivPrev/(sender-receiver));


		if(senderProb>receivProb) {
			result = (int) (senderProb*10);}
		else{
			result=(int) ((1-receivProb)*10);}
		
		return result;
	}

	/**
	 * Num sentences.
	 *
	 * @param int tokens - number of sentences of the analyzed email
	 * @return int - the probability of being sender or receiver (based on the number of sentences)
	 */
	public int numSentences(int tokens){
		int result = 0;
		int sender = 6;
		int receiver = 4;
		if (tokens>sender){ 
			result=10;
			return result;
		}else if (tokens<receiver){ result=0;
		return result;}
		
		double senderPrev= sender-tokens;
		double receivPrev= tokens-receiver;

		double senderProb= 1-(senderPrev/(sender-receiver));
		double receivProb= 1-(receivPrev/(sender-receiver));


		if(senderProb>receivProb) {
			result = (int) (senderProb*10);}
		else{
			result=(int) ((1-receivProb)*10);}
		
		return result;
	}

	/**
	 * Num info about money.
	 *
	 * @param int tokens - number of info about money of the analyzed email
	 * @return int - the probability of being sender or receiver (based on the info about money)
	 */
	public int numInfoAboutMoney(int tokens){
		int result=0;
		int sender = 2;
		int receiver = 0;
		if (tokens>sender){ 
			result=10;
			return result;
		}else if (tokens<receiver){ result=0;
		return result;}
		
		double senderPrev= sender-tokens;
		double receivPrev= tokens-receiver;

		double senderProb= 1-(senderPrev/(sender-receiver));
		double receivProb= 1-(receivPrev/(sender-receiver));


		if(senderProb>receivProb) {
			result = (int) (senderProb*10);}
		else{
			result=(int) ((1-receivProb)*10);}
		
		return result;
	}

	/**
	 * Num info about location.
	 *
	 * @param tokens - number of info about location of the analyzed email
	 * @return int - the probability of being sender or receiver (based on the info about location)
	 */
	public int numInfoAboutLocation(int tokens){
		int result=0;
		int sender = 3;
		int receiver = 0;
		if (tokens>sender){ 
			result=10;
			return result;
		}else if (tokens<receiver){ result=0;
		return result;}
		
		double senderPrev= sender-tokens;
		double receivPrev= tokens-receiver;

		double senderProb= 1-(senderPrev/(sender-receiver));
		double receivProb= 1-(receivPrev/(sender-receiver));


		if(senderProb>receivProb) {
			result = (int) (senderProb*10);}
		else{
			result=(int) ((1-receivProb)*10);}
		
		return result;
	}

	/**
	 * Decide, taking into account how likely is that the writer is either a sender or a receiver.
	 *
	 * @param int body - probability of the body
	 * @param int tokens - probability of the tokens
	 * @param int sentences - probability of the sentences
	 * @param int money - probability of the money
	 * @param int location - probability of the location
	 * @return true, if successful
	 */
	public boolean decide(int body, int tokens, int sentences, int money, int location){
		boolean sender = true;
		if (tokens==0||tokens==1||tokens==2){
			if(body<5){
				sender=false;
				System.out.println("We are almost sure than the writer is a receiver");
			}
			else if(sentences<5&&money<5&&location<5){
				sender=false;
				System.out.println("We think that the writer is a receiver. But we are not sure at all");
			}
			else if(sentences>5&&money>5&&location>5){
				sender=true;
				System.out.println("We think that the writer is a sender. But we are not sure at all");
			}
			else{
				System.out.println("To be honest, we don't have any idea");}
		}
		else if (tokens==10||tokens==9||tokens==8){
			if(body>4){
				sender=true;
				System.out.println("We are almost sure than the writer is a sender");
			}
			else if(sentences<5&&money<5&&location<5){
				sender=false;
				System.out.println("We think that the writer is a receiver. But we are not sure at all");
			}
			else if(sentences>5&&money>5&&location>5){
				sender=true;
				System.out.println("We think that the writer is a sender. But we are not sure at all");
			}
			else{System.out.println("To be honest, we don't have any idea");}
		}
		else{
			if(sentences<5&&money<5&&location<5){
				sender=false;
				System.out.println("We think that the writer is a receiver. But we are not sure at all");
			}
			else if(sentences>5&&money>5&&location>5){
				sender=true;
				System.out.println("We think that the writer is a sender. But we are not sure at all");
			}
			
		}
		return sender;

	}

	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main (String[] args){
		Task1 task1=new Task1();
		Task2 task2 = new Task2();
		File file = new File("/Users/alvaro/Documents/TUGraz/KDDM2/Receivers/133");
		String mail="";
		mail = task1.readEmail(file);
		String[] sentences = null;
		sentences = task1.Task1Deteccion(mail);
		//int numSentences = sentences.length;
		for (String b: sentences){
			task1.decide( b, b.length());

		} 
		int tokens = task2.numTokens(task1.getNumTokens());
		System.out.println("Token probability: "+tokens);
		int body = task2.numBody(task1.getBody());
		System.out.println("Body probability: "+body);
		int sentence = task2.numSentences(sentences.length);
		System.out.println("Sentence probability: "+sentence);
		int money = task2.numInfoAboutMoney(task1.getInfoAboutMoney());
		System.out.println("Money probability: "+money);
		int location = task2.numInfoAboutLocation(task1.getInfoAboutLocation());	
		System.out.println("Location probability: "+location);
		boolean result = task2.decide(body, tokens, sentence, money, location);
		if(result)System.out.println("SENDER");
		if(!result)System.out.println("RECEIVER");

	}

}
