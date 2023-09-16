package shared;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.ceil;
import static java.lang.Math.min;

public record SpellChecker(DictionaryManager dictionaryManager) {
    private static final int EDIT_DISTANCE_THRESHOLD = 4;

    /**
     * Return a list of words with edit distance from input less than certain threshold.
     * Edit distance is Levenshtein distance.
     *
     * @param word input of the program with incorrect spelling
     * @return list of words similar with input
     */
    public List<String> correctSpelling(String word) {
        final var wordLength = word.length();
        final var windowSize = (int)ceil(1.0 * wordLength / 3);

        // searching queries is created by omit some characters and replace them with a placeholder
        final var queries = IntStream.rangeClosed(0, wordLength - windowSize)
                .boxed()
                .map(x -> word.substring(0, x) + "%" + word.substring(x + windowSize))
                .collect(Collectors.toList());

        final var similarWords = dictionaryManager.searchKeyWord(queries, 100);


        return similarWords.stream()
                // map each entry with its edit distance from the word
                .map(w -> Map.entry(w, calculateEditDistance(word, w)))
                // get only suitable word .ie: edit distance less than threshold
                .filter(e -> e.getValue() < EDIT_DISTANCE_THRESHOLD)
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /**
     * Calculate Levenshtein distance using iterative with two matrix rows.
     * Pseudocode of this algorithm is in
     * <a href="https://en.wikipedia.org/wiki/Levenshtein_distance">Levenshtein distance</a>.
     */
     private static int calculateEditDistance(String a, String b) {
         final var aLength = a.length();
         final var bLength = b.length();

        // create two work vectors of integer distances
        var v0 = new int[bLength + 1];
        var v1 = new int[bLength + 1];

        // initialize v0 (the previous row of distances)
        // this row is A[0][i]: edit distance for an empty s
        // the distance is just the number of characters to delete from t
        for (var i = 0; i <= bLength; ++i) {
            v0[i] = i;
        }

        for (var i = 0; i < aLength; ++i) {
            // calculate v1 (current row distances) from the previous row v0

            // first element of v1 is A[i + 1][0]
            // edit distance is delete (i + 1) chars from s to match empty t
            v1[0] = i + 1;

            // use formula to fill in the rest of the row
            for (var j = 0; j < bLength; ++j) {
                // calculating costs for A[i + 1][j + 1]
                final var deletionCost = v0[j + 1] + 1;
                final var insertionCost = v1[j] + 1;

                var substitutionCost = 0;
                if (a.charAt(i) == b.charAt(j)) {
                    substitutionCost = v0[j];
                } else {
                    substitutionCost = v0[j] + 1;
                }

                v1[j + 1] = min(deletionCost, min(insertionCost, substitutionCost));
            }

            // copy v1 (current row) to v0 (previous row) for next iteration
            // since data in v1 is always invalidated, a swap without copy could be more efficient
            final var tmp = v0;
            v0 = v1;
            v1 = tmp;
        }

        return v0[bLength];
    }
}
