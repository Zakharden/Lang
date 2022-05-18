package First.MyLanguage;

public class UnOp extends Node {
    Token oper;
    Node value;

    public UnOp(Token oper, Node value) {
        this.oper = oper;
        this.value = value;
    }
}
