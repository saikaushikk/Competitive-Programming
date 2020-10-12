import java.util.*;

import javax.lang.model.util.ElementScanner6;

import java.io.*;
public class C{
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
    static List<List<Integer>> adj;
    static boolean[] visited;
    static int n;
    static int[] centroid;
    public static int dfs(int cur)
    {
        int size = 1; 
        visited[cur] = true;
        for(int x:adj.get(cur))
        {
            if(!visited[x])
            {
                int temp = dfs(x);
                // System.out.println(cur + " " + temp);
                centroid[cur] = Math.max(centroid[cur],temp); 
                size+=temp;
            }
        }
        centroid[cur] = Math.max(centroid[cur],n-size);
        return size;
    }
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t = in.nextInt();
        while(t-- >0)
        {
            n = in.nextInt();
            adj = new ArrayList<>();
            for(int i=0;i<=n;i++)
                adj.add(new ArrayList<>());
            for(int i=0;i<n-1;i++)
            {
                int u = in.nextInt(),v = in.nextInt();
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
            centroid = new int[n+1];
            visited = new boolean[n+1];
            for(int i=1;i<=n;i++)
            {
                if(!visited[i])
                    dfs(i);
            }
            int min = Integer.MAX_VALUE;
            for(int i=1;i<=n;i++)
                min = Math.min(min,centroid[i]);
            int count =0;
            for(int i=1;i<=n;i++)
                if(min==centroid[i]) count++;
            if(count==1)
            {
                int c = -1;
                for(int i=1;i<=n;i++){
                    if(centroid[i]==min){
                        c = i;
                        break;
                    }
                }
                out.printLine(c + " " + adj.get(c).get(0));
                out.printLine(c + " " + adj.get(c).get(0));
            }
            else
            {
                int c1 = 0,c2=0;
                for(int i=1;i<=n;i++){
                    if(centroid[i]==min)
                    {
                        if(c1==0)
                        {
                            c1 = i;
                        }
                        else if(c2==0)
                        {
                            c2 = i;
                        }
                        else
                            break;
                    }
                }
                int edge = -1;
                for(int x:adj.get(c1))
                {
                    if(x!=c2)
                    {
                        edge = x;
                        break;
                    }
                }
                out.printLine(c1 + " " + edge);
                out.printLine(c2 + " " + edge);
            }
        }
        out.flush();
        out.close();
    }
}