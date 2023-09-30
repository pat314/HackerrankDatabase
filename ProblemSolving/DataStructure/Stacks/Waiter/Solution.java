import java.util.ArrayList;
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