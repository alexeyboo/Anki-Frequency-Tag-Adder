import com.opencsv.CSVReader;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IOUtils {
	static String name;
	static String filePath = new File("").getAbsolutePath();


	public static BufferedReader getFileReader(String fileName) {
		BufferedReader bufferedReader = null;

		try {
			name = filePath + "\\src\\main\\resources\\" + fileName;
			bufferedReader = new BufferedReader(new FileReader(name));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return bufferedReader;
	}

	public static CSVReader getCSVReader(String fileName) {
		CSVReader csvReader = null;
		String csvName;
		try {
			csvName = filePath + "\\src\\main\\resources\\" + fileName;
			csvReader = new CSVReader(new FileReader(csvName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return csvReader;
	}

	public static void writeFile(Map<String, String> myMapWithFrequency) {
		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(name.replace(".txt", "_withtags.txt")));

			for (String s : myMapWithFrequency.keySet()) {
				bufferedWriter.write(s + "\t" + myMapWithFrequency.get(s));
				bufferedWriter.newLine();
				bufferedWriter.flush();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
