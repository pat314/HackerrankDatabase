import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'getMax' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts STRING_ARRAY operations as parameter.
     */

     //Các hàm được dùng
     //split(" "): tách các từ cách nhau bởi dấu cách " " trong string thành mảng các từ
     //Integer.parseInt(String s): chuyển số bằng string thành số
     //Collections.max(stack): tìm số lớn nhất trong stack

    public static List<Integer> getMax(List<String> operations) {
    // Write your code here
        Stack<Integer> stack = new Stack<>();
        List<Integer> ans = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < operations.size(); i++) {
            String[] tmp = operations.get(i).split(" ");
            
            switch (tmp[0]) {
                case "1":
                {
                    int num = Integer.parseInt(tmp[1]);
                    if (max < num) max = num;
                    stack.push(num);
                    break;
                }
                
                case "2":
                {
                    int remove = stack.pop();
                    if (remove == max) {
                        if (stack.empty()) {
                            max = 0;
                        } else {
                            max = Collections.max(stack);
                        }
                    }
                    break;
                }
                
                case "3":
                {
                    ans.add(max);
                    break;
                }
            }
        }
        
        
        return ans;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> ops = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String opsItem = bufferedReader.readLine();
            ops.add(opsItem);
        }

        List<Integer> res = Result.getMax(ops);

        for (int i = 0; i < res.size(); i++) {
            bufferedWriter.write(String.valueOf(res.get(i)));

            if (i != res.size() - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

