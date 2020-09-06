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
   // static int mod = (int)(1e9+7);
    public static long pow(long a,long b)
    {
        long ans = 1;
        while(b> 0)
        {
            if((b & 1)==1){
                ans = (ans*a); 
            }
            a = (a*a);
            b = b>>1;
        }
        return ans;
    }
    static class Node{
        int val;
        Node parent;
        Node neighbour;
        public Node(int val,Node parent,Node neighbour)
        {
            this.val = val;
            this.parent = parent;
            this.neighbour = neighbour;
        }
    }
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        //IOUtils io = new IOUtils();
        int n = in.nextInt(),m = in.nextInt();
        int size = (int)pow(2,n);
        Node[] arr = new Node[size];
        for(int i=0;i<size;i++)
        {
            int x = in.nextInt();
            arr[i] = new Node(x,null,null);
        }
        Queue<Node> q = new LinkedList<>();
        q.addAll(Arrays.asList(arr));
        boolean turn = true;
        while(q.size()>1)
        {
            int s = q.size();
            //System.out.println(s);
            for(int i=0;i<s;i+=2)
            {
                Node a = q.poll();
                Node b = q.poll();
                int temp = 0;
              //  System.out.println(a.val + " " + b.val);
                if(turn)
                    temp = a.val|b.val;
                else
                    temp = (a.val)^(b.val);
                Node node = new Node(temp,null,null);
                a.parent = node;
                b.parent = node;
                a.neighbour = b;
                b.neighbour = a;
                q.offer(node);
            }
            turn = turn?false:true;
        }
       // System.out.println(q.poll().val);
        for(int i=0;i<m;i++)
        {
            int p = in.nextInt()-1;
            int x = in.nextInt();
            arr[p].val = x;
            q.clear();
            q.offer(arr[p]);
            q.offer(arr[p].neighbour);
            turn = true;
           // System.out.println(q);
            while(q.size()>1)
            {
                Node a = q.poll();
                Node b = q.poll();
                int temp = 0;
              //   System.out.println(a.val + " " + b.val);
                if(turn)
                    temp = a.val|b.val;
                else
                    temp = (a.val) ^ (b.val);
                if(a.parent==null)
                    break;
                Node par = a.parent;
                par.val = temp;
                q.offer(par);
                if(par.neighbour!=null)
                    q.offer(par.neighbour);
                turn = turn?false:true;
            }
            out.printLine(q.poll().val);
        }
        out.flush();
        out.close();
    }
}