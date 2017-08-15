/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DS;

import java.util.LinkedList;
import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

/**
 *
 * @author Aditya Garg
 */
class BipartiteGraph {

    private final int v;
    private final LinkedList<Integer> list[];

    public BipartiteGraph(int v){
       
       this.v = v;
       list = new LinkedList[v];
       
       for(int i = 0; i < v; i++)
           list[i] = new LinkedList<>();      
        
    }
    
    public void addEdge(int v, int w){ list[v].add(w); }

    public int getDegree(int v){ return list[v].size(); }
    
    public void setDegree(int v) {}
    
    public boolean isEdge(int u, int v){
        return list[v].contains((int)u) && list[u].contains((int)v);
    }
  }
  

// CODE for ICPC
class FastIO_example {

    public static void main(String[] args) {
	
        StringBuffer output = new StringBuffer();
        scanner s = new scanner();

        int t = s.nextInt();
        assert t<=100 && t>=1;
                
        while(t-- > 0){
                    
            int n = s.nextInt(),
                m = s.nextInt(),
                d = s.nextInt(),
                D = s.nextInt();
                    
            assert n>=1 && n <= 100;
            if(d > D || m == 0 || d == 0 || D == 0 || m < n)
                output.append(-1).append("\n");
            else if(m >= n){
                int countEdges = n;
                BipartiteGraph bg = new BipartiteGraph(2*n);
                int l[] = new int[n], r[] = new int [n];
                      
                        
                for (int i = 1; i < n; i++) {
                    if(bg.getDegree(l[i-1]) <= D){
                        for (int j = 1; j < n; j++) {
                            if(bg.getDegree(r[j-1]) <= D){
                                bg.addEdge(l[i-1], r[j-1]);
                                countEdges++;
                                if(countEdges > m) 
                                break;
                            }
                        else break;
                        }
                    }
                    else break;
                }
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if(bg.isEdge(l[i], r[j]))
                            output.append(l[i]).append(" ").append(r[j]).append("\n");
                        }
                    }
            }
        }
        System.out.println(output.toString());
                
    }
	
static class scanner {
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = System.in.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public String nextLine() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isEndOfLine(c));
            return res.toString();
        }

        public String nextString() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public long nextLong() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public int nextInt() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }
           
        public int[] nextIntArray(int n) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextInt();
            }
            return arr;
        }
       
        public long[] nextLongArray(int n) {
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextLong();
            }
            return arr;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        private boolean isEndOfLine(int c) {
            return c == '\n' || c == '\r' || c == -1;
        }
    }
}