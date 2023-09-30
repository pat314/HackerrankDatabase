import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

class Solution {
         public static boolean checkPrime(int n) {
         if (n <= 1) return false;
         for (int i = 2; i <= Math.sqrt((double) n); i++) {
             if (n % i == 0) return false;
         }
         return true;
     }
     
     public static List<Integer> primeArray(int n) {
         List<Integer> result = new ArrayList<>();
         int count = 0;
         int i = 2;
         while (count < n) {
             if (checkPrime(i)) {
                 result.add(i);
                 count++;
             }
             i++;
         }
         return result;
     }

     public static List<Integer> waiter(List<Integer> number, int q) {
        // Write your code here
        List<Integer> primeArr = primeArray(q);
        List<Integer> ans = new ArrayList<>();

        //Tóm tắt bài toán: ban đầu người ta cho mảng A, ta sẽ đổ vào 2
        //stack A1 và B1, sau đó lại lấy stack A1 thực hiện đổ vào stack A2, B2
        // cứ như vậy cho đến khi stack Ai không còn phần tử nào nữa thì dừng.

        //Như vậy, ý tưởng đầu tiên, ban đầu ta sẽ đổ mảng number đã cho vào 2
        // stack A và B (lần 1)
        // Các lần sau ta sẽ duyệt stack A, những phần tử nào thỏa mãn chia hết cho số
        // nguyên tố thứ i thì ta sẽ bỏ vào stack B, sau đó đảo ngược lại stack A bằng
        //cách đổ vào 1 stack khác rồi gán lại cho A. (hành động tương tự như các pt 
        //còn lại ở lần thứ A_i sẽ đổ vào stack thứ A_i+1)
        Stack<Integer> A = new Stack<>();
        Stack<Integer> B = new Stack<>();
        for (int i = number.size() - 1; i >=0; i--) {
            if (number.get(i) % primeArr.get(0) == 0) {
                B.push(number.get(i));
            }
            else A.push(number.get(i));
        }
        
        while (B.empty() == false) ans.add(B.pop());
        
        int i = 1;
        while (i < q && A.empty() == false) {
            for (int j = A.size() - 1; j >= 0; j--) {
                if (A.get(j) % primeArr.get(i) == 0) {
                    B.push(A.get(j));
                    A.remove(j);
                }
            }
            Stack<Integer> tmp = new Stack<>();
            while (A.empty() == false) tmp.push(A.pop());
            A = tmp;
            
            while (B.empty() == false) ans.add(B.pop());
            i++;
        }
        
        
        
        while (A.empty() == false) ans.add(A.pop());
            
        return ans;
    }


    //cải tiến ý tưởng trên, ta sẽ không cần dùng stack nữa. Ta sẽ tạo ra 
    //một list mới để chứa kết quả, duyệt dãy đã cho q lần, mỗi lần duyệt, 
    //ta sẽ lấy những số chia hết cho số nguyên tố thứ i bỏ vào trong list kết quả 
    //(clone pt đó sang list kết quả rồi xóa phần tử đó khỏi list cũ) 
    //(thay cho việc duyệt dãy từ cuối lên đầu, push các phần tử thỏa mãn 
    //vào stack Bi rồi đổ ngược lại vào list kết quả). 
    //Sau đó đảo ngược list cũ (thay cho việc push các phần tử còn lại vào stack Ai)
    public static List<Integer> waiter2(List<Integer> number, int q) {
        // Write your code here
        List<Integer> prime = primeArray(q);
        List<Integer> num = new ArrayList<>();
        for(int p:prime){
            for(int i =0;i<number.size();i++){
                if(number.get(i)%p==0){
                    num.add(number.get(i));
                    number.remove(i);
                    i--;
                }
            }
            Collections.reverse(number);
        }
        Collections.reverse(number);
        num.addAll(number);
        return num;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int q = scanner.nextInt();

        List<Integer> arr = new ArrayList<>();

        //Sample input test case:
        //47 21
        // 80 37 86 79 8 39 43 41 15 33 30 15 45 55 61 74 49 49 20 66 77 19 85 44 81 82 27 5 36 83 91 45 39 44 19 44 71 49 8 66 81 40 29 60 35 31 44
        for (int i = 0; i < n; i++) {
            int tmp = scanner.nextInt();
            arr.add(tmp);
        }

        List<Integer> result = waiter(arr, q);

        for (int x : result) System.out.println(x);

        scanner.close();
    }
}