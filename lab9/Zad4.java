import java.util.Arrays;
// import org.apache.commons.lang3.ArrayUtils;

public class Zad4 {

	private static <T> T[] sort(T[] input) {
		Object[] result = Arrays.copyOf(input, input.length);
		Arrays.sort(result);
		return (T[]) result;
	}

  private static <T> String arrayToString(T[] array) {
    String result = "{ ";
    for (int i = 0; i < array.length-1; i++) {
      result += array[i] + ", ";
    }
    result += array[array.length-1] + " }";
    return result;
  }

	public static void main(String[] args) {
//		int[] ints = { 5, 2, 7, 1, 3 };
//		int[] sortedInts = ArrayUtils.toPrimitive(sort(ArrayUtils.toObject(ints)));

		Integer[] ints = { 5, 2, 7, 1, 3 };
		Integer[] sortedInts = sort(ints);
    System.out.println(arrayToString(sortedInts));

		Double[] doubles = { 5.6, 2.4, 1.1, 6.9, 7.9 };
		Double[] doublesSorted = sort(doubles);
    System.out.println(arrayToString(doublesSorted));

		Character[] chars = { 'c', 'f', 'a', 'b' };
		Character[] sortedChars = sort(chars);
    System.out.println(arrayToString(sortedChars));

		String[] strings = { "cecylia", "barbara", "adam", "¿oliborz", "warszawka" };
		String[] stringsSorted = sort(strings);
    System.out.println(arrayToString(stringsSorted));

		Boolean[] bools = { true, false, true, true, false };
		Boolean[] boolsSorted = sort(bools);
    System.out.println(arrayToString(boolsSorted));
	}

}
