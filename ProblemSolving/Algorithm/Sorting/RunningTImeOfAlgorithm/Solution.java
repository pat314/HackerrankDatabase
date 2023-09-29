package RunningTImeOfAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static int runningTime(List<Integer> arr) {
    // Write your code here
        int n = arr.size();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = arr.get(i); 
        
        int countShift = 0;
        for (int i = 1; i < n; i++) {
            int box = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] > box) {
                countShift++;
                a[j+1] = a[j];
                j--;
            }
        }
        return countShift;
    }

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        arr.add(2);
        arr.add(1);
        arr.add(3);
        arr.add(1);
        arr.add(2);
        System.out.println(Solution.runningTime(arr));
    }
}
