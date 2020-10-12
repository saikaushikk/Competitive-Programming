import java.util.*;
import java.io.*;
public class D{
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
    static void add(TreeMap<Long,Integer> map,long val)
    {
        map.put(val,map.getOrDefault(val, 0)+1);
    }
    static void remove(TreeMap<Long,Integer> map,long val)
    {
        if(map.get(val)==1)
            map.remove(val);
        else
            map.put(val,map.get(val)-1);
    }
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int n = in.nextInt(),q = in.nextInt();
        int[] arr = in.nextIntArray(n);
        long sum = 0;
        // TreeSet<Long> pq = new TreeSet<>((a,b)->(Long.compare(b, a)));
        TreeSet<Long> set = new TreeSet<>();
        TreeMap<Long,Integer> map = new TreeMap<>();
        for(int x:arr){
            set.add((long)x);
        }
        long prev = set.first();
        for(Long x:set)
        {
            Long diff = (x-prev);
            sum+=diff;
            add(map,diff);
            prev = x;
        }
        if(set.size()<=1)
            out.printLine(0);
        else
            out.printLine(sum-map.lastKey());
        while(q-- >0)
        {
            int type = in.nextInt();
            long val = in.nextLong();
            if(type==0)
            {
                set.remove(val);
                Long higher = set.higher(val);
                Long lower = set.lower(val); 
                if(higher==null && lower==null)
                {
                    // out.printLine(0);
                    sum=0;
                    map.clear();
                    // continue outer;
                }
                else if(lower==null)
                {
                    long diff = higher-val;
                    sum-=diff;
                    remove(map,diff);
                }
                else if(higher==null)
                {
                    long diff = val-lower;
                    sum-=diff;
                    remove(map,diff);
                }
                else
                {
                    long diff1 = val-lower;
                    long diff2 = higher-val;
                    remove(map,diff1);
                    remove(map,diff2);
                    add(map,higher-lower);
                    // max = Math.max(max,higher-lower);
                }
                // out.printLine(set + " " + sum + "  " + max);
                if(set.size()<=1)
                    out.printLine(0);
                else
                    out.printLine(sum-map.lastKey());
            }
            else
            {
                set.add(val);
                Long higher = set.higher(val);
                Long lower = set.lower(val);
                if(higher==null && lower==null)
                {
                    // out.printLine(0);
                    sum=0;
                    // continue outer;
                }
                else if(lower==null)
                {
                    long diff = higher-val;
                    sum+=diff;
                    add(map,diff);
                }
                else if(higher==null)
                {
                    long diff = val-lower;
                    sum+=diff;
                    add(map,diff);
                }
                else
                {
                    long diff1 = val-lower;
                    long diff2 = higher-val;
                    remove(map,(higher-lower));
                    add(map,diff1);
                    add(map,diff2);
                }
                // out.printLine(set + " " + sum + "  " + max);
                if(set.size()<=1)
                    out.printLine(0);
                else
                    out.printLine(sum-map.lastKey());
            }
            out.flush();
        }
        out.flush();
        out.close();
    }
}