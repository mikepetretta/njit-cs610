# NJITCS610
## Module 13 Assignment
### Problem Description

This document describes an iterative algorithm to build successive graphs  
\(G_2, G_3, \dots, G_N\) (where \(N\) is the number of nodes in the graph),  
and then to assemble the **transitive closure** \(G^+\) of a directed graph \(G\).

---

### Definitions

- **Original graph** \(G\):  
  A directed graph on vertices \(\{1,2,\dots,N\}\) with adjacency relation \(G[i,j]\).

- **Path-length graph** \(G_k\):  
  A graph with an arc \((i \to j)\) if and only if there exists a path of **exactly** length \(k\) in \(G\) from \(i\) to \(j\).

- **Transitive closure** \(G^+\):  
  A graph with an arc \((i \to j)\) if and only if there exists **any** path in \(G\) of length \(1,2,\dots,N\) from \(i\) to \(j\).

---

### Computing \(G_k\) Iteratively

To compute \(G_k\) from \(G_{k-1}\) and the original adjacency matrix \(G\), use:

```pseudo
for i = 1 to N:
    for j = 1 to N:
        Gk[i, j] ← false
        for h = 1 to N:
            -- if there's a (k-1)-length path i→h and a direct edge h→j
            Gk[i, j] ← Gk[i, j] OR ( G_{k-1}[i, h] AND G[h, j] )
```

### Example Results
Example results for input -
```
g.addEdge(0, 1);
g.addEdge(1, 2);
g.addEdge(2, 0);
g.addEdge(1, 3);
g.addEdge(3, 4);
```
```
Transitive closure matrix:
1 1 1 1 1 
1 1 1 1 1 
1 1 1 1 1 
0 0 0 0 1 
0 0 0 0 0 
```

### How to run the program
From terminal or command line (with java installed) -
<br>
javac Assignment.java
<br>
java Assignment
<br>
