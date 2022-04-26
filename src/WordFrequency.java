public class WordFrequency implements Comparable<WordFrequency> {

    String words;
    Integer count;

    public WordFrequency(String words, Integer count) {
        this.words = words;
        this.count = count;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "WordFrequency [count=" + count + ", words=" + words + "]";
    }

    @Override
    public int compareTo(WordFrequency wordsCompare) {

        if (this.count > wordsCompare.count)
            return 1;
        if (this.count < wordsCompare.count)
            return -1;

        return 0;
    }

}
