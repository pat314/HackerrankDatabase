import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'andXorOr' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY a as parameter.
     */

    public static int andXorOr(List<Integer> a) {
    // Write your code here
    //Ý tưởng là ta sẽ dùng 1 stack để duyệt tất cả các đoạn con
    //Trong mỗi đoạn con ta chỉ cẩn lấy 2 phần tử bé nhất đoạn
    //Như vậy, trong stack, ta sẽ chứa 1 phẩn tử bé nhất đoạn con
    //Các phần tử tiếp theo ta xét nếu lớn hơn pt trên cùng của stack
    //ta sẽ push vào stack, nếu gặp phần tử bé hơn phàn tử trên cùng
    //ta sẽ pop stack đến khi nào phần tử trên cùng nhỏ hơn phần tử đang xét
    //cùng lúc đó ta sẽ tính biểu thức của yêu cầu và lưu lại gtri lớn nhất
    //sau mỗi lần tính
        int result = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < a.size(); i++) {        
            while (!stack.empty()) {
                result = Math.max(result, Result.S(stack.peek(), a.get(i)));
                
                if (stack.peek() > a.get(i)) {
                    stack.pop();
                } else break;
            }
            stack.push(a.get(i));
        }
        return result;
    }
    
    public static int S(int x, int y) {
        return ((x & y) ^ (x | y)) & (x ^ y);
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int aCount = Integer.parseInt(bufferedReader.readLine().trim());

        String[] aTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> a = new ArrayList<>();

        for (int i = 0; i < aCount; i++) {
            int aItem = Integer.parseInt(aTemp[i]);
            a.add(aItem);
        }

        int result = Result.andXorOr(a);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
