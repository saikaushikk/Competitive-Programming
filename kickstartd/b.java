import java.util.*;
import java.io.*;
class Solution{
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
    static Integer[][] dp;
    public static int dfs(int[] arr,int n,int idx,int prevVal,int prevnote)
    {
        if(idx==n)
        {
            return 0;
        }
      //  System.out.println(prevnote);
        if(dp[idx][prevnote]!=null)
            return dp[idx][prevnote];
        int res = Integer.MAX_VALUE;
        if(arr[idx]==prevVal)
        {
            res = Math.min(res,dfs(arr,n,idx+1,arr[idx],prevnote));
        }
        else if(arr[idx]>prevVal)
        {
            if(prevnote==3)
            {
                for(int i=0;i<4;i++)
                {
                    res = Math.min(res,1+dfs(arr,n,idx+1,arr[idx],i));
                }
            }
            else
            {
                for(int i=prevnote+1;i<4;i++)
                {
                    res = Math.min(res,dfs(arr,n,idx+1,arr[idx],i));
                }
            }
        }
        else
        {
            if(prevnote==0)
            {
                for(int i=0;i<4;i++)
                {
                    res = Math.min(res,1+dfs(arr,n,idx+1,arr[idx],i));
                }
            }
            else
            {
                for(int i=0;i<prevnote;i++)
                {
                    res = Math.min(res,dfs(arr,n,idx+1,arr[idx],i));
                }
            }
        }
        return dp[idx][prevnote]=res;
    }
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        //IOUtils io = new IOUtils();
        int t = in.nextInt();
        int caseno = 1;
        while(t-- >0)
        {
            int n = in.nextInt();
            int[] arr = in.nextIntArray(n);
            int res = Integer.MAX_VALUE;
            dp = new Integer[n+1][4];
            for(int i=0;i<4;i++)
            {
                res = Math.min(res,dfs(arr, n, 1, arr[0], i));
            }
            out.printLine("Case #"+caseno+": " + res);
            caseno++;
        }
        out.flush();
        out.close();
    }
}