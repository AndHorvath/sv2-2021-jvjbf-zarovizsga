package sentences;

import java.util.List;

public class SentenceTransformer {

    public String shortenSentence(String sentence) {
        validateParameter(sentence);
        if (isLongSentence(sentence)) {
            return getShortenedForm(sentence);
        }
        return sentence;
    }

    // --- private methods ----------------------------------------------------

    private void validateParameter(String sentence) {
        if (!doesStartWithUppercase(sentence)) {
            throw new IllegalArgumentException("Must start with capital letter!");
        }
        if (!doesEndWithPunctuationMark(sentence)) {
            throw new IllegalArgumentException("Must end with . ! or ?");
        }
    }

    private boolean doesStartWithUppercase(String sentence) {
        return Character.isUpperCase(sentence.charAt(0));
    }

    private boolean doesEndWithPunctuationMark(String sentence) {
        List<Character> punctuationMarks = List.of('.', '!', '?');
        return punctuationMarks.contains(sentence.charAt(sentence.length() - 1));
    }

    private boolean isLongSentence(String sentence) {
        return sentence.chars().filter(Character::isWhitespace).count() >= 4;
    }

    private String getShortenedForm(String sentence) {
        String[] sentenceArray = sentence.split(" ");
        return sentenceArray[0] + " ... " + sentenceArray[sentenceArray.length - 1];
    }
}