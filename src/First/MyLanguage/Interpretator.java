package First.MyLanguage;

import java.util.HashMap;

public class Interpretator {
    HashMap<String,String> scope=new HashMap<>();

    public String run(Node node){
        if (node.getClass()== INode.class){
            int left=Integer.parseInt(this.run(((INode) node).lVal));
            int right=Integer.parseInt(this.run(((INode) node).rVal));
            switch (((INode) node).oper.type.typeName) {
                case "LESS":
                    if (left < right) {
                        for (int i = 0; i < ((INode) node).thenOperations.size(); i++)
                            this.run(((INode) node).thenOperations.get(i));
                    }
                    else{
                        for (int i = 0; i < ((INode) node).elseOperations.size(); i++)
                            this.run(((INode) node).elseOperations.get(i));
                    }
                    break;
                case "MORE":
                    if (left > right) {
                        for (int i = 0; i < ((INode) node).thenOperations.size(); i++)
                            this.run(((INode) node).thenOperations.get(i));
                    }
                    else{
                        for (int i = 0; i < ((INode) node).elseOperations.size(); i++)
                            this.run(((INode) node).elseOperations.get(i));
                    }
                    break;
                case "EQUAL":
                    if (left == right) {
                        for (int i = 0; i < ((INode) node).thenOperations.size(); i++)
                            this.run(((INode) node).thenOperations.get(i));
                    }
                    else{
                        for (int i = 0; i < ((INode) node).elseOperations.size(); i++)
                            this.run(((INode) node).elseOperations.get(i));
                    }
                    break;
            }
        }
        if (node.getClass()==UnOpNode.class) {
            if (((UnOpNode) node).oper.type.typeName.equals("PRINT")) {
                System.out.println(this.run(((UnOpNode) node).value));
            }
        }
        if (node.getClass()== Musor.class) {
            if (((Musor) node).oper.type.typeName.equals("ASSIGN"))
            {
                String res = this.run(((Musor) node).rVal);
                VarNode varNode = (VarNode) (((Musor) node).lVal);
                this.scope.put(varNode.var.value, res);
                return res;
            }
            else
            {
                int left=Integer.parseInt(this.run(((Musor) node).lVal));
                int right=Integer.parseInt(this.run(((Musor) node).rVal));
                switch (((Musor) node).oper.type.typeName){
                    case "PLUS":
                        return String.valueOf(left+right);
                    case "MINUS":
                        return String.valueOf(left-right);
                    case "MULT":
                        return String.valueOf(left*right);
                    case "DIV":
                        return String.valueOf(left/right);
                    case "ASSIGN":
                }
            }
        }
        if (node.getClass()==VarNode.class) {
            return scope.get(((VarNode) node).var.value);
        }
        if (node.getClass()== NumNode.class) {
            return ((NumNode) node).number.value;
        }
        if (node.getClass()== WhNode.class){
            int left=Integer.parseInt(this.run(((WhNode) node).lVal));
            int right=Integer.parseInt(this.run(((WhNode) node).rVal));
            switch (((WhNode) node).oper.type.typeName) {
                case "LESS":
                    while (left < right) {
                        for (int i = 0; i < ((WhNode) node).operations.size(); i++)
                            this.run(((WhNode) node).operations.get(i));
                        left = Integer.parseInt(this.run(((WhNode) node).lVal));
                        right = Integer.parseInt(this.run(((WhNode) node).rVal));
                    }
                    break;
                case "MORE":
                    while (left > right) {
                        for (int i = 0; i < ((WhNode) node).operations.size(); i++)
                            this.run(((WhNode) node).operations.get(i));
                        left = Integer.parseInt(this.run(((WhNode) node).lVal));
                        right = Integer.parseInt(this.run(((WhNode) node).rVal));
                    }
                    break;
                case "EQUAL":
                    while (left == right) {
                        for (int i = 0; i < ((WhNode) node).operations.size(); i++)
                            this.run(((WhNode) node).operations.get(i));
                        left = Integer.parseInt(this.run(((WhNode) node).lVal));
                        right = Integer.parseInt(this.run(((WhNode) node).rVal));
                    }
                    break;
            }
        }
        if (node.getClass()== FNode.class){
            int left=Integer.parseInt(this.run(((FNode) node).lVal));
            int right=Integer.parseInt(this.run(((FNode) node).rVal));
            switch (((FNode) node).oper.type.typeName) {
                case "LESS":
                    while (left < right) {
                        for (int i = 0; i < ((FNode) node).operations.size(); i++)
                            this.run(((FNode) node).operations.get(i));
                        this.run(((FNode) node).action);
                        left = Integer.parseInt(this.run(((FNode) node).lVal));
                        right = Integer.parseInt(this.run(((FNode) node).rVal));
                    }
                    break;
                case "MORE":
                    while (left > right) {
                        for (int i = 0; i < ((FNode) node).operations.size(); i++)
                            this.run(((FNode) node).operations.get(i));
                        this.run(((FNode) node).action);
                        left = Integer.parseInt(this.run(((FNode) node).lVal));
                        right = Integer.parseInt(this.run(((FNode) node).rVal));
                    }
                    break;
                case "EQUAL":
                    while (left == right) {
                        for (int i = 0; i < ((FNode) node).operations.size(); i++)
                            this.run(((FNode) node).operations.get(i));
                        this.run(((FNode) node).action);
                        left = Integer.parseInt(this.run(((FNode) node).lVal));
                        right = Integer.parseInt(this.run(((FNode) node).rVal));
                    }
                    break;
            }
        }
        return "";
    }
}