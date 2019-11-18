package analyst;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Parser extends getClassContent {
    List<String> ObjectCurrent=new Stack<>();
    Stack<String> AccessModCurrent=new Stack<>();
    Stack<String> typeCurrent=new Stack<>();
    Stack<String> othersCurrent=new Stack<>();
    boolean InsideClass;
    Stack<String> ObjectCurrentName=new Stack<>();
    //File[] files;
    //getClassContent get;
    ObjectClasses ob;
    List<ObjectClasses> listofObjectClasses=new ArrayList<>();
    List<String> content=new ArrayList<>();
    Stack<Integer> cnt= new Stack<>();

    public Parser(String path) throws IOException {
         super(path);
    }

    public void handle(){
        for(File file:listOfFiles ){
            try {
                content = readContentFromFile(file);
            } catch(IOException e) {
                e.getMessage();
            }
            for(int i=0;i<content.size();i++){
                if(content.get(i).equals("class")){
                     handle_class(i,content);
//                     System.out.println(ob.to_String());
                }
            }

            System.out.println(content);
        }

    }
    public void handle_class(int i,List<String> content){
        ObjectCurrent.add("class");
        //take modifier and others
        for(int j=i;j>=0;j--){
           String s=access_mod.getModifier(content.get(j));
           if(s!=""){
               AccessModCurrent.add(s);
           }
           s=othrs.get_Other(content.get(j));
           if(s!="") {
               othersCurrent.add(content.get(j));
           }
           if(othersCurrent.size()==AccessModCurrent.size() && othersCurrent.size()>0 && AccessModCurrent.size()>0) break;
        }

        if(AccessModCurrent.size()>othersCurrent.size()) othersCurrent.add("");
        else AccessModCurrent.add("");
        //take name classes
        ObjectCurrentName.add(content.get(i+1));
        //create a new Objectclasses
        ob=new ObjectClasses(ObjectCurrentName.peek(),AccessModCurrent.peek(),othersCurrent.peek());
        //check has Parents
        while(!content.get(i).equals("{")){
            if(content.get(i).equals("implements")||content.get(i).equals("extends")){
                ob.hasParents=true;
                break;
            }
        }
        System.out.println(ob.to_String());
        //return ob;






    }

}
