import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'twoStacks' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER maxSum
     *  2. INTEGER_ARRAY a
     *  3. INTEGER_ARRAY b
     */

    public static int twoStacks(int maxSum, List<Integer> a, List<Integer> b) {
    // Write your code here
        int i = 0, j = 0;
        int count = 0;
        long sum = 0;
        
        //Khi làm bài này, ta có thể nghĩ ngay tới phương pháp tham lam, bằng
        //cách lấy những phần tử bé nhất từ 2 đầu của stack. Tuy nhiên, cách này sẽ
        //bị fail vài test case. 
        //Ta sẽ tiến hành theo cách như sau:
        //Ta giả sử ban đầu chỉ có 1 stack và ta sẽ lấy hết những phần tử trên đầu 
        //stack để thu được 1 tổng thỏa mãn nhỏ hơn maxSum. 
        //Sau đó, ta tiến hành lấy thêm những phần tử từ stack thứ 2, nếu tổng các
        //stack lấy ra từ 2 stack lớn hơn tổng maxSum quy định thì ta bỏ bớt những
        //phần tử lấy từ stack đầu tiên ra. 
        //So sánh tổng hai lần lấy stack(lấy từ 1 stack và lấy thêm từ stack 2),
        //cách lấy nào nhiều hơn thì cách đó chính là cách lấy được nhiều nhất
        
        //Giả sử khi bỏ đi tổng cộng a stack từ stack A,
        //thêm vào tổng cộng b stack từ stack B:
        //Nếu -a + b > 0 => lần lấy thứ 2 tối ưu hơn, ngược lại lần lấy đầu tiên là 
        //tối ưu nhất

        //get all of the element in a as you can
        while (i < a.size() && sum + a.get(i) <= maxSum) {
            sum += a.get(i++);
            count++;
        }
        
        //take the elements in the stack b
        while (j < b.size() && i >= 0) {
            sum += b.get(j++);
            //if the sum exceeds the maxSum, exclude the elements get from the first stack
            while (i > 0 && sum > maxSum) {
                sum -= a.get(--i);
            }
            //if this approach can get more elements, we update the count
            if (sum <= maxSum && count < i + j) count = i + j; 
        }
        return count;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int g = Integer.parseInt(bufferedReader.readLine().trim());

        for (int gItr = 0; gItr < g; gItr++) {
            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int n = Integer.parseInt(firstMultipleInput[0]);

            int m = Integer.parseInt(firstMultipleInput[1]);

            int maxSum = Integer.parseInt(firstMultipleInput[2]);

            String[] aTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> a = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                int aItem = Integer.parseInt(aTemp[i]);
                a.add(aItem);
            }

            String[] bTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> b = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                int bItem = Integer.parseInt(bTemp[i]);
                b.add(bItem);
            }

            int result = Result.twoStacks(maxSum, a, b);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
