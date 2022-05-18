package First.MyLanguage;

import java.util.ArrayList;

public class INode extends Node {
    Token oper;
    Node lVal;
    Node rVal;
    public ArrayList<Node> thenOperations = new ArrayList<>();
    public ArrayList<Node> elseOperations = new ArrayList<>();
    public INode(Token oper, Node lVal, Node rVal) {
        this.oper = oper;
        this.lVal = lVal;
        this.rVal = rVal;
    }
    public void addThenOperations(Node op){
        thenOperations.add(op);
    }
    public void addElseOperations(Node op){
        elseOperations.add(op);
    }
}
