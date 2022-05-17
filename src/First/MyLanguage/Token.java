package First.MyLanguage;

public class Token {
    TokenType type;
    String value;
    int pos;

    public Token(TokenType type, String value, int pos) {
        this.type = type;
        this.value = value;
        this.pos = pos;
    }
}
