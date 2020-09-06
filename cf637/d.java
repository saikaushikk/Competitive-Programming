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
 
  
    static String[] digits = {"1110111", "0010010", "1011101", "1011011", "0111010", "1101011", "1101111", "1010010", "1111111", "1111011"};
    static int n,k;
    // public static int find(int index,int sum,int num)
    // {
    //     if(index==n)
    //     {
    //         if(sum==0)
    //         {
    //             return dp[index][sum][num] = 1;
    //         }
    //         return dp[index][sum][num] = 0;
    //     }
    //     if(sum<0)
    //         return dp[index][sum][num]=0;
    //     if(dp[index][sum][num]!=null)
    //         return dp[index][sum][num];
    //     int max=0;
    //     for(int i=9;i>=0;i--)
    //     {
    //         int cnt = count[index][i];
    //         if(cnt!=-1 && sum-cnt>=0)
    //         {
    //             max = Math.max(max,find(index+1,sum-cnt,i));
    //         }
    //     }
    //     return dp[index][sum][num]=max;
    // }
        
    // public static void findans(int index,int sum,int num)
    // {
    //     if(index==n)
    //         return;
    //     for(int i=9;i>=0;i--)
    //     {
    //         int cnt = count[index][i];
    //         if(cnt!=-1 && sum-cnt>=0 && dp[index+1][sum-cnt][i]==1)
    //         {
    //             sb.append((char)(i+48));
    //             findans(index+1,sum-cnt,i);
    //             return;
    //         }
    //     }
    // }
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        //IOUtils io = new IOUtils();
        n =in.nextInt();k = in.nextInt();  
        String[] arr = new String[n];
        for(int i=0;i<n;i++)
        {
            arr[i] = in.readString();
        }
        int inf = 1<<29;
        //static Integer[][][] dp;
        //static int[][] count;
        int[][] count = new int[n][10];
        boolean[][] dp = new boolean[n+1][k+1];
        for(int i=0;i<n;i++)
        {
            for(int j=9;j>=0;j--)
            {
                int cnt = 0;
                for(int k=0;k<7;k++)
                {
                    if(arr[i].charAt(k)=='0' && digits[j].charAt(k)=='1')
                    {
                        cnt++;
                    }
                    else if(arr[i].charAt(k)=='1' && digits[j].charAt(k)=='0')
                    {
                        cnt = inf;
                        break;
                    }
                }
                count[i][j] = cnt;
            }
        }
        dp[n][0] = true;
        for(int i =n-1;i>=0;i--)
        {
            for(int sum = 0;sum<=k;sum++)
            {
                for(int d=0;d<10;d++)
                {
                    if(sum-count[i][d]>=0)
                    {
                        dp[i][sum] |= dp[i+1][sum-count[i][d]];
                    }
                }
            }
        }
        if(!dp[0][k])
            out.printLine("-1");
        else{
            for(int i=0;i<n;i++)
            {
                for(int d=9;d>=0;d--)
                {
                    if(k-count[i][d]>=0 && dp[i+1][k-count[i][d]])
                    {
                        k=k-count[i][d];
                        out.print(d);
                        break;
                    }
                }
            }
        }
        out.printLine();
        
        out.flush();
        out.close();
    }
}