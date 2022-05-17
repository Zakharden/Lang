package First.MyLanguage;

import java.util.ArrayList;

public class Parser {
    ArrayList<Token> tokens;
    int pos=0;

    public Parser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }
    public Token receive(String[] need){
        Token curToken;
        if (pos<tokens.size()) {
            curToken = tokens.get(pos);
            for (String tokenTypeName : need)
                if (tokenTypeName.equals(curToken.type.typeName)) {
                    pos++;
                    return curToken;
                }
        }
        return null;
    }
    public void need(String[] expected){
        Token token= receive(expected);
        if(token==null){
            throw new Error("На месте "+pos+" предполагалось"+expected[0]);
        }
    }
    public Node parseVarNum(){
        if (tokens.get(pos).type.typeName.equals("NUM")){
            pos++;
            return new NumNode(tokens.get(pos-1));
        }
        if (tokens.get(pos).type.typeName.equals("VAR")){
            pos++;
            return new VarNode(tokens.get(pos-1));
        }
        throw new Error("Предполагается переменная или число на месте: "+pos);
    }
    public Node parsePar(){
        if (tokens.get(pos).type.typeName.equals("LPAR")){
            pos++;
            Node node = parseFormula();
            need(new String[]{"RPAR"});
            return node;
        }
        else
            return parseVarNum();
    }
    public Node parseMultDiv(){
        Node leftVal= parsePar();
        Token operator= receive(new String[]{"MULT","DIV"});
        while (operator!=null){
            Node rightVal= parsePar();
            leftVal=new Musor(operator,leftVal,rightVal);
            operator= receive(new String[]{"MULT","DIV"});
        }
        return leftVal;
    }
    public Node parseFormula(){
        Node leftVal= parseMultDiv();
        Token operator= receive(new String[]{"PLUS","MINUS"});
        while (operator!=null){
            Node rightVal= parseMultDiv();
            leftVal=new Musor(operator,leftVal,rightVal);
            operator= receive(new String[]{"PLUS","MINUS"});
        }
        return leftVal;
    }
    public Node parseString(){
        switch (tokens.get(pos).type.typeName) {
            case "VAR" -> {
                Node varNode = parseVarNum();
                Token operator = receive(new String[]{"ASSIGN"});
                if (operator != null) {
                    Node rightVal = parseFormula();
                    return new Musor(operator, varNode, rightVal);
                }
                throw new Error("После переменной предполагается бинарный оператор на месте:" + pos);
            }
            case "PRINT" -> {
                pos++;
                return new UnOpNode(tokens.get(pos - 1), this.parseFormula());
            }
            case "IF" -> {
                pos++;
                return parseIf();
            }
            case "WHILE" -> {
                pos++;
                return parseWhile();
            }
            case "FOR" -> {
                pos++;
                return parseFor();
            }
        }
        throw new Error("Ошибка на месте: "+pos+". Предполагалось действие или переменная");
    }
    public Node parseIf(){
        Node leftVal=parseFormula();
        Token operator=receive(new String[]{"LESS","MORE","EQUAL"});
        Node rightVal=parseFormula();
        INode iNode =new INode(operator,leftVal,rightVal);
        need(new String[]{"LRectPar"});
        while(!tokens.get(pos).type.typeName.equals("RRectPAR")) {
            iNode.addThenOperations(getOperations());
            if (pos==tokens.size())
                throw new Error("Ошибка, ожидалось }");
        }
        pos++;
        need(new String[]{"ELSE"});
        need(new String[]{"LRectPar"});
        while(!tokens.get(pos).type.typeName.equals("RRectPAR")) {
            iNode.addElseOperations(getOperations());
            if (pos==tokens.size())
                throw new Error("Ошибка, предполагалось }");
        }
        pos++;
        return iNode;
    }
    public Node parseFor(){
        Node leftVal=parseFormula();
        Token operator=receive(new String[]{"LESS","MORE","EQUAL"});
        Node rightVal=parseFormula();

        need(new String[]{"END"});

        Node varNode = parseVarNum();
        Token assign = receive(new String[]{"ASSIGN"});
        Node rightActVal = parseFormula();
        Musor action= new Musor(assign, varNode, rightActVal);
        if (assign == null)
            throw new Error("После переменной предполагалось = на месте:"+pos);
        FNode fNode = new FNode(operator,leftVal,rightVal,action);
        need(new String[]{"LRectPar"});
        while(!tokens.get(pos).type.typeName.equals("RRectPAR")) {
            fNode.addOperations(getOperations());
            if (pos==tokens.size())
                throw new Error("Ошибка, предполагалось }");
        }
        pos++;
        return fNode;
    }
    public Node parseWhile(){
        Node leftVal=parseFormula();
        Token operator=receive(new String[]{"LESS","MORE","EQUAL"});
        Node rightVal=parseFormula();
        WhNode whileNode=new WhNode(operator,leftVal,rightVal);
        need(new String[]{"LRectPar"});
        while(!tokens.get(pos).type.typeName.equals("RRectPAR")) {
            whileNode.addOperations(getOperations());
            if (pos==tokens.size())
                throw new Error("Ошибка, предполагалось }");
        }
        pos++;
        return whileNode;
    }
    public Node getOperations(){
        Node codeStringNode=parseString();
        need(new String[]{"END"});
        return codeStringNode;
    }
    public RootNode parseTokens(){
        RootNode root=new RootNode();
        while (pos<tokens.size()){
            Node codeStringNode= parseString();
            need(new String[]{"END"});
            root.addNode(codeStringNode);
        }
        return root;
    }
}
