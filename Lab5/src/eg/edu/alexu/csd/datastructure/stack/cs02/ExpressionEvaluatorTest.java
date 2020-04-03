package eg.edu.alexu.csd.datastructure.stack.cs02;

import static org.junit.jupiter.api.Assertions.*;
import java.util.EmptyStackException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExpressionEvaluatorTest {


    @Test
    void testIsOperator() {
        ExpressionEvaluator EE = new ExpressionEvaluator();
        //operators
        assertEquals(1,EE.isOperator('+'));
        assertEquals(1,EE.isOperator('-'));
        assertEquals(2,EE.isOperator('*'));
        assertEquals(2,EE.isOperator('/'));
        //others
        assertEquals(0,EE.isOperator('('));
        assertEquals(0,EE.isOperator(')'));
        assertEquals(0,EE.isOperator('x'));
        assertEquals(0,EE.isOperator('X'));
        assertEquals(0,EE.isOperator('0'));
        assertEquals(0,EE.isOperator('9'));
        assertEquals(0,EE.isOperator('a'));
    }

    @Test
    void testInfixToPostfix() {
        ExpressionEvaluator EE = new ExpressionEvaluator();
        //Valid expression
        assertEquals("3 2 + ",EE.infixToPostfix("3+2"));
        assertEquals("5 6 * ",EE.infixToPostfix("5 *6"));
        assertEquals("3 4 / ",EE.infixToPostfix("3/ 4"));
        assertEquals("000 3 - ",EE.infixToPostfix("000 - 3  "));
        assertEquals("040 2 + 2 - ",EE.infixToPostfix("    040+2-2      "));
        assertEquals("0 ",EE.infixToPostfix(" 0"));
        assertEquals("030001 ",EE.infixToPostfix(" 030001  "));
        assertEquals("a b c * + ",EE.infixToPostfix("a+ b* c"));
        assertEquals("0 3 * 2 / ",EE.infixToPostfix("0*3/2"));
        assertEquals("6 54 + 2 * ",EE.infixToPostfix("(6+54)  *2"));
        assertEquals("0 A - ",EE.infixToPostfix("-A"));
        assertEquals("$ 0 @ + + ",EE.infixToPostfix("$ ++ @"));
        assertEquals("0 0 22 - 2 / - ",EE.infixToPostfix("-(-22/2)"));
        assertEquals("0 0 0 0 966 - - - - ",EE.infixToPostfix("-(-(-(-966)))"));
        assertEquals("30 0 31 + + 4 7 - * ",EE.infixToPostfix("(30++31)* (4-7)"));
        assertEquals("3 4 44 43 - + * ",EE.infixToPostfix(" 3 * (4+(44-(43)))"));
        assertEquals("700 33 / ",EE.infixToPostfix("(700/33)"));
        assertEquals("x 0 y z * - - ",EE.infixToPostfix("x--(y*z)"));
        assertEquals("8 999 / 222 - ",EE.infixToPostfix("8 / 999-222"));
        assertEquals("0 a - 0 b + - 0 45 - + ",EE.infixToPostfix("-a-+b+-45"));
        assertEquals("89 0 00090 - + ",EE.infixToPostfix("(89) +   -00090"));
        assertEquals("999 88 98 * - ",EE.infixToPostfix("999-88*(98)"));
        assertEquals("88 0 99 - / 0 88 + * ",EE.infixToPostfix("88/-99*+88"));
        assertEquals("0 78 + 0 99 - 0 9 + * - ",EE.infixToPostfix("+ 78--99*+9"));
        assertEquals("20 0 2 7 - 0 5 4 + 8 1 - - - + - / ",EE.infixToPostfix("20/-(2-7+-(5+4-(8-1)))"));
        //Invalid expression
        Assertions.assertThrows(RuntimeException.class,()->EE.infixToPostfix(null));
        Assertions.assertThrows(RuntimeException.class,()->EE.infixToPostfix(""));
        Assertions.assertThrows(RuntimeException.class,()->EE.infixToPostfix("+"));
        Assertions.assertThrows(RuntimeException.class,()->EE.infixToPostfix("(9"));
        Assertions.assertThrows(RuntimeException.class,()->EE.infixToPostfix("90+"));
        Assertions.assertThrows(RuntimeException.class,()->EE.infixToPostfix("XY"));
        Assertions.assertThrows(RuntimeException.class,()->EE.infixToPostfix("4+*2"));
        Assertions.assertThrows(RuntimeException.class,()->EE.infixToPostfix("4-/7"));
        Assertions.assertThrows(RuntimeException.class,()->EE.infixToPostfix("4//2"));
        Assertions.assertThrows(RuntimeException.class,()->EE.infixToPostfix("4+++3"));
        Assertions.assertThrows(RuntimeException.class,()->EE.infixToPostfix("--5"));
        Assertions.assertThrows(RuntimeException.class,()->EE.infixToPostfix("-(*0)"));
        Assertions.assertThrows(RuntimeException.class,()->EE.infixToPostfix("(5)(3)"));
        Assertions.assertThrows(RuntimeException.class,()->EE.infixToPostfix("*90"));
        Assertions.assertThrows(RuntimeException.class,()->EE.infixToPostfix("/a"));
        Assertions.assertThrows(RuntimeException.class,()->EE.infixToPostfix("p44"));
        Assertions.assertThrows(RuntimeException.class,()->EE.infixToPostfix("((45+4)+3"));
        Assertions.assertThrows(RuntimeException.class,()->EE.infixToPostfix("3(4+2)"));
    }

    @Test
    void testEvaluate() {
        ExpressionEvaluator EE = new ExpressionEvaluator();
        //Valid expression
        assertEquals(0,EE.evaluate("0 "));
        assertEquals(6,EE.evaluate(" 6"));
        assertEquals(94,EE.evaluate("4 90 + "));
        assertEquals(-3,EE.evaluate("6 009 -"));
        assertEquals(2,EE.evaluate("4 2 /"));
        assertEquals(0,EE.evaluate("3 00 *"));
        assertEquals(-9,EE.evaluate("0 9 -"));
        assertEquals(100,EE.evaluate("10 10 * 00 +"));
        assertEquals(0,EE.evaluate("0 9 /"));
        assertEquals(8,EE.evaluate("7 0 * 8 +"));
        assertEquals(9,EE.evaluate("9 0 - "));
        assertEquals(400,EE.evaluate("900 02 / 50 -"));
        assertEquals(1,EE.evaluate("3 2 /"));
        assertEquals(3,EE.evaluate("3 2 / 3 2 / +"));
        assertEquals(2,EE.evaluate("20 0 2 7 - 0 5 4 + 8 1 - - - + - / "));
        //Invalid expression
        Assertions.assertThrows(RuntimeException.class,()->EE.evaluate(null));
        Assertions.assertThrows(RuntimeException.class,()->EE.evaluate(""));
        Assertions.assertThrows(RuntimeException.class,()->EE.evaluate("3 3+"));
        Assertions.assertThrows(RuntimeException.class,()->EE.evaluate("3 + 3"));
        Assertions.assertThrows(RuntimeException.class,()->EE.evaluate("9 9"));
        Assertions.assertThrows(RuntimeException.class,()->EE.evaluate(" 10 0 /"));
        Assertions.assertThrows(RuntimeException.class,()->EE.evaluate("8 9 9 -"));
        Assertions.assertThrows(RuntimeException.class,()->EE.evaluate("-9 2 -"));
        Assertions.assertThrows(RuntimeException.class,()->EE.evaluate("0 0 /"));
        Assertions.assertThrows(RuntimeException.class,()->EE.evaluate("2 2 + +"));
        Assertions.assertThrows(RuntimeException.class,()->EE.evaluate("+"));
        Assertions.assertThrows(RuntimeException.class,()->EE.evaluate("8a"));
        Assertions.assertThrows(RuntimeException.class,()->EE.evaluate("99-"));
        Assertions.assertThrows(RuntimeException.class,()->EE.evaluate("9 9 ++"));
        Assertions.assertThrows(RuntimeException.class,()->EE.evaluate("98 9 *-"));
        Assertions.assertThrows(RuntimeException.class,()->EE.evaluate("/ 9 9"));
        Assertions.assertThrows(RuntimeException.class,()->EE.evaluate("90 9 0"));
    }
}