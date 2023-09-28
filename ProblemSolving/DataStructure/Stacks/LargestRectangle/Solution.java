import java.util.*;


class Solution {
    public static long largestRectangle(List<Integer> h) {
    // Write your code here
        Stack<Integer> stack = new Stack<>();
        
        int top;
        long max_area = 0;
        long subarea;
        
        int i = 0;
        int n = h.size();
        while (i < n)
        {
            if (stack.empty() || h.get(stack.peek()) <= h.get(i)) {
                stack.push(i++);
            } else {
                top = stack.peek();
                stack.pop();
                subarea = h.get(top) * (stack.empty()? i : i - 1 - stack.peek());
                if (max_area < subarea) max_area = subarea;
            }
            
        }
        
        while (stack.empty() == false) {
            top = stack.peek();
            stack.pop();
            subarea = h.get(top) * (stack.empty() ? i : i - stack.peek() - 1);
            if (max_area < subarea) {
                max_area = subarea;
            }
        }
        
        return max_area;

    }
    
    public static void main(String[] args) {
        List<Integer> array = new ArrayList<>();
        array.add(2);
        array.add(3);
        array.add(4);
        array.add(5);
        array.add(4);
        System.out.println(largestRectangle(array));
    }
}

