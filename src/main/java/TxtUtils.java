import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.collections4.map.CaseInsensitiveMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class TxtUtils {
	public static Set<String[]> getSetOfEnAndTags(String fileName) {
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
				frequencys.put(string[1], Integer.valueOf(string[0]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvException e) {
			e.printStackTrace();
		}


		return frequencys;
	}

	public static Map<String, String> getNotesWithTags(String sourceDictionaryName, String frequencyListName) {
		Set<String[]> notes = getSetOfEnAndTags(sourceDictionaryName);
		Map<String, Integer> frequency = getMapOfPositionAndEns(frequencyListName);
		Map<String, String> toReturn = new HashMap<>();

		for (String[] note : notes) {
			int max = 0;
			String tagsToAppend = "";
			String[] words = note[0].split(" ");
			for (String word : words) {
				if (word.contains("-")) {
					String[] split = word.split("-");
					for (String s : split) {
						Integer i = frequency.get(s);
						if (i != null && i > max) {
							max = i;
						}
					}
				}
				Integer i = frequency.get(word);
				if (i != null && i > max) {
					max = i;
				}
			}

			tagsToAppend += max + "k";

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
}
