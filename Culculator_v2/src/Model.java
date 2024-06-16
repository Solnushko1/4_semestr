package repository;

import java.util.Stack;

public class Model {
    public static double solveEquation(String equation) {
        if (!isBalanced(equation)) {
            throw new IllegalArgumentException("Unbalanced parentheses");
        }

        try {
            return eval(equation);
        } catch (ArithmeticException e) {
            return Double.NaN;
        }
    }

    private static boolean isBalanced(String equation) {
        Stack<Character> stack = new Stack<>();
        for (char ch : equation.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    private static double eval(String equation) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < equation.length()) ? equation.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            boolean eat(String strToEat) {
                int len = strToEat.length();
                for (int i = 0; i < len; i++) {
                    if (pos + i >= equation.length() || equation.charAt(pos + i) != strToEat.charAt(i)) {
                        return false;
                    }
                }
                pos += len - 1;
                nextChar();
                return true;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < equation.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                int termCount = 1;
                for (; ; ) {
                    if (eat('+')) {
                        if (termCount >= 15) throw new RuntimeException("Too many terms");
                        x += parseTerm();
                        termCount++;
                    } else if (eat('-')) {
                        if (termCount >= 15) throw new RuntimeException("Too many terms");
                        x -= parseTerm();
                        termCount++;
                    } else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else if (eat('^') || eat("**")) x = Math.pow(x, parseFactor());
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(equation.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') {
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = equation.substring(startPos, this.pos);
                    x = parseFactor();
                    switch (func) {
                        case "sqrt": x = Math.sqrt(x); break;
                        case "sin": x = Math.sin(Math.toRadians(x)); break;
                        case "cos": x = Math.cos(Math.toRadians(x)); break;
                        case "tan": x = Math.tan(Math.toRadians(x)); break;
                        case "log": x = Math.log(x) / Math.log(2); break;
                        case "exp": x = Math.exp(x); break;
                        case "fact": x = factorial(x); break;
                        default: throw new RuntimeException("Unknown function: " + func);
                    }
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^') || eat("**")) x = Math.pow(x, parseFactor());

                return x;
            }

            private double factorial(double x) {
                if (x < 0) throw new RuntimeException("Factorial is undefined for negative numbers");
                if (x == 0) return 1;
                int n = (int) x;
                double result = 1;
                for (int i = 1; i <= n; i++) {
                    result *= i;
                }
                return result;
            }
        }.parse();
    }
}
