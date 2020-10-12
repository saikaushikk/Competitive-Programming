import java.util.*;
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

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t = in.nextInt();
        outer:while(t-- >0)
        {
            int n = in.nextInt();
            if(n==2)
            {
                out.printLine(2);
                out.printLine("1 2");
                continue outer;
            }
            PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->(b-a));
            for(int i=1;i<=n;i++)
            {
                pq.add(i);
            }
            StringBuilder sb = new StringBuilder();
            while(pq.size()>1)
            {
                // out.printLine(pq);
                int cur = pq.poll();
                if(cur%2==0)
                {
                    if(pq.peek()%2==0)
                    {
                        int temp = pq.poll();
                        pq.add((cur+temp)/2);
                        sb.append(cur + " "+ temp + "\n");
                    }
                    else
                    {
                        int temp1 = pq.poll();
                        int temp2 = pq.poll();
                        // assert((temp2%2)==0);
                        pq.add((cur+temp1)/2);
                        sb.append(cur + " "+ temp2 + "\n");
                        pq.add(temp1);
                    }
                }
                else
                {
                    if(pq.peek()%2==1)
                    {
                        int temp = pq.poll();
                        pq.add((cur+temp)/2);
                        sb.append(cur + " "+ temp + "\n");
                    }
                    else
                    {
                        int temp1 = pq.poll();
                        int temp2 = pq.poll();
                        // assert((temp2%2)==1);
                        pq.add((cur+temp1)/2);
                        sb.append(cur + " "+ temp2 + "\n");
                        pq.add(temp1);
                    }
                }
            }
            out.printLine(pq.poll());
            out.print(sb);
        }
        out.flush();
        out.close();
    }
}