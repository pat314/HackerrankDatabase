import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class pair {
    private int count;
    private int n;
    
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public int getN() {
        return n;
    }
    public void setN(int n) {
        this.n = n;
    }
    public pair(int n, int count) {
        this.n = n;
        this.count = count;
    }
}


class Result {

    /*
     * Complete the 'downToZero' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER n as parameter.
     */

    public static int downToZero(int n) {
    // Write your code here

    /*
     * Ý tưởng bài này là ta sẽ duyệt tất cả các trường hợp có thể để làm cho n trở về 0
     * mỗi lần duyệt thì ta sẽ dùng queue để lưu kết quả duyệt gồm có giá trị của n và 
     * số bước để có giá trị n đó. Tại lần duyệt thứ i qua tất các các cách, nếu một cách nào đó cho ta một kết quả duyệt mà giá trị n = 0 thì số bước tương tứng với lần duyệt đó chính là số bước nhỏ nhất để có thể làm cho trở về 0 bằng bằng việc áp dụng 2 quy tắc như đề bài. 
     * (Bài này giống như phép duyệt cây quyết định)
     */

     //IMPLEMENTATION

        //Biến count lưu số bước nhỏ nhất để làm n trở về 0
        int count = 0;
        
        //Set memo để đánh dấu sự xuất hiện của giá trị n = x (0 <= x < n).
        //Giả sử tại decision thứ i ta thực hiện 1 hành động, hành động này 
        //sẽ thu được n = x. Nếu n = x chưa xuất hiện trong set thì ta sẽ 
        //thực hiện hành động đó và lưu lịch sử hành động đó vào queue 
        //(lịch sử gồm giá trị x và số bước tương ứng để đạt giá trị n = x).
        //Như vậy, trong queue lưu lần đầu tiên giá trị n = x xuất hiện cùng với số bước 
        //để đạt giá trị n = x. Đó cũng tương ứng với số bước nhỏ nhất để đạt tới 
        //giá trị n = x. Thật vậy:
        
        //Ví dụ: n = 9, ta muốn thu n = 3
        //Cách 1: n = max(3, 3) = 3 => 1 bước
        //Cách 2: n = n-1
        //n = n-1
        //n = n-1
        //n = max(2,3) = 3 => 4 bước
        //Như vậy là ta sẽ chỉ cần lưu (3,1) tương ứng với để thu được giá trị 3 từ n = 9
        //thì ta chỉ cần 1 bước
        Set<Integer> memo = new HashSet<>();
        
        //Hàng đợi dùng để lưu kết quả duyệt, gồm bộ các cặp số (n, count) 
        //tương ứng với giá trị n = x và số bước nhỏ nhất để đạt tới giá trị n = x
        Queue<pair> queue = new LinkedList<>();
        
        //Nếu tại đầu hàng đợi ta có 1 kết quả duyệt mà n <= 1 thì chứng tỏ ta 
        //đã tới đích (biến n về 0) hoặc chỉ cần thêm 1 bước để tới đích 
        if (n <= 1) {
                if (n == 1) count += 1;
                return count;
        }
        
        //Gọi cách 1: n--; cách 2 n = max (a, b)
        //Ta duyệt như sau: giả sử tại decision thứ i, ta kiểm tra giá trị n sẽ thu 
        //được bằng cách 1 đã xuất hiện chưa, nếu chưa thì ta thực hiện
        if (!memo.contains(n-1)) {
            memo.add(n-1);
            pair e = new pair(n-1, count+1);
            queue.add(e);
        }
        
        //Giả sử tại decision thứ i, ta kiểm tra giá trị các giá trị của n sẽ thu 
        //được bằng cách 2 đã xuất hiện chưa, nếu chưa thì ta thực hiện        
        for (int i = (int) Math.sqrt(n); i >= 2; i--) {
            if (n % i == 0) {
                int tmp = Math.max(i, n/i);
                if (!memo.contains(tmp)) {
                    memo.add(tmp);
                    pair e = new pair(tmp, count+1);
                    queue.add(e);
                }
            }
        }

        //Tiếp tục duyệt các decision lần lượt tại đầu các queue cho đến khi 
        //có 1 decision cho ta n = 0        
        while (queue.isEmpty() == false) {
            //Lấy pt đầu hàng đợi để duyệt
            pair item = queue.remove();
            n = item.getN();
            count = item.getCount();
            
            //nearly reach the goal!
            if (n <= 1) {
                if (n == 1) count += 1;
                break;
            }
            
            //tiếp tục thực hiện theo decision 1
            if (!memo.contains(n-1)) {
                memo.add(n-1);
                pair e = new pair(n-1, count+1);
                queue.add(e);
            }
            
            //tiếp tục thực hiện theo decision 2
            for (int i = (int) Math.sqrt(n); i >= 2; i--) {
                if (n % i == 0) {
                    int tmp = Math.max(i, n/i);
                    if (!memo.contains(tmp)) {
                        memo.add(tmp);
                        pair e = new pair(tmp, count+1);
                        queue.add(e);
                    }
                }
            }
            
        }

        //Sau vòng lặp trên ta sẽ thu được số lần ít nhất để có n = 0. 
        //Trả về kết quả và kết thúc hàm
        return count;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        for (int qItr = 0; qItr < q; qItr++) {
            int n = Integer.parseInt(bufferedReader.readLine().trim());

            int result = Result.downToZero(n);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
