import java.util.*;
import java.io.*;


class Solution {
    Set<Integer> cols = new HashSet<>();
    Set<Integer> diag1 = new HashSet<>();
    Set<Integer> diag2 = new HashSet<>();
    public int helper(int row,int n,int count)
    {
        for(int i=0;i<n;i++)
        {
            if(cols.contains(i))
                continue;
            int d1 = row-i;
            if(diag1.contains(d1))
                continue;
            int d2 = row+i;
            if(diag2.contains(d2))
                continue;
            if(row==n-1)
                count++;
            else{
                cols.add(i);
                diag1.add(d1);
                diag2.add(d2);
                count = helper(row+1,n,count);
                cols.remove(i);
                diag1.remove(d1);
                diag2.remove(d2);
            }
        }
        return count;
    }
    public int totalNQueens(int n) {
        return helper(0,n,0);
    }
}

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);
            
            int ret = new Solution().totalNQueens(n);
            
            String out = String.valueOf(ret);
            
            System.out.print(out);
        }
    }
}