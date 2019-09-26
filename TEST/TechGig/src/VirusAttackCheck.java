import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class VirusAttackCheck {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int noOfTestCases = scanner.nextInt(); 
		
		for(int i=0;i<noOfTestCases;i++){
			String incidents = scanner.next();
			Map<Character, Integer> characterMap = getCharacterMap(incidents);
			Integer max = characterMap.values()
				      .stream()
				      .mapToInt(v -> v)
				      .max().orElseThrow(NoSuchElementException::new);
			Optional<Character> maxKeys = characterMap.entrySet()
		              .stream()
		              .filter(entry -> Objects.equals(entry.getValue(), max))
		              .map(Map.Entry::getKey).min(Comparator.comparing(String::valueOf));
			if(maxKeys.isPresent())
				System.out.println(maxKeys.get());
		}
		scanner.close();
	}

	private static Map<Character, Integer> getCharacterMap(String incidents) {
		Map<Character, Integer> characterMap = new HashMap<>();
		char[] chars = incidents.toCharArray();
		for(int i = 0;i<chars.length; i++){
			int newCount = 1;
			if(characterMap.containsKey(chars[i])){
				newCount=  characterMap.get(chars[i])+1;
			}
			characterMap.put(chars[i], newCount);
		}
		return characterMap;
	}

}
