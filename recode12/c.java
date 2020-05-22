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
    public static int[] LIS(Set<Integer> list) {
        // time O(n log(n))
        // space O(n)
        Integer[] nums = list.toArray(new Integer[0]);
        int[] dp = new int[nums.length];
        int[] dpIdx = new int[nums.length];
        int[] prev = new int[nums.length];
        int res = 0;
        
        for (int i = 0; i < nums.length; ++i) {
            int num = nums[i];
            int pos = Arrays.binarySearch(dp, 0, res, num); // (array, start, end, key) (end exclusive)
            if (pos < 0) {
                // not found, -> insertion point
                pos = - (pos + 1);
            }
            
            dp[pos] = num;
            dpIdx[pos] = i;
            if (pos > 0) {
                prev[i] = dpIdx[pos - 1];
            } else {
                prev[i] = -1;
            }
            
            if (pos == res) {
                ++res;
            }
        }

        // reconstruct the the LIS
        int[] lis = new int[res];
        for (int i = dpIdx[res - 1], j = lis.length - 1; i >= 0 && j >= 0; i = prev[i], j -= 1) {
            lis[j] = nums[i];
        }
        
        // System.out.printf("nums:  %s\n", Arrays.toString(nums));
        // System.out.printf("dp:    %s\n", Arrays.toString(dp));
        // System.out.printf("dpIdx: %s\n", Arrays.toString(dpIdx));
        // System.out.printf("prev:  %s\n", Arrays.toString(prev));
        // System.out.printf("LIS:   %s\n", Arrays.toString(lis));
        
        return lis;
    }
    // public static int LIS(int[] nums) {
    //     // time O(n log(n))
    //     // space O(n)
    //     int[] dp = new int[nums.length];
    //     // dp[i] is the smallest possible ending value in all increasing subsequence with length i + 1
    //     int res = 0;
        
    //     for (int i = 0; i < nums.length; ++i) {
    //         int num = nums[i];
    //         int pos = Arrays.binarySearch(dp, 0, res, num); // (array, start, end, key) (end exclusive)
    //         if (pos < 0) {
    //             // not found, -> insertion point
    //             pos = - (pos + 1);
    //         }
    //         dp[pos] = num;
            
    //         if (pos == res) {
    //             ++res;
    //         }
    //     }
    //     return res;
    // }
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        //IOUtils io = new IOUtils();
        int t = in.nextInt();
        while(t-- >0)
        {
            int n = in.nextInt();
            int[] arr = in.nextIntArray(n);
            TreeMap<Integer,List<Integer>> map = new TreeMap<>();
            for(int i=0;i<n;i++)
            {
                if(!map.containsKey(arr[i]))
                    map.put(arr[i],new ArrayList<>());
                map.get(arr[i]).add(i);
            }
            int ans = 1,pre = -1;
            for(int x:map.keySet())
            {
                List<Integer> ind = map.get(x);
                int res = Collections.binarySearch(ind,pre);
                res = -res-1;
                if(pre>=ind.get(ind.size()-1))
                {
                    pre = ind.get(0);
                    ans++;
                }
                else{
                    pre = ind.get(res);
                }
            }
            out.printLine(ans);
        }
        out.flush();
        out.close();
    }
}