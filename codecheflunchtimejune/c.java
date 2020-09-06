import java.util.*;
import java.io.*;
class Main{
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

        public long nextLong() {
            int c = snext();
            while (isSpaceChar(c)) {
                c = snext();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = snext();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = snext();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public int[] nextIntArray(int n) {
            int a[] = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = nextInt();
            }
            return a;
        }

        public String readString() {
            int c = snext();
            while (isSpaceChar(c)) {
                c = snext();
            }
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = snext();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public String nextLine() {
            int c = snext();
            while (isSpaceChar(c))
                c = snext();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = snext();
            } while (!isEndOfLine(c));
            return res.toString();
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
    static class OutputWriter {
		private final PrintWriter writer;
 
		public OutputWriter(OutputStream outputStream) {
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
		}
 
		public OutputWriter(Writer writer) {
			this.writer = new PrintWriter(writer);
		}
 
		public void print(Object...objects) {
			for (int i = 0; i < objects.length; i++) {
				if (i != 0)
					writer.print(' ');
				writer.print(objects[i]);
			}
		}
 
		public void printLine(Object...objects) {
			print(objects);
			writer.println();
		}
 
		public void close() {
			writer.close();
		}
 
		public void flush() {
			writer.flush();
		}
 
		}
 
    static class IOUtils {
 
		public static int[] readIntArray(InputReader in, int size) {
            int[] array = new int[size];
            for (int i = 0; i < size; i++)
                array[i] = in.nextInt();
            return array;
        }

    }
    static int gcd(int a, int b) 
    { 
        if (a == 0) 
            return b; 
        return gcd(b % a, a); 
    } 
    static int mod = (int)(1e9+7);
    public static long pow(long a,long b)
    {
        long ans = 1;
        while(b> 0)
        {
            if((b & 1)==1){
                ans = (ans*a) % mod; 
            }
            a = (a*a) % mod;
            b = b>>1;
        }
        return ans;
    }
    // static long res;
    // static Long[][] dp;
    // public static long dfs(long[] arr,int idx,long cur,int count,int k)
    // {
    //     if(idx==31)
    //     {
    //         if(count==k)
    //             return 0;
    //         else
    //             return Long.MIN_VALUE;
    //     }
    //     if(dp[idx][count]!=null)
    //         return dp[idx][count];
    //    // System.out.println(idx + " " + cur + " " + k);
    //     long res = Long.MIN_VALUE;
    //     //not select
    //     res = Math.max(res,dfs(arr,idx+1,cur,count,k));
    //     //select
    //     cur |= (1<<idx);
    //     res = Math.max(res,arr[idx] + dfs(arr,idx+1,cur,count+1,k));
    //     return dp[idx][count] = res;
    // }
    // public static void find(long curSum,long[] arr,int idx,long cur,long target)
    // {
    //     if(curSum>target || idx>31)
    //     {
    //         return;
    //     }
    //     if(curSum==target)
    //     {
    //         res = Math.min(res,cur);
    //         return;
    //     }
    //     //System.out.println(curSum + " " + cur + " " + idx);
    //     if(arr[idx]>0){
    //         find(curSum,arr,idx+1,cur,target);
    //         cur|=(1<<idx);
    //         find(curSum + arr[idx],arr,idx+1,cur,target);
    //     }   
    // }
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        //IOUtils io = new IOUtils();
        int t = in.nextInt();
        while(t-- >0)
        {
            int n = in.nextInt(),k = in.nextInt();
            int[] arr = in.nextIntArray(n);
            long[] bits = new long[32];
            for(int i=0;i<32;i++)
            {
                for(int x:arr)
                {
                    if((x&(1<<i))!=0)
                    {
                        bits[i]++;
                    }
                }
            }
           // bits[31] = 100000L;
            for(int i=0;i<32;i++)
            {
                bits[i] = ((1<<i)*bits[i]);
            }
           // dp = new Long[33][33];
            PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->(bits[a]==bits[b])?a-b:Long.compare(bits[b],bits[a]));
            for(int i=0;i<32;i++)
                pq.add(i);
            int count = 0;
            long res = 0;
            while(count<k)
            {
                int cur = pq.poll();
                res|=(1<<cur);
                count++;
            }
            out.printLine(res);
        }
        out.flush();
        out.close();
    }
}