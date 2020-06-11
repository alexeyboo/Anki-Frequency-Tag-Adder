import java.util.Arrays;

public class ArrayUtils {
	public static String[] getFirstAndLastString(String[] split) {
		String[] toReturn = new String[2];
		System.arraycopy(split, 0, toReturn, 0, 1);
		System.arraycopy(split, split.length - 1, toReturn, 1, 1);

		return toReturn;
	}
}
