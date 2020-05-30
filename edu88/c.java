import java.util.*;
import java.io.*;
import java.math.BigDecimal;
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

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        //IOUtils io = new IOUtils();
        int T = in.nextInt();
        while(T-- >0)
        {
            double h = (double)in.nextInt(),c = (double)in.nextInt(),t = (double)in.nextInt();
            double etemp = (h+c)/2;
            double dif = t-etemp;
            BigDecimal diff = new BigDecimal(dif);
            if(h==t)
            {
                out.printLine("1");
                continue;
            }
            if(etemp>=t)
            {
                out.printLine("2");
                continue;
            }
            //long ans = 0;
            long low = 1,high = Long.MAX_VALUE;
            while(low<high)
            {
                // BigDecimal a = new Bi
                long mid = low + (high-low)/2;
                if(mid%2==0)
                    mid++;
                long even = mid/2;
                long odd = mid - even;
                double temp = ((double)(even*c+odd*h)/(double)mid);
                double ndif = Math.abs(t-temp);
                BigDecimal ndiff = new BigDecimal(ndif);
               // out.printLine(mid + " " + diff + " " + ndiff + " " + temp);
                // if(diff==ndiff)
                //     break;
               if(ndiff.compareTo(diff)<=0)
               {
                   //ans = mid;
                   diff = ndiff;
               }
                if(temp>=t)
                {
                    low = mid+1;
                }
                else
                {
                    high = mid-1;
                }
                // if(temp<=t && diff<=ndiff)
                //     break;
                // else{
                //     diff = ndiff;
                //     ans = i;
                // }
            }
            out.printLine(low);
            long ans = low;
            long res = low+1;
            long even = (low+2)/2;
            long odd = (low+2) - even;
            double temp = ((double)(even*c+odd*h)/(double)(low+2.0));
            double ndiff = Math.abs(t-temp);
            BigDecimal ndif = new BigDecimal(ndiff);
            if(ndif.compareTo(diff)<=0)
            {
                //ans = mid;
                diff = ndiff;
            }
            //long res = ans;
            even = (res)/2;
            odd = res - even;
            temp = ((double)(even*c+odd*h)/(double)res);
            ndiff = Math.abs(t-temp);
            out.printLine(res + " " + diff + " " + ndiff + " " + temp);
            //if(diff==ndiff)
            if(ndiff<=diff)
            {
                low = res;
                diff = ndiff;
            }
            if(ans-1>0)
            {
                even = (ans-1)/2;
                odd = (ans-1) - even;
                temp = ((double)(even*c+odd*h)/(double)ans-1);
                ndiff = Math.abs(t-temp);
                out.printLine(res + " " + diff + " " + ndiff + " " + temp);
                //if(diff==ndiff)
                if(ndiff<=diff)
                {
                    low = ans-1;
                    diff = ndiff;
                }
            }
            if(ans-2>0)
            {
                even = (ans-2)/2;
                odd = (ans-2) - even;
                temp = ((double)(even*c+odd*h)/(double)(ans-2));
                ndiff = Math.abs(t-temp);
                out.printLine(res + " " + diff + " " + ndiff + " " + temp);
                //if(diff==ndiff)
                if(ndiff<=diff)
                {
                    low = ans-2;
                    diff = ndiff;
                }
            }
            out.printLine(low);
        }

        out.flush();
        out.close();
    }
}

//     while(low<=high)
        //     {
        //         int mid = low + (high-low)/2;
        //         if(mid==1)
        //             break;
        //         int even = mid/2;
        //         int odd = (mid-(mid/2));
        //         double tempdiff = t - ((double)((odd*h)+(even*c))/(double)mid); 
        //         out.printLine(mid + " " + even + " " + odd);
        //         if(tempdiff<=t)
        //         {
        //             if(diff>=tempdiff)
        //             {
        //                 ans = Math.min(ans,mid);
        //                 diff = tempdiff;
        //             }
        //             high = mid;
        //         }
        //         else
        //         {
        //             low = mid+1;
        //         }
        //     }
        //     out.printLine(ans + " " + diff);