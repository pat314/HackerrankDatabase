import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'minimumMoves' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING_ARRAY grid
     *  2. INTEGER startX
     *  3. INTEGER startY
     *  4. INTEGER goalX
     *  5. INTEGER goalY
     */

    public static int minimumMoves(List<String> grid, int startX, int startY, int goalX, int goalY) {
        /*
         * Ý tưởng bài này cũng giống bài Down To Zero II: ta duyệt tất cả các cách đi 
         * có thể, tại 1 lần duyệt nào đó, với 1 cách đi nào đó, ta tới được đích thì
         * cách đi đó sẽ là cách đi tốn ít bước nhất
         * 
         * Lưu ý: trục Ox, Oy của grid:
         * O-------------------> y
         *  |
         *  |
         *  |
         *  |
         *  |
         *  |
         *  V
         *  x
         */

        // Write your code here
        //Biến set visit để lưu tại vị trí (x, y) này đã từng có cách nào đi qua trước 
        //đó chưa, nếu có thì vị trí (x, y) đã tồn tại trong set, ngược lại là không.
        Set<List<Integer>> visit = new HashSet<>();
        
        //Hàng đợi queue dùng để lưu vị trí (x, y) cùng với số bước để đến vị trí này.
        //Quan hệ giữa set và queue được sử dụng trong bài này là khi vị trí (x,y) chưa
        //tồn tại trong set thì ta mới thêm vị trí (x, y) và số bước để tới vị trí này.
        //Như vậy queue sẽ lưu lại số bước mà ta đến vị trí (x, y) lần đầu tiên. 
        //Đó cũng là số bước nhỏ nhất để đến vị trí này. (Với cùng 1 số bước, đi theo 
        //cách nào tới được vị trí này trước thì cách đó sẽ có số bước nhỏ nhất để tới vị
        //trí này). Ta có ví dụ:
        //Giả sử ta đang đứng trong 1 grid 2x2, sau lần decision thứ k, ta đang đứng
        //tại vị trí (0, 0), vị trí (1, 1) là vị trí mà ta chưa đi qua bao giờ, 
        //ta sẽ xét 2 cách đi đến vị trí này như sau:
        // 1. (0, 0) -> (0, 1) -> (1, 1) => 2 bước 
        //(đây là cách ngắn nhất để đến (1, 1) từ (0, 0), tự kiểm chứng)
        // => put vào trong set (1, 1) và put vào trong queue (1, 1) và số bước là k+2
        // 2. (0, 0) -> (0, 1) -> (0, 2) -> (1, 2) -> (1, 1) => 4 bước 
        // => cặp (1, 1) đã tồn tại trong set với số bước là k+2 < k+4
        // Như vậy ta thấy queue sẽ lưu số bước ít nhất để đến được vị trí (x, y)
        Queue<List<Integer>> queue = new LinkedList<>();
        
        //biến count để lưu số bước nhỏ nhất để đến 1 vị trí (x, y)
        int count = 0;
        
        //mỗi bước ta sẽ có nhiều lần di chuyển, mỗi lần di chuyển ta sẽ đi được 
        //1 ô lên trên, xuống dưới, sang trái, sang phải.
        int [][] moves = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        
        //bắt đầu: kiểm tra nếu ô đang đứng là đích luôn thì return count
        if (startX == goalX && startY == goalY) return count;
        //Nếu không thì ta bắt đầu đi 1 bước từ vị trí xuất phát
        count += 1;
        //Ta sẽ duyệt tất cả các cách di chuyển từ vị trí đang đứng
        for (int i = 0; i < moves.length; i++) {
            int x = startX;
            int y = startY;
            //sau khi lựa chọn hướng di chuyển, ta sẽ di chuyển 1 ô, 2 ô,... đến khi gặp
            //border, hoặc gặp blocks, hoặc tới đích thì dừng, khi dừng lại thì mới hoàn
            //thành 1 bước
            while (true) {
                int xi = moves[i][0];
                int yi = moves[i][1];
                //tịnh tiến 1 bước theo hướng đang duyệt
                x += xi;
                y += yi;
                //nếu chưa phải dừng lại thì tiếp tục đi tiếp
                if ((0 <= x && x < grid.size()) && (0 <= y && y < grid.get(x).length()) && grid.get(x).charAt(y) == '.') {
                    List<Integer> point = new ArrayList<>();
                    point.add(x);
                    point.add(y);
                    
                    //Nếu đang đi mà gặp goal thì trả về số bước rồi kết thúc hàm
                    if (x == goalX && y == goalY) return count;
                    
                    // Nếu không thì kiểm tra xem trước đó mình đã từng đi tới đây chưa,
                    //nếu chưa đi thì đánh dấu và ghi lại số bước để đêbs vị trí này lần
                    //đầu tiên (số bước nhỏ nhất)
                    else if (!visit.contains(point)) {
                        visit.add(point);
                        List<Integer> history = new ArrayList<>();
                        history.add(x);
                        history.add(y);
                        history.add(count);
                        queue.add(history);
                    }
                } 
                //Đi kịch hướng đó rồi chưa có gì thì duyệt hướng tiếp theo
                else break;
            }
        }
        
        //Tiếp tục duyệt tại lần đi thứ k+1 với các vị trí đang đứng sau lần thứ k
        while (!queue.isEmpty()) {
            List<Integer> history2 = queue.remove();
            int pathx = history2.get(0);
            int pathy = history2.get(1);
            count     = history2.get(2) + 1;
            
            for (int i = 0; i < moves.length; i++) {
                int x = pathx;
                int y = pathy;
                
                while (true) {
                    int xi = moves[i][0];
                    int yi = moves[i][1];
                    x += xi;
                    y += yi;
                    if ((0 <= x && x < grid.size()) 
                    && (0 <= y && y < grid.get(x).length()) 
                    && grid.get(x).charAt(y) == '.') {
                        List<Integer> point = new ArrayList<>();
                        point.add(x);
                        point.add(y);
                        if (x == goalX && y == goalY) return count;
                        else if (!visit.contains(point)) {
                            visit.add(point);
                            List<Integer> history = new ArrayList<>();
                            history.add(x);
                            history.add(y);
                            history.add(count);
                            queue.add(history);
                        }
                    } else break;
                }
            }  
        }
        //Nếu không có cách nào để đến vị trí goal thì trả về -1
        return -1;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> grid = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String gridItem = bufferedReader.readLine();
            grid.add(gridItem);
        }

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int startX = Integer.parseInt(firstMultipleInput[0]);

        int startY = Integer.parseInt(firstMultipleInput[1]);

        int goalX = Integer.parseInt(firstMultipleInput[2]);

        int goalY = Integer.parseInt(firstMultipleInput[3]);

        int result = Result.minimumMoves(grid, startX, startY, goalX, goalY);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
