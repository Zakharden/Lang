package First.MyLanguage;

public class UnOpNode extends Node {
    Token oper;
    Node value;

    public UnOpNode(Token oper, Node value) {
        this.oper = oper;
        this.value = value;
    }
}
