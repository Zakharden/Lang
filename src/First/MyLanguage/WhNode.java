package First.MyLanguage;

import java.util.ArrayList;

public class WhNode extends Node{
    Token oper;
    Node lVal;
    Node rVal;
    public ArrayList<Node> operations = new ArrayList<>();

    public WhNode(Token oper, Node lVal, Node rVal) {
        this.oper = oper;
        this.lVal = lVal;
        this.rVal = rVal;
    }
    public void addOperations(Node op){
        operations.add(op);
    }
}
