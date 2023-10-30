import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'lilysHomework' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */
     
    public static int countNum(List<Integer> arr, boolean reverse) {
        int count = 0;
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < arr.size(); i++) {
            index.put(arr.get(i), i);
        }
        
        List<Integer> clone = new ArrayList<>();
        for (int x : arr) {
            clone.add(x);
        }
        
        Collections.sort(arr);
        if (reverse) {
            Collections.reverse(arr);
        }

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) != clone.get(i)) {
                count++;
                int tmpindex = index.get(arr.get(i));
                index.replace(clone.get(i), tmpindex);
                //index.put(clone.get(i), tmpindex);   //on hackerrank use this       
                Collections.swap(clone, i, tmpindex);
                //otherwise
                // Integer tmp = clone.get(i);
                // clone.set(i, clone.get(tmpindex));
                // clone.set(tmpindex, tmp);
            }
        }        
        return count;        
    }

    public static int lilysHomework(List<Integer> arr) {
    // Write your code here
        List<Integer> clone = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) clone.add(arr.get(i));
        int count1 = Result.countNum(arr, false);
        int count2 = Result.countNum(clone, true);
    
        return Math.min(count1, count2);
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String[] arrTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrTemp[i]);
            arr.add(arrItem);
        }

        int result = Result.lilysHomework(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
