import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class App {

    static ArrayList<String> wordsList = new ArrayList<>();

    static HashMap<String, Integer> wordCount = new HashMap<>();

    static List<WordFrequency> wordFrequencyList = new ArrayList<>();

    /**
     * 1: Open input and output files.
     * 2: Reading each line from a file and append to string builder to store in
     * words.
     * 3: All of the lines from the txt file now are stored in storedWords, ready
     * for the array.
     * 4: Close buffer, no use for it anymore.
     * 5: take storeWords and split at every comma.
     * 6: Add words to hash map, if it does not exist key value set it to one,
     * otherwise replace key value and add one to the count.
     * 7: Iterate through the HashMap (wordCount) i.e through all the keys.
     * 8: create an ArrayList of WordFrequency objects.
     * Populate the ArrayList with the data stored in the HashMap
     * 9: Sort through the list
     * 
     * @param args
     * @throws Exception
     * @author Gianluca Venditti
     * 
     */

    public static void main(String[] args) throws Exception {

        // 1
        FileReader file = new FileReader("res/words.txt");
        FileWriter write = new FileWriter("res/words.html");
        BufferedReader bufferedReader = new BufferedReader(file);
        StringBuilder build = new StringBuilder();
        String storeWords;

        // 2
        while ((storeWords = bufferedReader.readLine()) != null) {
            build.append(storeWords);
        }

        // 3
        storeWords = build.toString();

        // 4
        bufferedReader.close();

        // 5 Splits at every comma
        String[] wordsList = storeWords.split("[ ,]+");

        // 6
        for (String addWord : wordsList) {
            Integer ctr = wordCount.get(addWord);

            if (wordCount.get(addWord) == null) {
                wordCount.put(addWord, 1);
            }

            else {
                wordCount.put(addWord, ctr + 1);
            }

        }

        StringBuilder buildingHTML = new StringBuilder();

        buildingHTML.append("<style>").append("\n");
        buildingHTML.append("* { font-size: 15px}").append("\n");
        buildingHTML.append(
                "table { margin: auto; color: #000000;  background-color: #ffffff; border-collapse: collapse; border-width: 2px; border-color: #000000; border-style: solid;}")
                .append("\n");
        buildingHTML.append(
                "table td, table th {width: 50%; border-width: 2px; border-color: #000000; border-style: solid; padding: 10px;}")
                .append("\n");

        buildingHTML.append("</style>").append("\n");

        buildingHTML.append("<table>").append("\n");
        buildingHTML.append("<tr>").append("\n");
        buildingHTML.append("<th> Text Input </th>").append("<th>Wordcount HTML Output</th>");
        buildingHTML.append("</tr>").append("\n");
        buildingHTML.append("<tr>").append("\n");
        buildingHTML.append("<td>").append(storeWords).append("/<td>");
        buildingHTML.append("<table>").append("\n");

        // 7
        for (String key : wordCount.keySet()) {
            // System.out.println(key);
            // System.out.println(wordCount.get(key));
            buildingHTML.append("<tr>").append("\n");
            buildingHTML.append("<td>").append(key).append("</td>").append("<td>").append(wordCount.get(key))
                    .append("</td>");
            buildingHTML.append("</tr>").append("\n");
        }

        // Closing Tags
        buildingHTML.append("</table>");
        buildingHTML.append("</td>");
        buildingHTML.append("</tr>");
        buildingHTML.append("</table>");

        // Append html builder to words to html
        write.append(buildingHTML.toString());

        // Close file
        write.close();

        // 8
        for (String key : wordCount.keySet()) {
            WordFrequency wordFrequency = new WordFrequency(key, wordCount.get(key));
            wordFrequencyList.add(wordFrequency);
        }

        // 9
        Collections.sort(wordFrequencyList);

        FileWriter writeSorted = new FileWriter("res/sorted.html");

        StringBuilder buildingSorted = new StringBuilder();

        buildingSorted.append("<style>").append("\n");
        buildingSorted.append("* { font-size: 15px}").append("\n");
        buildingSorted.append(
                "table { color: #000000;  background-color: #ffffff; border-collapse: collapse; border-width: 2px; border-color: #000000; border-style: solid;}")
                .append("\n");
        buildingSorted.append(
                "table td, table th { border-width: 2px; border-color: #000000; border-style: solid; padding: 10px;}")
                .append("\n");
        buildingSorted.append("</style>").append("\n");

        buildingSorted.append("<table>").append("\n");
        buildingSorted.append("<tr>").append("\n");
        buildingSorted.append("</tr>").append("\n");
        buildingSorted.append("<tr>").append("\n");
        buildingSorted.append("<table>").append("\n");

        for (WordFrequency wordFrequency : wordFrequencyList) {
            buildingSorted.append("<tr>").append("\n");
            buildingSorted.append("<td>").append(wordFrequency.words).append("</td>").append("<td>")
                    .append(wordFrequency.count)
                    .append("</td>");
            buildingSorted.append("</tr>").append("\n");
        }

        buildingSorted.append("</table>");
        buildingSorted.append("</td>");
        buildingSorted.append("</tr>");
        buildingSorted.append("</table>");

        writeSorted.append(buildingSorted.toString());

        writeSorted.close();

    }

}