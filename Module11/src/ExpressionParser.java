public class ExpressionParser {

    public static void main(String[] args) {
        String input = "2*{3+4}-5*{1+5-10}";
        ExpressionParser parser = new ExpressionParser();
        ParseResult parseResult = parser.parseExpression(input, 0);
        System.out.println("Evaluated Result: " + parser.evaluate(parseResult.node));
        System.out.println("Expression Tree:");
        parser.printTree(parseResult.node, "", true);
    }

    // Evaluates the value of the expression based on the constructed tree
    private int evaluate(final Node node) {
        if (node.left == null && node.right == null) {
            return Character.getNumericValue(node.value);
        }
        int leftVal = evaluate(node.left);
        int rightVal = evaluate(node.right);
        return switch (node.value) {
            case '+' -> leftVal + rightVal;
            case '-' -> leftVal - rightVal;
            case '*' -> leftVal * rightVal;
            case '/' -> leftVal / rightVal;
            default -> throw new IllegalArgumentException("Unknown operator: " + node.value);
        };
    }

    // Prints the tree visually
    private static void printTree(Node node, String prefix, boolean isLeft) {
        if (node == null) {
            return;
        }
        System.out.println(prefix + (isLeft ? "|-- " : "\\-- ") + node.value);
        // Recursively print the right and left subtrees
        printTree(node.right, prefix + (isLeft ? "|   " : "    "), false);
        printTree(node.left, prefix + (isLeft ? "|   " : "    "), true);
    }

    // <expression> ::= <factor> * <expression> | <factor> / <expression> | <factor>
    private ParseResult parseExpression(String expression, int index) {
        ParseResult leftResult = parseFactor(expression, index);
        index = leftResult.index;
        if (index < expression.length() && (expression.charAt(index) == '*' || expression.charAt(index) == '/')) {
            char operator = expression.charAt(index++);
            ParseResult rightResult = parseExpression(expression, index);
            return new ParseResult(new Node(operator, leftResult.node, rightResult.node), rightResult.index);
        }
        return leftResult;
    }

    // <factor> ::= <term> + <factor> | <term> - <factor> | <term>
    private ParseResult parseFactor(String expression, int index) {
        ParseResult leftResult = parseTerm(expression, index);
        index = leftResult.index;
        if (index < expression.length() && (expression.charAt(index) == '+' || expression.charAt(index) == '-')) {
            char operator = expression.charAt(index++);
            ParseResult rightResult = parseFactor(expression, index);
            return new ParseResult(new Node(operator, leftResult.node, rightResult.node), rightResult.index);
        }
        return leftResult;
    }

    // <term> ::= { <expression> } | <literal>
    private ParseResult parseTerm(String expression, int index) {
        if (expression.charAt(index) == '{') {
            index++; // Consume '{'
            ParseResult result = parseExpression(expression, index);
            index = result.index;
            if (expression.charAt(index) == '}') {
                index++; // Consume '}'
            }
            return new ParseResult(result.node, index);
        } else {
            return parseLiteral(expression, index);
        }
    }

    // <literal> ::= 0|1|2|3|4|5|6|7|8|9
    private ParseResult parseLiteral(String expression, int index) {
        char num = expression.charAt(index++);
        return new ParseResult(new Node(num), index);
    }
}

class ParseResult {

    Node node;
    int index;

    ParseResult(final Node node, final int index) {
        this.node = node;
        this.index = index;
    }
}

class Node {

    char value;
    Node left;
    Node right;

    public Node(char value) {
        this.value = value;
    }

    public Node(char value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
