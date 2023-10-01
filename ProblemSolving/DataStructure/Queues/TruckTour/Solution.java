import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'truckTour' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY petrolpumps as parameter.
     */

    public static int truckTour(List<List<Integer>> petrolpumps) {
    // Write your code here
    //Hình dung ta sẽ có 1 vị trí pump mà tại đó khi xuất phát ta sẽ đi được 1 vòng quanh tất cả các pump. Như vậy ta chỉ cần xuất phát từ pump đầu tiên, nếu xuất phát từ pump đầu tiên không đi được hết vòng tròn mà bị cạn nhiên liệu tại pump thứ i thì ta nhảy sang kiểm tra pump tại pump thứ i+1 luôn (vì nếu giả sử ta nhảy sang kiểm tra pump thứ 2, 3, ... , i-1, i thì chắc chắn ta cũng không đi được hết vòng tròn vì tổng số dư petrol vẫn là âm) Nếu xuất phát tại phần tử thứ k (k > 1) mà đi được hết dãy (số dư petrol cuối dãy vẫn là dương) thì chứng tỏ ta có thể xuất phát tại điểm thứ k
    //Giải thích: để đi được 1 vòng tròn thì
    // tổng số dư petrol = petrol nạp vào - petrol tiêu hao 
    //= tổng số dư petrol (1, k) + tổng số dư petrol (k+1, n) >= 0 
    //Như vậy giả sử ta xuất phát tank từ pump thứ k+1 và đi đến cuối dãy (n), tổng số 
    //dư petrol của ta > 0 thì các độ chênh lệch petrol tại pump thứ i (= petrol được nạp
    //tại pump thứu i - distance từ pump i đến pump kế tiếp) trong tổng số dư petrol 
    //(1, k) khi cộng dần vào tổng số dư petrol (k+1, n) đã tính cũng sẽ luôn cho ta các
    //tổng lớn hơn 0. Và do đó tank luôn chạy được từ pump i -> i+1 tại các pump trong
    //khoảng (1, k) mà không cần phải kiểm tra lại
        int tank = 0;
        int startIndex = 0;
        for (int i = 0; i < petrolpumps.size(); i++) {
            tank  = tank + petrolpumps.get(i).get(0) -  petrolpumps.get(i).get(1);
            if (tank < 0) {
                tank = 0;
                startIndex = i + 1;
            }
        }
        
        return startIndex;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> petrolpumps = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] petrolpumpsRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> petrolpumpsRowItems = new ArrayList<>();

            for (int j = 0; j < 2; j++) {
                int petrolpumpsItem = Integer.parseInt(petrolpumpsRowTempItems[j]);
                petrolpumpsRowItems.add(petrolpumpsItem);
            }

            petrolpumps.add(petrolpumpsRowItems);
        }

        int result = Result.truckTour(petrolpumps);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
