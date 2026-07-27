
import java.io.*;
import java.util.*;

public class Main {
    static final long MOD = 1_000_000_007L;
    static final int INF = Integer.MAX_VALUE;
    static FastScanner fs = new FastScanner(System.in);
    static PrintWriter out = new PrintWriter(System.out);

    static void solve() throws Exception {
        // Write solution here
    }

    public static void main(String[] args) throws Exception {
        int t = 1;
        // t = fs.nextInt();
        while (t-- > 0) solve();
        out.flush();
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr=0,len=0;
        FastScanner(InputStream is){in=is;}
        private int read() throws IOException{
            if(ptr>=len){len=in.read(buffer);ptr=0;if(len<=0)return -1;}
            return buffer[ptr++];
        }
        String next() throws IOException{
            StringBuilder sb=new StringBuilder();
            int c;
            while((c=read())!=-1 && c<=32);
            while(c!=-1 && c>32){sb.append((char)c);c=read();}
            return sb.toString();
        }
        int nextInt() throws IOException{return Integer.parseInt(next());}
        long nextLong() throws IOException{return Long.parseLong(next());}
    }

    // Bit Manipulation
    static boolean getBit(int n,int k){return ((n>>k)&1)==1;}
    static int setBit(int n,int k){return n|(1<<k);}
    static int clearBit(int n,int k){return n&~(1<<k);}
    static int toggleBit(int n,int k){return n^(1<<k);}
    static int updateBit(int n,int k,int b){return b==0?clearBit(n,k):setBit(n,k);}
    static boolean isPowerOfTwo(int n){return n>0&&(n&(n-1))==0;}
    static int countSetBits(int n){return Integer.bitCount(n);}
    static int rightMostSetBit(int n){return n&-n;}

    // Math
    static long gcd(long a,long b){while(b!=0){long t=a%b;a=b;b=t;}return a;}
    static long lcm(long a,long b){return a/gcd(a,b)*b;}
    static long modPow(long a,long e,long m){
        long r=1;a%=m;
        while(e>0){if((e&1)==1)r=r*a%m;a=a*a%m;e>>=1;}
        return r;
    }

    // Binary Search
    static int lowerBound(int[] a,int x){
        int l=0,r=a.length;
        while(l<r){
            int mid=(l+r)/2;
            if(a[mid]<x)l=mid+1;else r=mid;
        }
        return l;
    }
    static int upperBound(int[] a,int x){
        int l=0,r=a.length;
        while(l<r){
            int mid=(l+r)/2;
            if(a[mid]<=x)l=mid+1;else r=mid;
        }
        return l;
    }

    // Utilities
    static void swap(int[]a,int i,int j){int t=a[i];a[i]=a[j];a[j]=t;}
    static void reverse(int[]a){
        for(int i=0,j=a.length-1;i<j;i++,j--)swap(a,i,j);
    }

    static class Pair{
        int first,second;
        Pair(int f,int s){first=f;second=s;}
    }

    // DSU
    static class DSU{
        int[] p,s;
        DSU(int n){
            p=new int[n];s=new int[n];
            for(int i=0;i<n;i++){p[i]=i;s[i]=1;}
        }
        int find(int x){return p[x]==x?x:(p[x]=find(p[x]));}
        void union(int a,int b){
            a=find(a);b=find(b);
            if(a==b)return;
            if(s[a]<s[b]){int t=a;a=b;b=t;}
            p[b]=a;s[a]+=s[b];
        }
    }

    // Fenwick Tree
    static class Fenwick{
        int[] bit;
        Fenwick(int n){bit=new int[n+1];}
        void add(int i,int v){for(i++;i<bit.length;i+=i&-i)bit[i]+=v;}
        int sum(int i){int r=0;for(i++;i>0;i-=i&-i)r+=bit[i];return r;}
    }
}
