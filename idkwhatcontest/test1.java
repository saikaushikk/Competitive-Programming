import java.util.*;
import java.io.*;
class Solution {
    public boolean check(List<Integer> arr,int k)
    {
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<arr.size()-1;i++)
        {
            set.add(Math.abs(arr.get(i)-arr.get(i+1)));
        }
        //System.out.println(set.size());
        return set.size()==k;
    }
    public int[] constructArray(int n, int k) {
        int[] arr = new int[n];
        for(int i=1;i<=n;i++)
        {
            arr[i-1] = i;
        }
        boolean[] chosen = new boolean[n];
        List<Integer> l = new ArrayList<>();
        helper(arr,k,chosen,new ArrayList<>(),l);
       //System.out.println(l);
        for(int i=0;i<n;i++)
        {
            arr[i] = l.get(i);
        }
        return arr;
    }
    public void helper(int[] arr,int k,boolean[] chosen,List<Integer> cur,List<Integer> l)
    {
        //List<Integer> res = new ArrayList<>();
        if(cur.size()==arr.length && check(cur,k)){
            //System.out.println(cur);
            l.addAll(cur);
            return;
        }
        else{
            
            for(int i=0;i<arr.length;i++)
            {
                if(chosen[i]) continue;
                chosen[i] = true;
                cur.add(arr[i]);
                helper(arr,k,chosen,cur,l);
                chosen[i] = false;
                cur.remove(cur.size()-1);
            }
        }
    }
}

class MainClass {
    public static String integerArrayToString(int[] nums, int length) {
        if (length == 0) {
            return "[]";
        }
    
        String result = "";
        for(int index = 0; index < length; index++) {
            int number = nums[index];
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }
    
    public static String integerArrayToString(int[] nums) {
        return integerArrayToString(nums, nums.length);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);
            line = in.readLine();
            int k = Integer.parseInt(line);
            
            int[] ret = new Solution().constructArray(n, k);
            
            String out = integerArrayToString(ret);
            
            System.out.print(out);
        }
    }
}