package analyst;

import java.io.IOException;

public class Main {
    public Main() {
        try {
            initGetClassInfo();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    private void initGetClassInfo() throws IOException {
//        new getClassContent("");
        Parser parser=new Parser("");
        parser.handle();
    }
    public static void main(String[] args){
        Main m=new Main();
    }

}
