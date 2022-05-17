package First.MyLanguage;

import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String s=checkFile();
        System.out.println(s);
        Lexer lexer=new Lexer(s);
        Parser parser=new Parser(lexer.analyze());
        RootNode root=parser.parseTokens();
        Interpretator interpretator =new Interpretator();
        for(int i = 0; i<root.codeStr.size(); i++) {
            interpretator.run(root.codeStr.get(i));
        }
    }

    public static String checkFile(){
        String code="";
        try(FileReader reader = new FileReader("ThisCode.txt"))
        {
            int c;
            while((c=reader.read())!=-1){
                code=code.concat(String.valueOf((char)c));
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return code;
    }
}
