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
        new getClassContent("");
    }
    public static void main(String[] args){
        new Main();
    }

}
