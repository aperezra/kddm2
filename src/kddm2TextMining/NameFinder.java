package kddm2TextMining;

import java.io.InputStream;
import java.io.IOException;
//import de.l3s.boilerpipe.BoilerpipeProcessingException;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;




public class NameFinder {



	static String polla = "Pierre Vinken, 61 years old, will join the board as a nonexecutive director Nov. 29. Mr. Vinken is chairman of Elsevier N.V., the Dutch publishing group. Rudolph Agnew, 55 years old and former chairman of Consolidated Gold Fields PLC, was named a director of this British industrial conglomerate.";

	String sentence[] = new String[]{
			"Pierre",
			"Vinken",
			"is",
			"61",
			"years",
			"old",
			"."
	};

	public Span[] pruebaDeteccion (String polla){
		NameFinderME nameFinder = null;
		InputStream modelIn = null;
		Span[] array = null;
		try {
			// Loading sentence detection model
			modelIn = getClass().getResourceAsStream("/en-ner-person.bin");
			final TokenNameFinderModel model = new TokenNameFinderModel(modelIn);
			modelIn.close();

			nameFinder = new NameFinderME(model);
			array = nameFinder.find(sentence);


		} catch (final IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (modelIn != null) {
				try {
					modelIn.close();
				} catch (final IOException e) {} // oh well!
			}
		}

		return array;
	}
}