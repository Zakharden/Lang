package First.MyLanguage;

import java.util.ArrayList;

public class FNode extends Node{
    Token oper;
    Node lVal;
    Node rVal;
    Node action;
    public ArrayList<Node> operations = new ArrayList<>();

    public FNode(Token oper, Node lVal, Node rVal, Node action) {
        this.oper = oper;
        this.lVal = lVal;
        this.rVal = rVal;
        this.action=action;
    }
    public void addOperations(Node op){
        operations.add(op);
    }
}
