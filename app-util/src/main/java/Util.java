import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    public static void countChars(String file) {

        Map<Character, Integer> map = new HashMap<>();
        List<Character> symbols = new ArrayList<>();

        for (int i = 0; i < file.length(); i++) {
            if (!symbols.contains(file.charAt(i))) {
                if (Character.isLetter(file.charAt(i))) {
                    symbols.add(file.charAt(i));
                }
            }
        }

        for (Character symbol : symbols) {
            int count = 0;
            for (int j = 0; j < file.length(); j++) {
                if (symbol.equals(file.charAt(j))) {
                    count++;
                }
            }
            map.put(symbol, count);
        }

        sortByValue(map).forEach((k, v) -> System.out.println("Symbol: " + k + ". It's count: " + v));
        System.out.println();

    }

    public static void countWords(String file) {

        List<String> words = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        Pattern pattern = Pattern.compile("\\w+", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(file);

        while (matcher.find()) {
            if (!words.contains(matcher.group())) {
                for (String word : words) {
                    if (!matcher.group().equalsIgnoreCase(word)) {
                        break;
                    }
                }
                words.add(matcher.group().toLowerCase());
            }
        }

        Matcher matcher1;
        for (String s :
                words) {
            int count = 0;
            matcher1 = pattern.matcher(file);
            while (matcher1.find()) {
                if (s.equalsIgnoreCase(matcher1.group())) {
                    count++;
                }
            }
            map.put(s, count);
        }

        sortByValue(map).forEach((k, v) -> System.out.println("Word: " + k + ". It's count: " + v));
        System.out.println();

    }

    public static void reverseSentences(String file) {
        Pattern pattern = Pattern.compile(".+?\\.");
        Matcher matcher = pattern.matcher(file);
        List<String> sentences = new ArrayList<>();

        while ((matcher.find())) {
            sentences.add(matcher.group());
        }

        Pattern pattern1 = Pattern.compile("\\w+", Pattern.UNICODE_CHARACTER_CLASS);
        for (String sentence :
                sentences) {
            System.out.println("Sentence before reversing: " + sentence);

            List<String> words = new ArrayList<>();
            Matcher matcher1 = pattern1.matcher(sentence);
            while (matcher1.find()) {
                words.add(matcher1.group().toLowerCase());
            }
            Collections.reverse(words);

            StringJoiner joiner = new StringJoiner(" ");
            for (String word : words) {
                joiner.add(word);
            }
            sentence = String.valueOf(joiner);
            System.out.println("Sentence after reversing: " + sentence + "\n");

        }

    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

}