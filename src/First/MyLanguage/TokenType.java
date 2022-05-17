package First.MyLanguage;

public class TokenType {
    String typeName;
    String reg;
    public static TokenType[] tokenTypeList={
            new TokenType("NUM", "^0|[1-9][0-9]*"),

            new TokenType("SPACE", "\\ "),
            new TokenType("EndL", "[\\n]"),
            new TokenType("Karetka", "[\\r]"),

            new TokenType("EQUAL", "(?i)=="),
            new TokenType("LESS", "[<]"),
            new TokenType("MORE", "[>]"),

            new TokenType("ASSIGN", "[=]"),
            new TokenType("PLUS", "[+]"),
            new TokenType("MINUS", "[-]"),
            new TokenType("MULT", "[*]"),
            new TokenType("DIV", "[/]"),

            new TokenType("PRINT", "(?i)print"),
            new TokenType("IF","(?i)if"),
            new TokenType("ELSE","(?i)else"),
            new TokenType("FOR", "(?i)for"),
            new TokenType("WHILE","(?i)while"),

            new TokenType("END", "[;]"),
            new TokenType("LPAR", "[(]"),
            new TokenType("RPAR", "[)]"),
            new TokenType("LRectPar", "[{]"),
            new TokenType("RRectPAR", "[}]"),

            new TokenType("HASHSET", "(?i)HashSet"),
            new TokenType("LINKEDLIST", "(?i)LinkedList"),
            new TokenType("ADD","(?i)ADD"),
            new TokenType("GET","(?i)GET"),
            new TokenType("DELETE","(?i)DELETE"),
            new TokenType("CONTAINS","(?i)CONTAINS"),
            new TokenType("CLEAR","(?i)CLEAR"),

            new TokenType("VAR", "[a-z][a-z]*"),
    };
    public TokenType(String typeName, String reg) {
        this.typeName = typeName;
        this.reg = reg;
    }
}