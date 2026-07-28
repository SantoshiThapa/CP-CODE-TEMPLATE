import java.io.*;
import java.util.*;

class Main {

    // ─────────────────────────────────────────────────────────────────
    //  TOGGLE: set false before submitting
    // ─────────────────────────────────────────────────────────────────
    static final boolean DEBUG = true;

    // ═════════════════════════════════════════════════════════════════
    //  IO  —  Fast Buffered Input
    // ═════════════════════════════════════════════════════════════════
    static class IO {
        private static final BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
        private static StringTokenizer st;

        /** Next space-separated token */
        public static String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) { e.printStackTrace(); }
            }
            String res = st.nextToken();
            DBG.log("IO.next", res);
            return res;
        }

        /** Read int */
        public static int i() {
            int v = Integer.parseInt(next());
            DBG.log("IO.i", v);
            return v;
        }

        /** Read long */
        public static long l() {
            long v = Long.parseLong(next());
            DBG.log("IO.l", v);
            return v;
        }

        /** Read double */
        public static double d() {
            double v = Double.parseDouble(next());
            DBG.log("IO.d", v);
            return v;
        }

        /** Read full line */
        public static String line() {
            try {
                String res = br.readLine();
                DBG.log("IO.line", res);
                return res;
            } catch (IOException e) { e.printStackTrace(); return null; }
        }

        /** Read int array of size n */
        public static int[] iArr(int n) {
            int[] a = new int[n];
            for (int k = 0; k < n; k++) a[k] = i();
            DBG.log("IO.iArr", a);
            return a;
        }

        /** Read long array of size n */
        public static long[] lArr(int n) {
            long[] a = new long[n];
            for (int k = 0; k < n; k++) a[k] = l();
            DBG.log("IO.lArr", a);
            return a;
        }

        /** Read String array of size n */
        public static String[] sArr(int n) {
            String[] a = new String[n];
            for (int k = 0; k < n; k++) a[k] = next();
            DBG.log("IO.sArr", a);
            return a;
        }

        /** Read n×m int grid */
        public static int[][] iGrid(int n, int m) {
            int[][] g = new int[n][m];
            for (int r = 0; r < n; r++)
                for (int c = 0; c < m; c++) g[r][c] = i();
            DBG.msg("IO.iGrid [" + n + "x" + m + "] read");
            return g;
        }

        /** Read n×m char grid (each row is a string) */
        public static char[][] cGrid(int n) {
            char[][] g = new char[n][];
            for (int r = 0; r < n; r++) g[r] = next().toCharArray();
            DBG.msg("IO.cGrid [" + n + " rows] read");
            return g;
        }
    }

    // ═════════════════════════════════════════════════════════════════
    //  DBG  —  Debug Utilities  (all output goes to stderr)
    // ═════════════════════════════════════════════════════════════════
    static class DBG {

        /** Print label = value. Handles int[], long[], Object[] smartly. */
        public static void log(String label, Object val) {
            if (!DEBUG) return;
            String s;
            if      (val instanceof int[])    s = Arrays.toString((int[]) val);
            else if (val instanceof long[])   s = Arrays.toString((long[]) val);
            else if (val instanceof boolean[])s = Arrays.toString((boolean[]) val);
            else if (val instanceof Object[]) s = Arrays.deepToString((Object[]) val);
            else                              s = String.valueOf(val);
            System.err.println("[DBG] " + label + " = " + s);
        }

        /** Print 2D char grid */
        public static void grid(String label, char[][] g) {
            if (!DEBUG) return;
            System.err.println("[DBG GRID] " + label + ":");
            for (char[] r : g) System.err.println("  " + new String(r));
        }

        /** Print 2D int grid */
        public static void grid(String label, int[][] g) {
            if (!DEBUG) return;
            System.err.println("[DBG GRID] " + label + ":");
            for (int[] r : g) System.err.println("  " + Arrays.toString(r));
        }

        /** Milestone message */
        public static void msg(String info) {
            if (DEBUG) System.err.println("[DBG] " + info);
        }

        /** Print any iterable */
        public static void iter(String label, Iterable<?> it) {
            if (!DEBUG) return;
            StringBuilder sb = new StringBuilder("[");
            for (Object o : it) sb.append(o).append(", ");
            if (sb.length() > 1) sb.setLength(sb.length() - 2);
            sb.append("]");
            System.err.println("[DBG] " + label + " = " + sb);
        }
    }

    // ═════════════════════════════════════════════════════════════════
    //  BIT  —  Bit Manipulation Toolkit
    // ═════════════════════════════════════════════════════════════════
    static class Bit {

        // ── Single-bit queries ──────────────────────────────────────

        /** Is the k-th bit (0-indexed) set in n? */
        public static boolean isSet(long n, int k) {
            boolean res = ((n >> k) & 1L) == 1L;
            DBG.log("Bit.isSet(" + n + ", " + k + ")", res);
            return res;
        }

        /** Set the k-th bit */
        public static long set(long n, int k) {
            long res = n | (1L << k);
            DBG.log("Bit.set(" + n + ", " + k + ")", res);
            return res;
        }

        /** Clear the k-th bit */
        public static long clear(long n, int k) {
            long res = n & ~(1L << k);
            DBG.log("Bit.clear(" + n + ", " + k + ")", res);
            return res;
        }

        /** Toggle the k-th bit */
        public static long toggle(long n, int k) {
            long res = n ^ (1L << k);
            DBG.log("Bit.toggle(" + n + ", " + k + ")", res);
            return res;
        }

        // ── Whole-number operations ─────────────────────────────────

        /** Number of set bits (popcount) */
        public static int popcount(long n) {
            int res = Long.bitCount(n);
            DBG.log("Bit.popcount(" + n + ")", res);
            return res;
        }

        /** Position of lowest set bit (0-indexed). Returns -1 if n==0. */
        public static int lsb(long n) {
            int res = n == 0 ? -1 : Long.numberOfTrailingZeros(n);
            DBG.log("Bit.lsb(" + n + ")", res);
            return res;
        }

        /** Position of highest set bit (0-indexed). Returns -1 if n==0. */
        public static int msb(long n) {
            int res = n == 0 ? -1 : 63 - Long.numberOfLeadingZeros(n);
            DBG.log("Bit.msb(" + n + ")", res);
            return res;
        }

        /** Isolate lowest set bit: n & (-n) */
        public static long lowBit(long n) {
            long res = n & (-n);
            DBG.log("Bit.lowBit(" + n + ")", res);
            return res;
        }

        /** Turn off lowest set bit: n & (n-1) */
        public static long clearLow(long n) {
            long res = n & (n - 1);
            DBG.log("Bit.clearLow(" + n + ")", res);
            return res;
        }

        /** Is n a power of two? */
        public static boolean isPow2(long n) {
            boolean res = n > 0 && (n & (n - 1)) == 0;
            DBG.log("Bit.isPow2(" + n + ")", res);
            return res;
        }

        /** Smallest power of two >= n */
        public static long nextPow2(long n) {
            if (n <= 1) return 1;
            long res = Long.highestOneBit(n - 1) << 1;
            DBG.log("Bit.nextPow2(" + n + ")", res);
            return res;
        }

        /** Reverse all 32 bits */
        public static int reverseBits(int n) {
            int res = Integer.reverse(n);
            DBG.log("Bit.reverseBits(" + n + ")", res);
            return res;
        }

        // ── XOR tricks ──────────────────────────────────────────────

        /** XOR from 1..n in O(1): pattern repeats every 4 */
        public static long xorTo(long n) {
            long res;
            switch ((int)(n % 4)) {
                case 0: res = n;     break;
                case 1: res = 1;     break;
                case 2: res = n + 1; break;
                default: res = 0;    break;
            }
            DBG.log("Bit.xorTo(" + n + ")", res);
            return res;
        }

        /** XOR of all integers in [l, r] */
        public static long xorRange(long l, long r) {
            long res = xorTo(r) ^ xorTo(l - 1);
            DBG.log("Bit.xorRange(" + l + "," + r + ")", res);
            return res;
        }

        // ── Subset enumeration ──────────────────────────────────────

        /**
         * Enumerate all non-empty subsets of mask.
         * Usage:
         *   for (int sub = Bit.firstSub(mask); sub > 0; sub = Bit.nextSub(sub, mask))
         *       process(sub);
         */
        public static int firstSub(int mask) { return mask; }
        public static int nextSub(int sub, int mask) { return (sub - 1) & mask; }

        /** Count of set bits in each number 0..n (DP in O(n)) */
        public static int[] popcountAll(int n) {
            int[] dp = new int[n + 1];
            for (int k = 1; k <= n; k++) dp[k] = dp[k >> 1] + (k & 1);
            DBG.log("Bit.popcountAll[0.." + n + "]", dp);
            return dp;
        }

        // ── Arithmetic shorthands ────────────────────────────────────

        /** Multiply by 2^k  (left shift) */
        public static long shl(long n, int k) { return n << k; }

        /** Divide by 2^k   (right shift) */
        public static long shr(long n, int k) { return n >> k; }

        /** n % (2^k) using bitmask — valid only for positive n */
        public static long modPow2(long n, int k) { return n & ((1L << k) - 1); }
    }

    // ═════════════════════════════════════════════════════════════════
    //  MOD  —  Modular Arithmetic
    // ═════════════════════════════════════════════════════════════════
    static class Mod {
        static final long MOD = 1_000_000_007L;

        public static long add(long a, long b) {
            long res = (a % MOD + b % MOD + MOD) % MOD;
            DBG.log("Mod.add(" + a + "+" + b + ")", res); return res;
        }
        public static long sub(long a, long b) {
            long res = (a % MOD - b % MOD + MOD) % MOD;
            DBG.log("Mod.sub(" + a + "-" + b + ")", res); return res;
        }
        public static long mul(long a, long b) {
            long res = (a % MOD) * (b % MOD) % MOD;
            DBG.log("Mod.mul(" + a + "*" + b + ")", res); return res;
        }
        public static long inv(long a) {
            return Mth.pow(a, MOD - 2, MOD);
        }
        public static long div(long a, long b) {
            return mul(a, inv(b));
        }
    }

    // ═════════════════════════════════════════════════════════════════
    //  MTH  —  Math Utilities
    // ═════════════════════════════════════════════════════════════════
    static class Mth {

        public static long gcd(long a, long b) {
            long res = b == 0 ? a : gcd(b, a % b);
            DBG.log("Mth.gcd(" + a + "," + b + ")", res); return res;
        }
        public static long lcm(long a, long b) {
            long res = a / gcd(a, b) * b;
            DBG.log("Mth.lcm(" + a + "," + b + ")", res); return res;
        }
        public static long pow(long b, long e, long m) {
            long res = 1; b %= m;
            while (e > 0) {
                if ((e & 1) == 1) res = res * b % m;
                b = b * b % m; e >>= 1;
            }
            DBG.log("Mth.pow(" + b + "^" + e + "%" + m + ")", res); return res;
        }
        public static boolean prime(long n) {
            if (n < 2) return false;
            if (n < 4) return true;
            if (n % 2 == 0 || n % 3 == 0) return false;
            for (long k = 5; k * k <= n; k += 6)
                if (n % k == 0 || n % (k + 2) == 0) return false;
            return true;
        }
        public static boolean[] sieve(int n) {
            boolean[] p = new boolean[n + 1];
            Arrays.fill(p, true);
            if (n >= 0) p[0] = false;
            if (n >= 1) p[1] = false;
            for (int i = 2; (long)i * i <= n; i++)
                if (p[i]) for (int j = i * i; j <= n; j += i) p[j] = false;
            DBG.msg("Mth.sieve done N=" + n); return p;
        }
        public static long abs(long a) { return a < 0 ? -a : a; }
        public static long min(long a, long b) { return a < b ? a : b; }
        public static long max(long a, long b) { return a > b ? a : b; }
    }

    // ═════════════════════════════════════════════════════════════════
    //  ARR  —  Array Utilities (binary search + prefix/suffix)
    // ═════════════════════════════════════════════════════════════════
    static class Arr {

        /** First index where a[i] >= x  (array must be sorted) */
        public static int lb(int[] a, int x) {
            int l = 0, h = a.length;
            while (l < h) { int m = (l + h) >>> 1; if (a[m] >= x) h = m; else l = m + 1; }
            DBG.log("Arr.lb for " + x, l); return l;
        }
        /** First index where a[i] > x */
        public static int ub(int[] a, int x) {
            int l = 0, h = a.length;
            while (l < h) { int m = (l + h) >>> 1; if (a[m] > x) h = m; else l = m + 1; }
            DBG.log("Arr.ub for " + x, l); return l;
        }
        public static long[] pSum(int[] a) {
            int n = a.length; long[] p = new long[n + 1];
            for (int k = 0; k < n; k++) p[k + 1] = p[k] + a[k];
            DBG.log("Arr.pSum", p); return p;
        }
        public static long[] sSum(int[] a) {
            int n = a.length; long[] s = new long[n + 1];
            for (int k = n - 1; k >= 0; k--) s[k] = s[k + 1] + a[k];
            DBG.log("Arr.sSum", s); return s;
        }
        public static int[] pMin(int[] a) {
            int n = a.length, p[] = new int[n]; if (n == 0) return p;
            p[0] = a[0]; for (int k = 1; k < n; k++) p[k] = Math.min(p[k-1], a[k]);
            DBG.log("Arr.pMin", p); return p;
        }
        public static int[] sMin(int[] a) {
            int n = a.length, s[] = new int[n]; if (n == 0) return s;
            s[n-1] = a[n-1]; for (int k = n-2; k >= 0; k--) s[k] = Math.min(s[k+1], a[k]);
            DBG.log("Arr.sMin", s); return s;
        }
        public static int[] pMax(int[] a) {
            int n = a.length, p[] = new int[n]; if (n == 0) return p;
            p[0] = a[0]; for (int k = 1; k < n; k++) p[k] = Math.max(p[k-1], a[k]);
            DBG.log("Arr.pMax", p); return p;
        }
        public static int[] sMax(int[] a) {
            int n = a.length, s[] = new int[n]; if (n == 0) return s;
            s[n-1] = a[n-1]; for (int k = n-2; k >= 0; k--) s[k] = Math.max(s[k+1], a[k]);
            DBG.log("Arr.sMax", s); return s;
        }
        public static long[] pGcd(int[] a) {
            int n = a.length; long[] p = new long[n]; if (n == 0) return p;
            p[0] = a[0]; for (int k = 1; k < n; k++) p[k] = Mth.gcd(p[k-1], a[k]);
            DBG.log("Arr.pGcd", p); return p;
        }
        public static long[] sGcd(int[] a) {
            int n = a.length; long[] s = new long[n]; if (n == 0) return s;
            s[n-1] = a[n-1]; for (int k = n-2; k >= 0; k--) s[k] = Mth.gcd(s[k+1], a[k]);
            DBG.log("Arr.sGcd", s); return s;
        }
    }

    // ═════════════════════════════════════════════════════════════════
    //  STK  —  Monotonic Stack
    // ═════════════════════════════════════════════════════════════════
    static class Stk {
        /** Next Greater Element values (-1 if none) */
        public static int[] nge(int[] a) {
            int n = a.length; int[] res = new int[n]; Arrays.fill(res, -1);
            Deque<Integer> st = new ArrayDeque<>();
            for (int k = n - 1; k >= 0; k--) {
                while (!st.isEmpty() && st.peek() <= a[k]) st.pop();
                if (!st.isEmpty()) res[k] = st.peek();
                st.push(a[k]);
            }
            DBG.log("Stk.nge", res); return res;
        }
        /** Next Greater Element indices (-1 if none) */
        public static int[] ngei(int[] a) {
            int n = a.length; int[] res = new int[n]; Arrays.fill(res, -1);
            Deque<Integer> st = new ArrayDeque<>();
            for (int k = n - 1; k >= 0; k--) {
                while (!st.isEmpty() && a[st.peek()] <= a[k]) st.pop();
                if (!st.isEmpty()) res[k] = st.peek();
                st.push(k);
            }
            DBG.log("Stk.ngei", res); return res;
        }
        /** Previous Smaller Element indices (-1 if none) */
        public static int[] psei(int[] a) {
            int n = a.length; int[] res = new int[n]; Arrays.fill(res, -1);
            Deque<Integer> st = new ArrayDeque<>();
            for (int k = 0; k < n; k++) {
                while (!st.isEmpty() && a[st.peek()] >= a[k]) st.pop();
                if (!st.isEmpty()) res[k] = st.peek();
                st.push(k);
            }
            DBG.log("Stk.psei", res); return res;
        }
    }

    // ═════════════════════════════════════════════════════════════════
    //  GRD  —  Grid Traversal
    // ═════════════════════════════════════════════════════════════════
    static class Grd {
        static final int[] dR = {-1, 1, 0, 0};
        static final int[] dC = { 0, 0,-1, 1};
        // 8-directional (uncomment if needed)
        // static final int[] dR8 = {-1,-1,-1, 0, 0, 1, 1, 1};
        // static final int[] dC8 = {-1, 0, 1,-1, 1,-1, 0, 1};

        public static boolean ok(int r, int c, int R, int C) {
            return r >= 0 && r < R && c >= 0 && c < C;
        }
        public static void dfs(int r, int c, char[][] g, boolean[][] vis) {
            vis[r][c] = true;
            DBG.log("Grd.dfs", r + "," + c);
            for (int k = 0; k < 4; k++) {
                int nr = r + dR[k], nc = c + dC[k];
                if (ok(nr, nc, g.length, g[0].length) && !vis[nr][nc] && g[nr][nc] != '#')
                    dfs(nr, nc, g, vis);
            }
        }
        public static void bfs(int sR, int sC, char[][] g, boolean[][] vis) {
            Queue<int[]> q = new ArrayDeque<>();
            q.add(new int[]{sR, sC}); vis[sR][sC] = true;
            DBG.log("Grd.bfs start", sR + "," + sC);
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                for (int k = 0; k < 4; k++) {
                    int nr = cur[0] + dR[k], nc = cur[1] + dC[k];
                    if (ok(nr, nc, g.length, g[0].length) && !vis[nr][nc] && g[nr][nc] != '#') {
                        vis[nr][nc] = true; q.add(new int[]{nr, nc});
                    }
                }
            }
        }
    }

    // ═════════════════════════════════════════════════════════════════
    //  DSU  —  Disjoint Set Union
    // ═════════════════════════════════════════════════════════════════
    static class DSU {
        int[] p, sz;
        public DSU(int n) {
            p = new int[n]; sz = new int[n];
            for (int k = 0; k < n; k++) { p[k] = k; sz[k] = 1; }
            DBG.msg("DSU init n=" + n);
        }
        public int find(int k) { return p[k] == k ? k : (p[k] = find(p[k])); }
        public boolean union(int a, int b) {
            int ra = find(a), rb = find(b);
            if (ra == rb) return false;
            if (sz[ra] < sz[rb]) { int t = ra; ra = rb; rb = t; }
            p[rb] = ra; sz[ra] += sz[rb];
            DBG.log("DSU.union", a + "+" + b); return true;
        }
        public boolean same(int a, int b) { return find(a) == find(b); }
        public int size(int a) { return sz[find(a)]; }
    }

    // ═════════════════════════════════════════════════════════════════
    //  ST  —  Segment Tree (Range Sum, Point Update)
    // ═════════════════════════════════════════════════════════════════
    static class ST {
        int n; long[] t;
        public ST(long[] a) {
            n = a.length; t = new long[4 * n];
            build(a, 0, 0, n - 1);
            DBG.msg("ST built n=" + n);
        }
        void build(long[] a, int node, int s, int e) {
            if (s == e) { t[node] = a[s]; return; }
            int m = (s + e) >> 1;
            build(a, 2*node+1, s, m); build(a, 2*node+2, m+1, e);
            t[node] = t[2*node+1] + t[2*node+2];
        }
        public void upd(int idx, long val) {
            DBG.log("ST.upd idx=" + idx, val); upd(0, 0, n-1, idx, val);
        }
        void upd(int nd, int s, int e, int idx, long val) {
            if (s == e) { t[nd] = val; return; }
            int m = (s + e) >> 1;
            if (idx <= m) upd(2*nd+1, s, m, idx, val);
            else          upd(2*nd+2, m+1, e, idx, val);
            t[nd] = t[2*nd+1] + t[2*nd+2];
        }
        public long q(int l, int r) {
            long res = q(0, 0, n-1, l, r);
            DBG.log("ST.q [" + l + "," + r + "]", res); return res;
        }
        long q(int nd, int s, int e, int l, int r) {
            if (r < s || e < l) return 0;
            if (l <= s && e <= r) return t[nd];
            int m = (s + e) >> 1;
            return q(2*nd+1, s, m, l, r) + q(2*nd+2, m+1, e, l, r);
        }
    }

    // ═════════════════════════════════════════════════════════════════
    //  BIT INDEXED TREE (Fenwick)  —  also named BIT to avoid clash
    // ═════════════════════════════════════════════════════════════════
    static class Fenwick {
        int n; long[] t;
        public Fenwick(int n) { this.n = n; t = new long[n + 1]; DBG.msg("Fenwick init n=" + n); }
        /** Add val at 1-indexed position i */
        public void add(int i, long val) {
            DBG.log("Fenwick.add i=" + i, val);
            for (; i <= n; i += i & -i) t[i] += val;
        }
        /** Prefix sum [1..i] */
        public long sum(int i) {
            long s = 0; for (; i > 0; i -= i & -i) s += t[i];
            DBG.log("Fenwick.sum [1.." + i + "]", s); return s;
        }
        /** Range sum [l..r] (1-indexed) */
        public long sum(int l, int r) { return sum(r) - sum(l - 1); }
    }

    // ═════════════════════════════════════════════════════════════════
    //  GEN  —  Test-case Generator
    // ═════════════════════════════════════════════════════════════════
    static class Gen {
        static final Random rnd = new Random(42); // fixed seed for reproducibility

        public static int i(int min, int max) {
            int v = min + rnd.nextInt(max - min + 1);
            DBG.log("Gen.i", v); return v;
        }
        public static long l(long min, long max) {
            long v = min + (long)(rnd.nextDouble() * (max - min + 1));
            DBG.log("Gen.l", v); return v;
        }
        public static int[] iArr(int sz, int min, int max) {
            int[] a = new int[sz]; for (int k = 0; k < sz; k++) a[k] = i(min, max);
            DBG.log("Gen.iArr", a); return a;
        }
        public static int[] edgeArr(int sz) {
            int[] pool = {0, 1, -1, Integer.MAX_VALUE, Integer.MIN_VALUE};
            int[] a = new int[sz];
            for (int k = 0; k < sz; k++) a[k] = pool[rnd.nextInt(pool.length)];
            DBG.log("Gen.edgeArr", a); return a;
        }
        public static String str(int len) {
            StringBuilder sb = new StringBuilder();
            for (int k = 0; k < len; k++) sb.append((char)('a' + rnd.nextInt(26)));
            String res = sb.toString(); DBG.log("Gen.str", res); return res;
        }
    }

    // ═════════════════════════════════════════════════════════════════
    //  SOLVE  —  Write your solution here
    // ═════════════════════════════════════════════════════════════════
    static PrintWriter out;

    static void solve() {
        // Example: read n and an array, then work
        int n = IO.i();
        int[] a = IO.iArr(n);

        // ── Bit manipulation examples ──────────────────────────────
        // Bit.isSet(a[0], 2)       → is bit-2 set in a[0]?
        // Bit.popcount(a[0])       → number of set bits
        // Bit.xorRange(1, n)       → XOR of 1..n in O(1)
        // int mask = (1 << n) - 1; → all-ones mask for n bits
        //
        // Subset enumeration:
        // for (int sub = Bit.firstSub(mask); sub > 0; sub = Bit.nextSub(sub, mask))
        //     process(sub);

        // ── Prefix sums ───────────────────────────────────────────
        // long[] ps = Arr.pSum(a);  → ps[r+1] - ps[l] = sum of a[l..r]

        // ── DSU example ───────────────────────────────────────────
        // DSU dsu = new DSU(n);
        // dsu.union(u, v);

        // ── Segment tree example ──────────────────────────────────
        // ST seg = new ST(lArr);
        // seg.upd(idx, val);
        // long ans = seg.q(l, r);

        // ── Modular arithmetic ────────────────────────────────────
        // long ans = Mod.mul(x, y);

        out.println(n); // replace with your answer
    }

    // ═════════════════════════════════════════════════════════════════
    //  MAIN
    // ═════════════════════════════════════════════════════════════════
    public static void main(String[] args) {
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int T = IO.i();          // number of test cases
        // int T = 1;            // single test case — use this for LeetCode-style input
        while (T-- > 0) solve();

        out.flush();
    }
}
