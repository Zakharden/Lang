package First.MyLanguage;

import java.util.ArrayList;

public class RootNode extends Node {
    ArrayList<Node> codeStr=new ArrayList<>();
    public void addNode(Node node){
        codeStr.add(node);
    }
}
