package DataStrucures;

public class Stackk {

    private int capacity;
    private int[] stackArray;
    private int top;
    private String line = "\u001B[31m|\u001B[0m";
    private String line_ = "\u001B[31m-----\u001B[0m";

    public Stackk(int stackSize) {
        stackArray = new int[stackSize];
        top = -1;
        capacity = stackSize;
    }

    public void push(int data) {

        if (top < capacity - 1) {
            stackArray[++top] = data;// here top is incremneted first means first top<--top+1; get exicuted
            System.out.printf("Value %d added to the Stack \n", data);
        } else {
            System.out.println("Stack is overFlowed !");
        }
    }

    public void pop() {
        if (top == -1) {
            System.out.println("Stack underFlow !");
        } else {

            System.out.printf("Element %d deleted \n", stackArray[top]);
            top--;

        }
    }

    public int popp() {

        if (top == -1) {
            System.out.println("Stack underFlow !");
            return -1;
        } else {
            int delete = stackArray[top];
            System.out.printf("Element %d deleted \n", delete);
            top--;
            return delete;

        }
    }

    public void display() {
        if (top == -1) {
            System.out.println("Stack is empty !");
        } else {
            System.out.println("\u001b[41m Top \u001b[0m\n  | ");
            for (int i = top; i >= 0; i--) {

                System.out.println(line + " " + stackArray[i] + " " + line + "");
            }
            System.out.println(line_);
        }
    }

    public void peep(int i) {
        if (top - i + 1 <= -1) {
            System.out.println("Underflow");
        } else {
            System.out.println(stackArray[top - i + 1] + "<-- is at the i = " + i + "th position");
        }

    }

    public int peek() {
        if (top != -1) {
            return stackArray[top];
        }
        System.out.println("Empty Stack");
        return -1;
    }

}
// For Testing
/*
 * public static void main(String[] args) {
 * Stack s = new Stack(10);
 * s.display();
 * s.pop();
 * 
 * System.out.println(s.line__);
 * System.out.println("Top-->" + s.top);
 * System.out.println(s.line__);
 * s.push(7);
 * s.push(6);
 * s.push(5);
 * s.push(4);
 * s.push(3);
 * s.push(2);
 * s.push(1);
 * System.out.println(s.line__);
 * s.display();
 * while (s.top != -1) {
 * s.pop();
 * s.display();
 * System.out.println(s.peek());
 * s.peep(4);
 * }
 * 
 * // evolution of postfix
 * System.out.println(s.line__);
 * System.out.println("Ans " + s.evaluatePostfix("23+5+"));
 * System.out.println("Ans " + s.evaluatePostfix("23+62/-3*"));//
 * ((2+3)-(6/2))*3)
 * 
 * // evolution of prefix
 * System.out.println(s.line__);
 * System.out.println("Ans " + s.evaluatePrefix("+++235"));
 * System.out.println("Ans " + s.evaluatePrefix("*-+23/623"));//
 * ((2+3)-(6/2))*3)
 * }
 * 
 * 
 */// Exteas
/*
 * 
 * int evaluatePrefix(String prefix) {
 * Stack stack = new Stack(prefix.length());
 * for (int i = prefix.length() - 1; i >= 0; i--) {
 * char ch = prefix.charAt(i);
 * if (Character.isDigit(ch)) {
 * stack.push(Character.getNumericValue(ch));
 * } else if (isOperator(ch)) {
 * int operand1 = stack.popp();
 * int operand2 = stack.popp();
 * int reasult = stack.pefromOperation(operand1, operand2, ch);
 * stack.push(reasult);
 * }
 * }
 * return stack.popp();
 * }
 * 
 * int evaluatePostfix(String postfix) {
 * Stack stack = new Stack(postfix.length());
 * for (char c : postfix.toCharArray()) {
 * if (Character.isDigit(c)) {
 * stack.push(Character.getNumericValue(c));
 * } else if (stack.isOperator(c)) {
 * int operand2 = stack.popp();
 * int operand1 = stack.popp();
 * int reasult = stack.pefromOperation(operand1, operand2, c);
 * stack.push(reasult);
 * }
 * }
 * return stack.popp();
 * }
 * 
 * boolean isOperator(char ch) {
 * return ch == '+' || ch == '-' || ch == '*' || ch == '/';
 * }
 * 
 * int pefromOperation(int operand1, int operand2, char Operator) {
 * switch (Operator) {
 * case '+': {
 * return operand1 + operand2;
 * }
 * case '-': {
 * return operand1 - operand2;
 * }
 * case '*': {
 * return operand1 * operand2;
 * }
 * case '/': {
 * if (operand2 != 0) {
 * return operand1 / operand2;
 * } else {
 * throw new ArithmeticException("Divided by zero !");
 * }
 * }
 * default: {
 * throw new IllegalArgumentException("Invalid operator " + Operator);
 * }
 * 
 * }
 * }
 * 
 * 
 * 
 */
