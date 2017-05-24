// Adele Miller
// 5/2/2015
// 
// THIS CODE IS PRESENTED "AS IS."
// (have fun with that.)
//
// This program is for formatting texts from Matt Joseph's
// http://devadvance.com/sms-backup-reader/
// XML converted, which maintains timestamps and "Sent:"
// and "Received" notes, and formats them as a chat.
// Ex. Adele: [text message]
//
//
//	   Allie: [text message}
// This does not remove the phone number and contact name
// present at the top of the text file. 

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class SMSMain {
	public static boolean lastReceived = true;
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner console = new Scanner(System.in);
		System.out.print("What is the name of the text file? ");
		Scanner input = new Scanner(new File(console.nextLine()));

		System.out.print("What is the name of the output text file? ");
		PrintStream output = new PrintStream(new File(console.nextLine()));

		System.out.print("What should \"Sent\" be replaced by? ");
		String sentReplace = console.nextLine();

		System.out.print("What should \"Received:\" be replaced by? ");
		String receivedReplace = console.nextLine();

		while (input.hasNextLine()) {
			String current = input.nextLine();
			if (current.contains("Received:")) {
				current = changeTokens(current, 9, receivedReplace, output, true); // Respective char Ct 9 and 5
				lastReceived = true;
			} else if (current.contains("Sent:")) {
				current = changeTokens(current, 5, sentReplace, output, false);
				lastReceived = false;
			}
			output.print(current + "\n"); // writes line to output
		}
	}

	public static String changeTokens(String line, int startLength, String replaceStr, PrintStream output, boolean isReceived) {
		if (!(lastReceived == isReceived)) { output.print("\n"); } // Spaces out lines of dialogue if from different person
		line = line.substring(startLength); // Skips starting token
		return replaceStr + line.substring(30); // Length of the time stamp is 30 characters
	}
}
