import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public ArrayList<String> wordsWithoutFilter = new ArrayList<>();
    public ArrayList<String> words = new ArrayList<>();
    public int[][] filter = new int[2][100];

    public Main(String fileNameFromResources) {
        fillWords(fileNameFromResources);
    }

    public void addWord(String word){
        int hashOne = Math.abs(word.hashCode())%100;
        int hashTwo = mySimpleHash(word);
        words.add(word);
        filter[0][hashOne]++;
        filter[1][hashTwo]++;
    }

    public void removeWord(String word){
        int hashOne = Math.abs(word.hashCode())%100;
        int hashTwo = mySimpleHash(word);
        words.remove(word);
        filter[0][hashOne]--;
        filter[1][hashTwo]--;
    }

    public boolean maybeContainsWord(String word){
        int hashOne = Math.abs(word.hashCode())%100;
        int hashTwo = mySimpleHash(word);
        return (filter[0][hashOne]>0)&&(filter[1][hashTwo]>0);
    }

    public int mySimpleHash(String word){
        char[] chars = word.toCharArray();
        int sum = 0;
        for(int i = 0; i < chars.length; i++){
            sum+=(chars[i]+7)*2;
        }
        return sum%100;
    }

    public void fillWords(String fileNameFromResources){
        ArrayList<String> rows = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/" + fileNameFromResources));
            String s;
            while((s = br.readLine())!=null){
                rows.add(s);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        splitRowsOnWords(rows);
    }

    public void splitRowsOnWords(ArrayList<String> rows){
        for(String row : rows){
            String[] wordsFromRow = row.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
            Arrays.stream(wordsFromRow).forEach(word -> {
                if(!word.equals(" ")){
                    wordsWithoutFilter.add(word);
                    addWord(word);
                }
            });
        }
    }

}

