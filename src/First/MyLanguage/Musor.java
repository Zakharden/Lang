package First.MyLanguage;

public class Musor extends Node{
    Token oper;
    Node lVal;
    Node rVal;
    public Musor(Token oper, Node lVal, Node rVal) {
        super();
        this.oper = oper;
        this.lVal = lVal;
        this.rVal = rVal;
    }
}
