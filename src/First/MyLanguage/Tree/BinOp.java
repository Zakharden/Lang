package First.MyLanguage;

public class BinOp extends Node{
    Token oper;
    Node lVal;
    Node rVal;
    public BinOp(Token oper, Node lVal, Node rVal) {
        super();
        this.oper = oper;
        this.lVal = lVal;
        this.rVal = rVal;
    }
}
