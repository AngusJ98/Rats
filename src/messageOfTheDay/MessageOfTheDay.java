package messageOfTheDay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MessageOfTheDay {

	private static HttpURLConnection connection;
	
	private static String getInfoFromUrl(String urlStr) {
		BufferedReader reader;
		String line;
		StringBuffer responseContent = new StringBuffer();
		
		try {
			URL url = new URL(urlStr);
			connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			
			int status = connection.getResponseCode();
			//System.out.println(status);
			
			if(status > 299) {
				reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				while((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			} else {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			}
			String message = responseContent.toString();
			return message;

		} catch (MalformedURLException e) {
			e.printStackTrace();
			return "URL Error";
		} catch (IOException e) {
			e.printStackTrace();
			return "IO Error";
		} finally {
			connection.disconnect();
		}
		
	}

	private static String getSolution() {
		String unsolvedMessage = getInfoFromUrl("http://cswebcat.swansea.ac.uk/puzzle");
		char[] solutionArr = new char[unsolvedMessage.length()];
		int shift = 1;
		for (int i = 0; i < unsolvedMessage.length();i++) {
			char newLetter;
			if(i % 2 == 0) {
				newLetter = shiftLetterBack(unsolvedMessage.charAt(i), shift);
			} else {
				newLetter = shiftLetterForward(unsolvedMessage.charAt(i), shift);
			}
			solutionArr[i] = newLetter;
			shift++;

		}
		return (new String(solutionArr) + "CS-230");
	}

	private static char shiftLetterForward(char c, int shift) {
		char next = c;
		for (int i = 0; i < shift; i++) {
			if (next == 'z') {
				next = 'a';
			} else if (next == 'Z') {
				next = 'A';
			} else {
				//convert to int and add 1
				int nextAsInt = (int) next;
				next = (char) (nextAsInt + 1);
			}
		}
		return next;
	}

	private static char shiftLetterBack(char c, int shift) {
		char next = c;
		for (int i = 0; i < shift; i++) {
			if (next == 'a') {
				next = 'z';
			} else if (next == 'A') {
				next = 'Z';
			} else {
				//conver
				int nextAsInt = (int) next;
				next = (char) (nextAsInt - 1);
			}
		}
		return next;
	}

	public static String getMotd() {
		String solutionPart = getSolution();
		solutionPart = solutionPart.length() + solutionPart;

		return getInfoFromUrl("http://cswebcat.swansea.ac.uk/message?solution=" + solutionPart);
	}

}
