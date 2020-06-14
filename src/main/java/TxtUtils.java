import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.collections4.map.CaseInsensitiveMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class TxtUtils {
	public static Set<String[]> getSetOfNotes(String fileName) {
		BufferedReader bufferedReader = IOUtils.getFileReader(fileName);
		Set<String[]> setOfNotes = new HashSet<>();

		try {
			String fields;
			while ((fields = bufferedReader.readLine()) != null) {
				String[] split = fields.split("\t", 7);
				String[] firstAndLastString = ArrayUtils.getFirstAndLastString(split);
				setOfNotes.add(firstAndLastString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return setOfNotes;
	}

	public static Map<String, Integer> getMapOfPositionAndEns(String fileName) {
		CSVReader csvReader = IOUtils.getCSVReader(fileName);
		Map<String, Integer> frequencys = new CaseInsensitiveMap<>();

		try {
			List<String[]> strings = csvReader.readAll();
			for (String[] string : strings) {
				String s = string[2];
				if (!s.isEmpty()) {
					frequencys.put(string[1], Integer.valueOf(s));
				} else {
					frequencys.put(string[1], 0);
				}
			}
		} catch (IOException | CsvException e) {
			e.printStackTrace();
		}


		return frequencys;
	}

	public static Map<String, String> getNotesWithTags(String sourceDictionaryName, String frequencyListName) {
		Set<String[]> notes = getSetOfNotes(sourceDictionaryName);
		Map<String, Integer> frequency = getMapOfPositionAndEns(frequencyListName);
		Map<String, String> toReturn = new HashMap<>();

		for (String[] note : notes) {
			int min = 10000000;
			String tagsToAppend = "";
			String[] words = note[0].split(" ");
			for (String word : words) {
				if (word.contains("-")) {
					String[] split = word.split("-");
					for (String s : split) {
						min = getMin(frequency, min, s);
					}
				}

				if (word.contains("/")) {
					String[] split = word.split("/");
					for (String s : split) {
						min = getMin(frequency, min, s);
					}
				}
				min = getMin(frequency, min, word);
			}

			StringBuilder s = new StringBuilder(String.valueOf(min));

			int length = s.length();
			for (int i = 0; i < 7 - length; i++) {
				s.insert(0,0);
			}

			tagsToAppend += s;

			String[] tags = note[1].split(" ");
			for (String tag : tags) {
				if ("marked".equals(tag)) {
					tagsToAppend += " marked";
				}
			}

			toReturn.put(note[0], tagsToAppend);
		}

		return toReturn;
	}

	public static int getMin(Map<String, Integer> frequency, int min, String word) {
		Integer i = frequency.get(word);
		if (i != null && i < min) {
			min = i;
		} else if (i == null) {
			return 0;
		}
		return min;
	}
}
