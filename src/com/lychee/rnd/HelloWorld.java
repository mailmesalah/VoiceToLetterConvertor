package com.lychee.rnd;

import javax.speech.*;
import javax.speech.recognition.*;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

public class HelloWorld extends ResultAdapter {
	static Recognizer rec;

	// Receives RESULT_ACCEPTED event: print it, clean up, exit
	public void resultAccepted(ResultEvent e) {
		Result r = (Result)(e.getSource());
		ResultToken tokens[] = r.getBestTokens();

		for (int i = 0; i < tokens.length; i++)
			System.out.print(tokens[i].getSpokenText() + " ");
		System.out.println();

		// Deallocate the recognizer and exit
		try {
			rec.deallocate();
		} catch (EngineException | EngineStateError e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.exit(0);
	}

	public static void main(String args[]) {
		//try {
			// Create a recognizer that supports English.		
			try {
				
				rec = Central.createRecognizer(
								new EngineModeDesc(Locale.ENGLISH));
				 
				System.out.println(rec);
				System.out.println(System.getProperty("user.home"));
							
				 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e+" Central.createRecognizer(new EngineModeDesc(Locale.ENGLISH))");
			}
			
			// Start up the recognizer
			try {				
				rec.allocate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e+" rec.allocate()");
			}
	 
			// Load the grammar from a file, and enable it
			FileReader reader = null;
			try {
				reader = new FileReader("Grammer.txt");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e+" FileReader");
			}
			RuleGrammar gram = null;
			try {
				gram = rec.loadJSGF(reader);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e+" rec.loadJSGF()");
			}
			gram.setEnabled(true);
	
			// Add the listener to get results
			rec.addResultListener(new HelloWorld());
	
			// Commit the grammar
			try {
				rec.commitChanges();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e+" rec.commitChanges()");
			}
	
			// Request focus and start listening
			rec.requestFocus();
			try {
				rec.resume();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e+" rec.resume()");
			}
		//} catch (Exception e) {
		//	System.out.println(e);
		//}
	}
}
