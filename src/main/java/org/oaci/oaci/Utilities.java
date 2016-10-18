package org.oaci.oaci;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Utilities {

	public static File getFile(String fileName) {
		// Get file from resources folder
		ClassLoader classLoader = Utilities.class.getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		return file;
	}
	
	public static File readFile(String fileName) {

		StringBuilder result = new StringBuilder("");

		try (Scanner scanner = new Scanner(getFile(fileName))) {

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.append(line).append("\n");
			}

			scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return new File(result.toString());

	}
}
