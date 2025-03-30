# NJITCS610
## Module 11 Assignment
### Problem Description
Consider the following expression BNF:

```plaintext
<expression> ::= <factor> * <expression> | <factor> / <expression> | <factor>

<factor> ::= <term> + <factor> | <term> - <factor> | <term>

<term> ::= { <expression> } | <literal>

<literal> ::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
```

Using recursive descent, and only recursive descent, scan expressions that adhere to this BNF to build their expression tree; write an integer valued function that scans the tree to evaluate the expression represented by the tree.

There are plenty of clever programs online that you can download to evaluate arithmetic expression tree; if you want zero in this assignment, download one and submit it as programming assignment #2; if you want a grade greater than zero, please follow these instructions. Thanks.

Input:

A numeric expression adhering to this BNF.
Output:

Some representation of the expression tree.
The result of evaluating the expression.

### Example Results 
Example results for input -
```
2*{3+4}-5*{1+5-10}
```
```
Evaluated Result: 20
Expression Tree:
|-- *
|   \-- *
|       \-- +
|           \-- -
|               \-- 1
|               |-- 5
|           |-- 1
|       |-- -
|       |   \-- 5
|       |   |-- +
|       |   |   \-- 4
|       |   |   |-- 3
|   |-- 2
```

### How to run the program
From terminal or command line (with java installed) -
<br>
javac ExpressionParser.java
<br>
java ExpressionParser "{Expression}"
<br>
