import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SharkMatch {

	public static String user1 = "A";
	public static String user2 = "B";

	public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	String[] arr = {user1,user2};
		int noOfTestCases = scanner.nextInt();
		for (int i = 0; i < noOfTestCases; i++) {
			int r = scanner.nextInt();
			int p = scanner.nextInt();
			List<String[]> allLists = new ArrayList<>();
			createCombinations(arr, r, allLists);
			int numberofCombi = getNumberOfCombi(allLists, p);
			System.out.println(numberofCombi);
			
		}
		
		
	}

	private static int getNumberOfCombi(List<String[]> allLists, int factor ) {
		int count = 0;

		for (int k = 0; k < allLists.size(); k++) {
			String[] elements = allLists.get(k);
			int user1Count = 0;
			int user2Count = 0;
			boolean add = true;
			for (int m = 0; m < elements.length; m++) {
				if (elements[m].equals(user1)) {
					user1Count++;
				}
				if (elements[m].equals(user2)) {
					user2Count++;
				}
				if (user1Count < user2Count * factor) {
					add = false;
					break;
				}

			}
			if (add) {
				count++;
			}

		}

		return count;
	}

	static void createCombinations(String[] set, int k, List<String[]> allLists) 
	{ 
	    int n = set.length;  
	    createCombinations(set, "", n, k, allLists); 
	} 
	static void createCombinations(String[] set, String prefix, int n, int k, List<String[]> allLists) {

		// Base case: k is 0,
		// print prefix
		if (k == 0) {
			char[] chars = prefix.toCharArray();
			String[] combination = new String[chars.length];
			for(int i=0;i<chars.length;i++){
				combination[i] = chars[i]+"";
			}
			allLists.add(combination);
			return;
		}

		// One by one add all characters
		// from set and recursively
		// call for k equals to k-1
		for (int i = 0; i < n; ++i) {

			// Next character of input added
			String newPrefix = prefix + set[i];

			// k is decreased, because
			// we have added a new character
			createCombinations(set, newPrefix, n, k - 1, allLists);
		}
	}

}
