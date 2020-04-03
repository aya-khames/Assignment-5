package eg.edu.alexu.csd.datastructure.stack.cs02;
import java.util.Scanner;

public class ExpressionEvaluator implements IExpressionEvaluator{
    /**
     * take a character and check if it is a operator or not
     * @param c char to check
     * @return 1 if + or - ,2 if * or / ,0 otherwise
     */

    public int isOperator(char c) {
        switch(c) {
            case '+':
                return 1;
            case '-':
                return 1;
            case '*':
                return 2;
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    /**
     * Takes a symbolic/numeric infix expression as input and converts it to
     * postfix notation. There is no assumption on spaces between terms or the
     * length of the term (e.g., two digits symbolic or numeric term)
     * @param expression
     * infix expression
     * @return postfix expression, or through exception if
     * invalid infix
     */

    @Override
    public String infixToPostfix(String expression) {
        if(expression == null)
            throw new RuntimeException("Invalid Infix");
        Stack s = new Stack();
        String postfix ="";
        boolean operator = false;
        boolean sign = false;
        for(int i =0;i<expression.length();i++) {
            if(expression.charAt(i)=='('&&!operator) {
                if(sign)
                    postfix += "0 ";
                int j = ++i;
                int n =0;
                for(;expression.charAt(i)!=')'||((expression.charAt(i)==')')&&n>0);i++) {
                    if(expression.charAt(i)==')')
                        n--;
                    if(expression.charAt(i)=='(')
                        n++;
                    if((i+1)==expression.length())
                        throw new RuntimeException("Invalid Infix (bracket not closed)");
                }
                String str = infixToPostfix(expression.substring(j, i));
                if(str== null)
                    throw new RuntimeException("Invalid Infix (inside of bracket)");
                postfix += str ;
                operator = true;
                if(sign) {
                    sign = false;
                    postfix += s.pop()+" ";
                }
            }
            else if(Character.isDigit(expression.charAt(i)) && !operator) {
                if(sign)
                    postfix += "0 ";
                while(i<expression.length()&&Character.isDigit(expression.charAt(i))) {
                    postfix += (expression.charAt(i++));
                }
                i--;
                postfix +=" ";
                operator = true;
                if(sign) {
                    sign = false;
                    postfix += s.pop()+" ";
                }
            }
            else if((isOperator(expression.charAt(i))==1&&!sign)||((isOperator(expression.charAt(i))==2)&&operator&&!sign)) {
                if(s.isEmpty())
                    s.push(expression.charAt(i));
                else {
                    if(isOperator((char) s.peek())==2&&operator) {
                        postfix += s.pop() + " ";
                        s.push(expression.charAt(i));
                    }
                    else if(operator&&isOperator(expression.charAt(i))!=2) {//here
                        postfix += s.pop() + " ";
                        s.push(expression.charAt(i));
                    }
                    else
                        s.push(expression.charAt(i));
                }
                if(!operator)
                    sign = true;
                operator = false;
            }
            else if(!operator&&!(expression.charAt(i)==' ')) {
                if(sign)
                    postfix += "0 ";
                postfix += (expression.charAt(i)) + " ";
                operator = true;
                operator = true;
                if(sign) {
                    sign = false;
                    postfix += s.pop()+" ";
                }
            }
            else if(!(expression.charAt(i)==' '))
                throw new RuntimeException("Invalid Infix");
        }
        while(!s.isEmpty())
            postfix += s.pop() + " ";
        if(!operator)
            throw new RuntimeException("Invalid Infix");
        return postfix;
    }

    /**
     * Evaluate a postfix numeric or symbolic expression, with a single space separator
     *if symbolic expression read from user value of each symbol
     * @param expression
     * postfix expression
     * @return the expression evaluated value, or through exception if
     * invalid postfix
     */

    @Override
    public int evaluate(String expression) {
        if(expression == null)
            throw new RuntimeException("Invalid Postfix");
        Stack s = new Stack();
        boolean space = false;
        boolean dividebyzero = false;
        if(expression.charAt(0) == ' ')
            space = true;
        for(int i =0;i<expression.length();i++){
            if(space) {
                space=false;
                if(expression.charAt(i) != ' ')
                    throw new RuntimeException("Invalid Postfix");
            }else {
                try {
                    switch(expression.charAt(i)) {
                        case '+':
                            s.push((float) s.pop()+(float) s.pop());
                            break;
                        case '-':
                            s.push(-(float) s.pop()+(float)s.pop());
                            break;
                        case '*':
                            s.push((float) s.pop()*(float) s.pop());
                            break;
                        case '/':
                            if((float)s.peek()==0) {
                                dividebyzero = true;
                                throw new RuntimeException("can't divide by zero");
                            }
                            s.push((1/(float) s.pop())*(float) s.pop());
                            break;
                        default:
                            float x=0;
                            if(!Character.isDigit(expression.charAt(i))) {
                                Scanner in = new Scanner(System.in);
                                System.out.println("please enter value of "+expression.charAt(i));
                                String str = in.next();
                                int j =0;
                                while(!Character.isDigit(str.charAt(j))) {
                                    System.out.println("(invalid value)please enter a intger");
                                    str = in.next();
                                }
                                str+=" ";
                                while(Character.isDigit(str.charAt(j))) {
                                    x *= 10;
                                    x += Character.getNumericValue(str.charAt(j++));
                                }
                                i++;
                            }
                            while(i<expression.length()&&Character.isDigit(expression.charAt(i))) {
                                x *= 10;
                                x += Character.getNumericValue(expression.charAt(i++));
                            }
                            s.push(x);
                            i--;
                    }
                    space = true;
                }
                catch(Exception EmptyStackException) {
                    if(dividebyzero)
                        throw new RuntimeException("can't divide by zero");
                    throw new RuntimeException("Invalid Postfix");
                }
            }
        }
        float x = (float) s.pop();
        if(!s.isEmpty())
            throw new RuntimeException("Invalid Postfix");
        return (int)x;
    }

    /**
     * Tests if the element is a symbol
     * @param b
     * element
     * @return true if element is symbol
     */

    public boolean isSymbol (char b) {
        return (b >= 'a' && b <= 'z') || (b >= 'A' && b <= 'Z');
    }
}