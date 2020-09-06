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
    public static void swap(long[] arr,long x,long y,long z)
    {
        long tempx = arr[(int)x],tempy = arr[(int)y],tempz = arr[(int)z];
        arr[(int)y] = tempx;
        arr[(int)z] = tempy;
        arr[(int)x] = tempz;
    }
    public static boolean dif(int a,int b,int c)
    {
        if(a==b || a==c || b==c)
        return false;
        return true;
    }
    static class Pair{
        long f,s;
        public Pair(long f,long s)
        {
            this.f = f;
            this.s = s;
        }
    }
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        //IOUtils io = new IOUtils();
        int tt = in.nextInt();
        while(tt-- >0)
        {
            long n = in.nextLong(),limit = in.nextLong();
            long[] A = new long[(int)n];
            for(int i=0;i<n;i++)
                A[i] = in.nextLong();
            long count = 0;
            long[] arr = new long[(int)n];
            long[] idx = new long[(int)n];
            Deque<Pair> stack = new LinkedList<>();
            List<Pair> vp = new ArrayList<>();
            for(int i=0;i<n;i++)
            {
                vp.add(new Pair(A[i],i));
            }
            Collections.sort(vp,(a,b)->(int)(a.f-b.f));
            for(int i=0;i<n;i++)
            {
                arr[(int)vp.get(i).s] = i;
                idx[i] = vp.get(i).s;
            }
            StringBuilder sb = new StringBuilder();
            for(long i=0;i<n;i++)
            {
                if(arr[(int)i]==i || (!stack.isEmpty() && i==stack.peek().s))
                {
                    continue;
                }
                long j = arr[(int)i];
                long id = idx[(int)i];
                if(arr[(int)j]!=i)
                {
                    long k = arr[(int)j];
                    idx[(int)i] = i;
                    idx[(int)j] = j;
                    swap(arr,i,j,id);
                    assert id!=j;
                    idx[(int)k] = id;
                    sb.append((id+1) + " " + (i+1) + " " + (j+1) + "\n");
                    count++;
                    continue;
                }
                if(!stack.isEmpty() && stack.peek().s==i)
                    continue;
                if(stack.isEmpty())
                {
                    assert i!=arr[(int)i];
                    stack.push(new Pair(i,arr[(int)i]));
                    continue;
                }
                Pair cur = stack.pop();
                count+=2;
                swap(arr,cur.f,cur.s,i);
                swap(arr,cur.f,j,i);
                sb.append((cur.f+1) + " " + (cur.s+1) + " " + (i+1) + "\n");
                sb.append((cur.f+1) + " " + (j+1) + " " + (i+1) + "\n");
                idx[(int)cur.f] = cur.f;
                idx[(int)j] = j;
                idx[(int)i] = i;
                idx[(int)cur.s] = cur.s;
                //assert dif(cur.f,cur.s,i) && dif(cur.f,j,i);
                continue;
            }
            boolean flag = false;
            for(long i=0;i<n;i++)
            {
                if(arr[(int)i]!=i){
                    flag = true;
                    break;
                }
            }
            if(flag || !stack.isEmpty() || limit<count)
            {
                out.printLine("-1");
            }
            else{
                out.printLine(count);
                out.print(sb);
            }
        }
        out.flush();
        out.close();
    }
}