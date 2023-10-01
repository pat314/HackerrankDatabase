import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'solve' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY arr
     *  2. INTEGER_ARRAY queries
     */

    public static List<Integer> solve(List<Integer> arr, List<Integer> queries) {
    // Write your code here
        List<Integer> result = new ArrayList<>();
        
        //duyệt từng queries
        for (int i = 0; i < queries.size(); i++) {
            
            int d = queries.get(i);
            int maxnum = -1;
            //đầu tiên ta duyệt mảng con đầu tiên có độ dài d như yêu cầu của queries 
            //thứ i để khởi tạo giá trị maxnum tương ứng với GTLN trong dãy này
            for (int j = 0; j < d; j++) {
                if (maxnum < arr.get(j)) maxnum = arr.get(j);
            }
            //Lúc này min của các max sẽ bằng max vì trong mảng max chỉ đang có 1 pt 
            int minnum = maxnum;
            
            //Sau đó ta duyệt tiếp các dãy con có độ dài d như sau: tịnh tiến 
            //boundary mảng con lên thêm 1 ptu 
            //(ví dụ ta có mảng 2 3 4 5 6, d = 2, mảng con lúc đầu đang xét là 2 3
            //, ta tịnh tiến boundary mảng con lên thêm 1 ptu thì lúc này mảng con là 3 4)
            for (int j = d; j < arr.size(); j++) {
                //Ta sẽ tìm max như sau:
                //Nếu max của mảng con cũ là gtri phần tử nằm trong mảng cũ 
                //nhưng không nằm trong mảng mới (như ví dụ trên thì là ptu 2),
                //thì ta phải tìm lại maxnum cho mảng con mới đang xét
                if (maxnum == arr.get(j - d)) {
                    maxnum = -1;
                    for (int k = j - d + 1; k < j+1; k++) {
                        if (maxnum < arr.get(k)) maxnum = arr.get(k);
                    }
                } 
                //Nếu max của mảng con cũ là 1 trong những ptu đang nằm trong mảng con 
                //mới thì ta chỉ cần kiểm tra xem ptu mới thêm vào (như ví dụ trên là ptu
                //4) có lớn hơn maxnum không, nếu có thì gán lại maxnum
                else if (maxnum < arr.get(j)) maxnum = arr.get(j);
                //Sau mỗi lần duyệt mảng con thì ta update lại min của các max nếu cần
                if (minnum > maxnum) minnum = maxnum;
            }
            //Sau khi duyệt hết các mảng con, ta đã lấy được min của các max, 
            //ta add vào trong list result
            result.add(minnum);
        }
        //Sau khi duyệt hết quries, ta thu được 1 list các min theo list các queries 
        //yêu cầu, ta trả về list result và kết thúc hàm
        return result;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int q = Integer.parseInt(firstMultipleInput[1]);

        String[] arrTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrTemp[i]);
            arr.add(arrItem);
        }

        List<Integer> queries = new ArrayList<>();

        for (int i = 0; i < q; i++) {
            int queriesItem = Integer.parseInt(bufferedReader.readLine().trim());
            queries.add(queriesItem);
        }

        List<Integer> result = Result.solve(arr, queries);

        for (int i = 0; i < result.size(); i++) {
            bufferedWriter.write(String.valueOf(result.get(i)));

            if (i != result.size() - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
