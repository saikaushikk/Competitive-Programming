import java.util.*;
import java.io.*;
class Main{
    static int partition(int[] arr,int low,int high)
    {
        Random r = new Random();
        int pivotIndx = r.nextInt((high - low) + 1) + low;
        int i = low;
        int temp = arr[high];
        arr[high] = arr[pivotIndx];
        arr[pivotIndx] = temp;
        int pivot = arr[high];
        for(int j=low;j<high;j++)
        {
            if(arr[j]<pivot)
            {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }
        temp = arr[i];
        arr[i] = arr[high];
        arr[high] = temp;
        return i;
    }
    static void sort(int[] arr,int low,int high)
    {
        if(low<high)
        {
            int p =partition(arr,low,high);
            sort(arr,low,p-1);
            sort(arr,p+1,high);
        }
    }
    public static void main(String[] args) {
        int[] arr = new int[]{1,3,2,1,31,23,13,1,23,4,-123133};
        sort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}