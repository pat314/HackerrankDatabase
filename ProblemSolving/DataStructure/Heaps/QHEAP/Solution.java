import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Heap {
    int[] arr = new int[100000 + 1];
    int N;
    public Heap() {
        int N = 0;
    }
    
    public boolean isEmpty() {
         return N ==0;
    }
    
    //insert in a heap
    public void insert(int key) {
        arr[++N] = key;
        swim(N);
    }
    
    private void swim (int k) {
        while (k > 1 && arr[k/2] > arr[k]) {
            exchange(k, k/2);
            k = k/2;
        }
    }
    
    private void exchange(int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    
    //delete from heap
    public void delete(int key) {
        int i;
        for (i = 1; i < N; i++) {
            if (key == arr[i]) break;
        }
        
        exchange(i, N--);
        sink(i);
    }
    
    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && arr[j] > arr[j+1]) j++;
            if (arr[k] < arr[j]) break;
            exchange(k, j);
            k = j;
        }
    }
    
    //peek a heap
    public int peek() {
        return arr[1];
    }
    
}

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        int Q = scanner.nextInt();
        Heap pq = new Heap();
        
        for (int i = 0; i < Q; i++) {
            int q = scanner.nextInt();
            switch (q) {
                case 1:
                {
                    int num = scanner.nextInt();
                    pq.insert(num);
                    break;
                }
                
                case 2:
                {
                    int num = scanner.nextInt();
                    pq.delete(num);
                    break;
                }
                
                case 3:
                {
                    System.out.println(pq.peek());
                }
            }
            
        }
    }
}