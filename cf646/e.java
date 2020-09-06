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
    static int[] a,b,c,min;
    static List<List<Integer>> adj;
    static long res;
    public static int[] dfs(int node,int par)
    {
        int[] bad = new int[2];
        if(b[node]!=c[node])
        {
            bad[b[node]]++;
        }
        for(int child:adj.get(node))
        {
            if(child==par)
                continue;
            min[child] = Math.min(min[node],a[child]);
            int[] rem = dfs(child,node);
            bad[rem[0]]+=rem[1];
        }
        int x = Math.min(bad[0],bad[1]);
        res+=(long)(2L*x*min[node]);
        bad[0]-=x;
        bad[1]-=x;  
        if(bad[0]==0 && bad[1]==0)
        {
            return new int[]{0,0};
        }
        if(bad[0]>0)
        {
            if(par==0)
            {
                res = -1;
            }
            return new int[]{0,bad[0]};
        }
        else{
            if(par==0){
                res = -1;
            }
            return new int[]{1,bad[1]};
        }
    }
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        //IOUtils io = new IOUtils();
        int n = in.nextInt();
        a = new int[n+1];
        b = new int[n+1];
        c = new int[n+1];
        min = new int[n+1];
        adj = new ArrayList<>();
        for(int i=1;i<=n;i++)
        {
            int[] temp = in.nextIntArray(3);
            a[i] = temp[0];
            b[i] = temp[1];
            c[i] = temp[2];
        }
        for(int i=0;i<=n;i++)
            adj.add(new ArrayList<>());
        for(int i=0;i<n-1;i++)
        {
            int u = in.nextInt(),v = in.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
       // System.out.println(Arrays.toString(a) + " " + Arrays.toString(b) + " " + Arrays.toString(c));
        min[1] = a[1];
        res = 0;
        dfs(1,0);
        out.printLine(res);
        out.flush();
        out.close();
    }
}