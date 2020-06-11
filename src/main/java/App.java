import java.util.Arrays;
import java.util.Map;

public class App {
	public static void main(String[] args) {
		Map<String, String> notesWithTags = TxtUtils.getNotesWithTags("deck.txt", "frequency_list.csv");
		for (String s : notesWithTags.keySet()) {
			System.out.println(s + " " + notesWithTags.get(s));
		}

		IOUtils.writeFile(notesWithTags);
	}
}
