import java.util.*;
import java.io.*;
public class Solution{
    static class InputReader {

        private final InputStream stream;
        private final byte[] buf = new byte[8192];
        private int curChar, snumChars;
        private SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int snext() {
            if (snumChars == -1)
                throw new InputMismatchException();
            if (curChar >= snumChars) {
                curChar = 0;
                try {
                    snumChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (snumChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int nextInt() {
            int c = snext();
            while (isSpaceChar(c)) {
                c = snext();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = snext();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = snext();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public boolean isSpaceChar(int c) {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        private boolean isEndOfLine(int c) {
            return c == '\n' || c == '\r' || c == -1;
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
        }

    }
    public static void update(int i,int add,int[] BIT)
    {
        while(i>0 && i<BIT.length)
        {
            BIT[i]+=add;
            i = i+(i&(-i));
        }
    }
    public static void insert(int x,int[] BIT)
    {
        update(x,1,BIT);
    }
    public static void delete(int x,int[] BIT)
    {
        update(x,-1,BIT);
    }
    public static int sum(int i,int[] BIT)
    {
        int sum = 0;
        while(i>0)
        {
            sum+=BIT[i];
            i = i - (i&(-i));
        }
        return sum;
    }
    public static int kthSmallest(int k,int[] BIT)
    {
        int l = 0; 
        int h = BIT.length; 
        while (l < h) 
        { 
            int mid = (l + h) / 2; 
            if (k <= sum(mid, BIT)) 
                h = mid; 
            else
                l = mid+1; 
        } 
        
        return l; 
    }
    public static void main(String[] args) {
        InputReader sc = new InputReader(System.in);
        int n = sc.nextInt(),q =sc.nextInt();
        int[] BIT = new int[n+1];
        for(int i=0;i<n;i++)
        {
            int x = sc.nextInt();
            insert(x,BIT);
        }
        // out.printLine(kthSmallest(4, BIT));
        for(int i=0;i<q;i++)
        {
            int x = sc.nextInt();
            if(x<0)
            {
                delete(kthSmallest(-x,BIT),BIT);
            }
            else{
                insert(x,BIT);
            }
        }
        if(sum(n,BIT)==0)
            System.out.println(0);
        else
            System.out.println(kthSmallest(1, BIT));
    }
}