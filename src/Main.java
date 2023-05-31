import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String palindrome = "cainve, beevaes c?an i seae";
        String notPalindrome = "cainve, beevaese c?an i seae";
        String trueString = "Should return true: ";
        String falseString = "Should return false: ";
        System.out.println(trueString + isPalindromePermutationWithHashMap(palindrome));
        System.out.println(falseString + isPalindromePermutationWithHashMap(notPalindrome));
        System.out.println(trueString + isPalindromePermutationWithIntArray(palindrome));
        System.out.println(falseString + isPalindromePermutationWithIntArray(notPalindrome));
        System.out.println(trueString + isPalindromePermutationWithBitVector(palindrome));
        System.out.println(falseString + isPalindromePermutationWithBitVector(notPalindrome));
    }
    public static Boolean isPalindromePermutationWithHashMap(String string) {
        Map<Character, Integer> map = countLettersWithMap(string);
        boolean hasOddNumberOfLetters = false;
        for(Map.Entry<Character, Integer> entry : map.entrySet()) {
            int entryValue = entry.getValue();
            if(!isEven(entryValue) && isEven(string.length())) {
                return false;
            } else if(!isEven(entryValue) && hasOddNumberOfLetters == false) {
                hasOddNumberOfLetters = true;
            } else if(!isEven(entryValue) && hasOddNumberOfLetters == true) {
                return false;
            }
        }
        return true;
    }
    private static Map<Character, Integer> countLettersWithMap(String string) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < string.length(); i++) {
            char character = string.charAt(i);
            if(character >= 'a' && character <= 'z') {
                int count = 0;
                if(map.containsKey(character)) {
                    count = map.get(character);
                }
                map.put(character, ++count);
            }
        }
        return map;
    }
    private static Boolean isEven(int entryValue) {
        return entryValue % 2 == 0;
    }
    public static Boolean isPalindromePermutationWithIntArray(String string) {
        int[] letters = countLettersWithArray(string);
        boolean hasOddNumberOfLetters = false;
        for(int i = 0; i < letters.length; i++) {
            if(!isEven(letters[i]) && isEven(string.length())) {
                return false;
            } else if(!isEven(letters[i]) && hasOddNumberOfLetters == false) {
                hasOddNumberOfLetters = true;
            } else if(!isEven(letters[i]) && hasOddNumberOfLetters == true) {
                return false;
            }
        }
        return true;
    }
    private static int[] countLettersWithArray(String string) {
        int[] letters = new int[26];
        for(int i = 0; i < string.length(); i++) {
            char character = string.charAt(i);
            if(character >= 'a' && character <= 'z') {
                int element = character - 'a';
                letters[element]++;
            }
        }
        return letters;
    }
    public static Boolean isPalindromePermutationWithBitVector(String string) {
        int checker = countLettersWithBitVector(string);
        boolean hasOddNumberOfLetters = false;
        for(int i = 0; i < 26; i++) {
            int mask = 1 << i;
            if((checker & mask) != 0 && isEven(string.length())) {
                return false;
            } else if((checker & mask) != 0 && hasOddNumberOfLetters == false) {
                hasOddNumberOfLetters = true;
            } else if((checker & mask) != 0 && hasOddNumberOfLetters == true) {
                return false;
            }
        }
        return true;
    }
    private static int countLettersWithBitVector(String string) {
        int checker = 0;
        for(int i = 0; i < string.length(); i++) {
            char character = string.charAt(i);
            if(character >= 'a' && character <= 'z') {
                int bitElement = character - 'a';
                int mask = 1 << bitElement;
                if((checker & mask) == 0) {
                    checker |= mask;
                } else {
                    checker &= ~(mask);
                }
            }
        }
        return checker;
    }
}