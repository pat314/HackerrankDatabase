import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'poisonousPlants' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY p as parameter.
     */

    public static int poisonousPlants(List<Integer> p) {
        // Write your code here
        /**
         * Ý tưởng bài toán ở đây là giả sử ta đã xác định được vị trí cây thứ
         * i trong dãy là cây sẽ không bị chết (tức là đã loại bỏ được toàn bộ 
         * số cây sẽ chết trong khoảng cây từ vị trí 0 đến i), và một dãy cây
         * tiếp theo chết trong ngày thứ k. 
         * (Số lượng phần tử dãy bằng 0 thì chứng tỏ cây tiếp theo cũng sẽ không chết)
         * Điều kiện để cho cây tiếp theo sau dãy
         * cây đó không bị chết trong ngày thứ k + 1 là cây đó phải có lượng pesticide 
         * nhỏ hơn cả cây thứ i (và do đó cây sẽ sống).
         * 
         * Ví dụ: p[] =  6 5 8 4 7 10 9
         * Cây thứ i = 3, p[i] = 4 sẽ là cây sống và 1 dãy các cây tiếp theo (i = 4,
         *  i =  5) sẽ chết trong ngày thứ k. Như vậy điều kiện để cho cây tiếp theo
         * sau dãy cây đó, i = 6 (p[i] = 9) sống là nó phải có lượng pesticide nhỏ 
         * hơn cả cây thứ i = 3. Tuy nhiên, do p[6] = 9 > p[3] = 4 nên cây đó sẽ chết
         * vào ngày k+1
         */
        //Biến max để lưu giá trị ngày để không còn cây nào chết nữa
        int max = 0;
        // Ta sẽ sử dụng stack như sau: ta sẽ duyệt dãy lưu lượng pesticide của từng cây
        //stack ở đáy sẽ lưu cây thứ i thỏa mãn sẽ không bao giờ chết, các stack tiếp theo
        //sẽ lưu 1 dãy các cây sẽ chết trong ngày thứ k. Ta sẽ kiểm tra cây tiếp theo sau
        //dãy đó có chết trong ngày thứ k+1 không
        Stack<List<Integer>> stack = new Stack<>();
        
        for (int i = 0; i < p.size(); i++) {
            //biến day lưu lượng ngày mà 1 cây poisonous có thể sống 
            //(nếu sau cùng, day = 0 thì cây đó sẽ sống mãi)
            int day = 0;
            int plant = p.get(i);
            //kiểm tra xem cây đó có nhỏ hơn toàn bộ cây trong stack không
            //(như vậy nếu cây lớn hơn bất kì 1 cây nào đó trong stack 
            //thì cây đó sẽ là cây poisonous và sẽ chết trong ngày k+1)
            //vòng lặp sẽ dừng nếu cây lớn hơn bất kì 1 cây trong stack 
            //hoặc khi stack rỗng (tức là cây đó nhỏ hơn tất cả các cây trong stack)
            while (!stack.empty() && stack.peek().get(0) >= plant) {
                day = Math.max(day, stack.pop().get(1));
            }
            //Nếu cây đó lớn hơn 1 cây nào đó trong stack thì chứng tỏ 
            //cây đó sẽ chết trong ngày k+1
            if (!stack.empty()) day++;
            //Nếu không thì cây đó sẽ sống và ta sẽ dùng cây đó thêm vào stack đang rỗng
            //để làm điều kiện kiểm tra dãy cây tiếp theo.
            else day = 0;
            
            //biễn max sẽ lưu số ngày nhiều nhất để 1 cây poisonous có thể sống
            //hay nói cách khác là số ngày để loại bỏ hết cây poisonous
            max = Math.max(max, day);
            
            //thủ tục thêm cây vào stack
            List<Integer> arr = new ArrayList<>();
            arr.add(plant);
            arr.add(day);
            stack.push(arr);
        }
        
        return max;
    }

}

//class solution của hackerrank
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String[] pTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> p = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int pItem = Integer.parseInt(pTemp[i]);
            p.add(pItem);
        }

        int result = Result.poisonousPlants(p);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}