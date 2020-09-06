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
    public static boolean isValid1(int x,int y,int z,int u,int v,int w)
    {
        if(x==y && y==z)
        {
            return true;
        }
        else if(x==y && z==0)
        {
            return true;
        }
        else if(y==z && x==0)
        {
            return true;
        }
        else if(x==z && y==0)
        {
            return true;
        }
        else if(x==0 && y==0)
        {
            return true;
        }
        else if(y==0 && z==0)
            return true;
        else if(x==0 && z==0)
            return true;
        else if(u==v && v==w)
        {
            return true;
        }
        else if(u==v && z==0)
            return true;
        else if(v==w && x==0)
            return true;
        else if(u==w && y==0)
            return true;
        else 
            return false;
    }
    public static boolean isValid2(int x,int y,int z,int u,int v,int w)
    {
        if(x==y)
        {
            return true;
        }
        else if(y==z)
        {
            return true;
        }
        else if(x==z)
        {
            return true;
        }
        else if(x==0 || y==0 || z==0)
        {
            return true;
        }
        else if(u==v)
        {
            return true;
        }
        else if(v==w)
        {
            return true;
        }
        else if(u==v)
        {
            return true;
        }
        else 
            return false;
    }
    public static int mx = 1000000000;
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        //IOUtils io = new IOUtils();
        int tt= in.nextInt();
        while(tt-- >0)
        {
            int[] n = in.nextIntArray(3);
            int[] t = in.nextIntArray(3);
            int x = t[0]-n[0],y=t[1]-n[1],z = t[2]-n[2];
            int u=-mx+1,v=mx+2,w=mx+3;
            if(n[0]!=0)
            {
                if(Math.abs(t[0])>=Math.abs(n[0]) && (Math.abs(t[0])%Math.abs(n[0]))==0)
                {
                    u = t[0]/n[0];
                }
            }
            if(n[1]!=0)
            {
                if(Math.abs(t[1])>=Math.abs(n[1]) && (Math.abs(t[1])%Math.abs(n[1]))==0)
                {
                    v = t[1]/n[1];
                }
            }
            if(n[2]!=0)
            {
                if(Math.abs(t[2])>=Math.abs(n[2]) && (Math.abs(t[2])%Math.abs(n[2]))==0)
                {
                    w = t[2]/n[2];
                }
            }
         //   out.printLine(u + " " + v + " " + w);
            if(x==0 && y==0 && z==0)
            {
                out.printLine("0");
                continue;
            }
            if(isValid1(x, y, z, u, v, w))
            {
                out.printLine("1");
            }
            else if(isValid2(x,y,z,u,v,w))
            {
                out.printLine("2");
            }
            else {
                out.printLine("3");
            }

        }
        out.flush();
        out.close();
    }
}