package First.MyLanguage;

public class InitNode extends Node {
    Token type;
    Node var;

    public InitNode(Token type, Node var) {
        this.type = type;
        this.var = var;
    }
}
