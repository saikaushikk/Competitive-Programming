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

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t = 1;
        int[][] DIR = {{0,1},{0,-1},{1,0},{-1,0}};
        while(t-- >0)
        {
            int n = in.nextInt(),m = in.nextInt(),x=in.nextInt(),y=in.nextInt();
            int[] cur = {x-1,y-1};
            boolean[][] visited = new boolean[n][m];
            int count = 0;
            while(cur[0]>0)
            {
                if(!visited[cur[0]][cur[1]]){
                    count++;
                    visited[cur[0]][cur[1]] = true;
                    out.printLine((cur[0]+1) + " " + (cur[1]+1));
                }
                cur[0]-=1;
            }
            while(cur[1]>0)
            {
                if(!visited[cur[0]][cur[1]]){
                    count++;
                    visited[cur[0]][cur[1]] = true;
                    out.printLine((cur[0]+1) + " " + (cur[1]+1));
                }
                cur[1]-=1;
            }
            for(int i=0;i<n;i++)
            {
                if(i%2==0)
                {
                    for(int j=0;j<m;j++)
                    {
                        if(!visited[i][j]){
                            count++;
                            visited[i][j] = true;
                            out.printLine((i+1) + " " + (j+1));
                        }
                    }
                }
                else
                {
                    for(int j=m-1;j>=0;j--)
                    {
                        if(!visited[i][j]){
                            count++;
                            visited[i][j] = true;
                            out.printLine((i+1) + " " + (j+1));
                        }
                    }
                }
            }
        }
        out.flush();
        out.close();
    }
}






// int count = 0;
// while(count<n*m)
// {
//     //right
//     while(cur[1]<m)
//     {
//         if(!visited[cur[0]][cur[1]]){
//             count++;
//             visited[cur[0]][cur[1]] = true;
//             out.printLine((cur[0]+1) + " " + (cur[1]+1));
//         }
//         if(cur[1]==m-1)
//             break;
//         else
//             cur[1]+=1;         
//     }
//     //down
//     while(cur[0]<n)
//     {
//         if(!visited[cur[0]][cur[1]]){
//             count++;
//             visited[cur[0]][cur[1]] = true;
//             out.printLine((cur[0]+1) + " " + (cur[1]+1));
//         }
//         if(cur[0]==n-1)
//             break;
//         else
//             cur[0]+=1;
//     }
//     //left
//     while(cur[1]>=0)
//     {
//         if(!visited[cur[0]][cur[1]]){
//             count++;
//             visited[cur[0]][cur[1]] = true;
//             out.printLine((cur[0]+1) + " " + (cur[1]+1));
//         }
//         if(cur[1]==0)
//             break;
//         else
//             cur[1]-=1;
//     }
//     //up
//     while(cur[0]>=0)
//     {
//         if(!visited[cur[0]][cur[1]]){
//             count++;
//             visited[cur[0]][cur[1]] = true;
//             out.printLine((cur[0]+1) + " " + (cur[1]+1));
//         }
//         if(cur[0]==0)
//             break;
//         else
//             cur[0]-=1;
//     }              
    
// }