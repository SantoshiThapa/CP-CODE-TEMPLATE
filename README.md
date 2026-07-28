# ☕ Java Competitive Programming Template

A battle-tested Java template for **CodeChef**, **LeetCode**, **Codeforces**, and other online judges.  
Drop `Main.java` into your editor, write your logic in `solve()`, and go.

---

## 🚀 Quick Start

```java
// 1. Set DEBUG = false before submitting
static final boolean DEBUG = true;  // ← flip to false

// 2. Single test case (LeetCode / most problems)
int T = 1;

// 3. Multi test case (CodeChef / Codeforces)
int T = IO.i();
while (T-- > 0) solve();
```

---

## 📦 Classes at a Glance

| Class | Purpose |
|-------|---------|
| `IO` | Fast buffered input |
| `DBG` | Debug printing to stderr |
| `Bit` | Bit manipulation toolkit ⭐ |
| `Mod` | Modular arithmetic (1e9+7) |
| `Mth` | GCD, LCM, prime, sieve, fast-pow |
| `Arr` | Binary search + prefix/suffix arrays |
| `Stk` | Monotonic stack (NGE, PSE) |
| `Grd` | Grid BFS / DFS |
| `DSU` | Disjoint Set Union |
| `ST` | Segment tree (range sum, point update) |
| `Fenwick` | BIT / Fenwick tree |
| `Gen` | Random test-case generator |

---

## 📥 IO — Fast Input

```java
int    n   = IO.i();          // read int
long   x   = IO.l();          // read long
double d   = IO.d();          // read double
String s   = IO.next();       // read token
String ln  = IO.line();       // read full line

int[]    a  = IO.iArr(n);     // int array of n
long[]   la = IO.lArr(n);     // long array of n
String[] sa = IO.sArr(n);     // String array of n

int[][]  g  = IO.iGrid(n, m); // n×m int grid
char[][] cg = IO.cGrid(n);    // n rows of chars (each row = one token)
```

> **Why not Scanner?** `BufferedReader + StringTokenizer` is ~3–5× faster, which matters on tight TLEs.

---

## 🐛 DBG — Debug Utilities

All output goes to **stderr** so it never pollutes your answer.

```java
DBG.log("array a", a);          // prints int[], long[], Object[] smartly
DBG.log("value x", x);          // prints any primitive / object
DBG.grid("grid g", charGrid);   // pretty-prints char[][]
DBG.grid("grid g", intGrid);    // pretty-prints int[][]
DBG.msg("reached here");         // milestone log
DBG.iter("list", myList);        // prints any Iterable
```

**Set `DEBUG = false` before submitting** — all DBG calls become no-ops.

---

## ⚡ Bit — Bit Manipulation Toolkit

The class you asked for! Every method is also debug-logged when `DEBUG = true`.

### Single-bit operations

```java
Bit.isSet(n, k)     // is k-th bit set?           e.g. isSet(6, 1) → true  (6 = 110)
Bit.set(n, k)       // set k-th bit               e.g. set(4, 1)   → 6
Bit.clear(n, k)     // clear k-th bit             e.g. clear(7, 1) → 5
Bit.toggle(n, k)    // flip k-th bit              e.g. toggle(5, 0)→ 4
```

### Whole-number operations

```java
Bit.popcount(n)     // count of set bits          e.g. popcount(7)  → 3
Bit.lsb(n)          // index of lowest set bit    e.g. lsb(12)      → 2
Bit.msb(n)          // index of highest set bit   e.g. msb(12)      → 3
Bit.lowBit(n)       // isolate lowest bit: n&-n   e.g. lowBit(12)   → 4
Bit.clearLow(n)     // turn off lowest bit: n&n-1 e.g. clearLow(12) → 8
Bit.isPow2(n)       // is n a power of two?       e.g. isPow2(16)   → true
Bit.nextPow2(n)     // smallest 2^k >= n          e.g. nextPow2(5)  → 8
Bit.reverseBits(n)  // reverse all 32 bits (int)
```

### XOR tricks (O(1))

```java
Bit.xorTo(n)            // XOR of 1 ^ 2 ^ ... ^ n
Bit.xorRange(l, r)      // XOR of l ^ (l+1) ^ ... ^ r

// Classic usage — find the single non-duplicate:
int xor = 0;
for (int x : a) xor ^= x;  // all duplicates cancel out
```

### Bitmask / subset tricks

```java
int mask = (1 << n) - 1;     // all-ones mask for n bits

// Check if set i is a subset of set j:
boolean sub = (i & j) == i;

// Enumerate all non-empty subsets of mask:
for (int sub = Bit.firstSub(mask); sub > 0; sub = Bit.nextSub(sub, mask)) {
    // process subset `sub`
}

// Count set bits for every number 0..n (DP, O(n)):
int[] cnt = Bit.popcountAll(n);
```

### Arithmetic shorthands

```java
Bit.shl(n, k)       // n * 2^k  (left shift)
Bit.shr(n, k)       // n / 2^k  (right shift)
Bit.modPow2(n, k)   // n % (2^k)  — faster than %  (positive n only)
```

### Common patterns cheatsheet

```java
// Odd / even check
if ((n & 1) == 1) { /* odd */ }

// Swap without temp
a ^= b; b ^= a; a ^= b;

// Absolute value (branchless, for 32-bit int)
int mask = n >> 31;
int abs  = (n + mask) ^ mask;

// Divide by 2 (floor)
int half = n >> 1;

// Multiply by 2
int dbl = n << 1;

// All-ones mask for lowest k bits
long mask = (1L << k) - 1;
```

---

## ➕ Mod — Modular Arithmetic (MOD = 1e9+7)

```java
Mod.add(a, b)   // (a + b) % MOD
Mod.sub(a, b)   // (a - b) % MOD
Mod.mul(a, b)   // (a * b) % MOD
Mod.inv(a)      // modular inverse of a  (Fermat's little theorem)
Mod.div(a, b)   // (a / b) % MOD
```

---

## 🔢 Mth — Math Utilities

```java
Mth.gcd(a, b)       // greatest common divisor
Mth.lcm(a, b)       // least common multiple
Mth.pow(b, e, m)    // b^e % m  (fast exponentiation)
Mth.prime(n)        // primality test O(√n)
Mth.sieve(n)        // boolean[] of primes up to n
Mth.abs(a)          // absolute value (long)
Mth.min(a, b)       // min of two longs
Mth.max(a, b)       // max of two longs
```

---

## 📊 Arr — Array Utilities

```java
Arr.lb(a, x)    // lower bound: first index where a[i] >= x
Arr.ub(a, x)    // upper bound: first index where a[i] >  x

Arr.pSum(a)     // prefix sums  → ps[r+1] - ps[l] = sum of a[l..r]
Arr.sSum(a)     // suffix sums
Arr.pMin(a)     // prefix minimums
Arr.sMin(a)     // suffix minimums
Arr.pMax(a)     // prefix maximums
Arr.sMax(a)     // suffix maximums
Arr.pGcd(a)     // prefix GCDs
Arr.sGcd(a)     // suffix GCDs
```

---

## 📚 Stk — Monotonic Stack

```java
Stk.nge(a)      // Next Greater Element values  (−1 if none)
Stk.ngei(a)     // Next Greater Element indices (−1 if none)
Stk.psei(a)     // Previous Smaller Element indices (−1 if none)
```

---

## 🗺️ Grd — Grid Traversal

```java
Grd.ok(r, c, R, C)          // bounds check
Grd.dfs(r, c, grid, vis)    // 4-directional DFS  ('#' = wall)
Grd.bfs(r, c, grid, vis)    // 4-directional BFS  ('#' = wall)
```

For 8-directional movement, uncomment `dR8`/`dC8` in `Grd` and adjust the loop to `k < 8`.

---

## 🔗 DSU — Disjoint Set Union

```java
DSU dsu = new DSU(n);
dsu.union(u, v);            // merge components
dsu.same(u, v);             // same component?
dsu.size(u);                // size of u's component
```

---

## 🌲 ST — Segment Tree (Range Sum)

```java
ST seg = new ST(longArray);
seg.upd(idx, val);          // point update (0-indexed)
seg.q(l, r);                // range sum [l, r] (0-indexed)
```

---

## 🌲 Fenwick — BIT / Fenwick Tree

```java
Fenwick fen = new Fenwick(n);
fen.add(i, val);            // add val at 1-indexed position i
fen.sum(i);                 // prefix sum [1..i]
fen.sum(l, r);              // range sum  [l..r]  (1-indexed)
```

---

## 🎲 Gen — Test-case Generator

Useful for stress testing against a brute-force solution.

```java
int    x  = Gen.i(1, 100);          // random int in [1, 100]
long   y  = Gen.l(0L, 1_000_000L); // random long
int[]  a  = Gen.iArr(n, 0, 100);   // random int array
int[]  e  = Gen.edgeArr(n);         // edge cases: 0, 1, -1, INT_MAX, INT_MIN
String s  = Gen.str(10);            // random lowercase string of length 10
```

---

## ✅ Pre-submit Checklist

- [ ] `DEBUG = false`
- [ ] `T = IO.i()` or `T = 1` set correctly
- [ ] `out.flush()` present at end of `main`
- [ ] No leftover `System.out.println` debug prints
- [ ] `long` used wherever overflow is possible (sum > 2×10⁹)
- [ ] Array sizes not off-by-one (especially 1-indexed Fenwick)

---

