package analyst;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Parser extends getClassContent {
    Stack<String> ObjectCurrent=new Stack<>();
    Stack<String> AccessModCurrent=new Stack<>();
    Stack<String> typeCurrent=new Stack<>();
    Stack<String> othersCurrent=new Stack<>();
    Stack<String> ObjectCurrentName=new Stack<>();
    ObjectClasses ob;
    ObjectConstructors obcon;
    ObjectFields obfield;
    ObjectMethods obmethod;
    public boolean  InsideClass;

    List<ObjectClasses> listofObjectClasses=new ArrayList<>();
    List<String> content=new ArrayList<>();
    int cnt=0;

    public Parser(String path) throws IOException {
         super(path);
    }

    public void handle() throws NullPointerException{
        for(File file:listOfFiles ){
            try {
                content = readContentFromFile(file);
            } catch(IOException e) {
                e.getMessage();
            }
            System.out.println(content);
            for(int i=0;i<content.size();i++){
                if(content.get(i).equals("class")||content.get(i).equals("interface")) {

                    handle_class(i);
                    while(!content.get(i).equals("{")) ++i;
                    i+=2;                  //pass through "{" of outer class
                    System.out.println(ob.to_String());
                } else if ( ObjectCurrentName.size()>0){                 //check inside a class
                    if(content.get(i).equals(ObjectCurrentName.get(0))&&!content.get(i-1).equals("(")&&!content.get(i-1).equals(",")) {
                        handle_constructor(i);//handle constructor
                        System.out.println("isconstructor");
                    }
                    else if(content.get(i).equals("{")) {
                        ++i;      //pass through "{" of constructor or method
                        cnt+=1;
                        while (cnt!=0) {                      //discard content in method or constructor
                            if (content.get(i).equals("{")) {
                                cnt+=1;
                                i++;
                            } else if(content.get(i).equals("}")){
                                cnt-=1;
                                i++;
                            } else i++;
                        }
                    }
                    else if(content.get(i).equals("(") && !content.get(i-1).equals(ObjectCurrentName.get(0))){

                        handle_method(i);        //handle method
                        if(obmethod.othr.equals("abstract")) {
                            while(!content.get(i).equals(";")){
                                i++;
                            }
                            i++;
                        }
                        System.out.println("ismethod");
                    } else if(content.get(i).equals(";")) {
                        handle_fields(i);         //handle fields
                        System.out.println("isfield");
                    }
                }
            }
            ob.SetStringForProperties();    //set List StringFields,method

            System.out.println(ob.parent);
            listofObjectClasses.add(ob);
            renew();
            }
        System.out.println(Arrays.deepToString(listOfFiles));

    }
    public void renew(){
        ObjectCurrent.clear();
        AccessModCurrent.clear();          //Update a new ObjectClass for each file
        typeCurrent.clear();
        othersCurrent.clear();
        ObjectCurrentName.clear();
        content.clear();
        cnt=0;
    }
    public void handle_class(int i){
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
        else if(othersCurrent.size()>AccessModCurrent.size()) AccessModCurrent.add("");
        //take name classes
        ObjectCurrentName.add(content.get(i+1));
        //create a new Objectclasses
        ob=new ObjectClasses(ObjectCurrentName.peek(),AccessModCurrent.peek(),othersCurrent.peek());
        //check has Parents
        while(!content.get(i).equals("{")){
            if(content.get(i).equals("implements")||content.get(i).equals("extends")){
                ob.hasParents=true;

                //break;
            }
           if(content.get(i).equals("extends")) ob.parent.add(content.get(i+1));
            i++;
        }
    }
    public void handle_constructor(int i){
        ObjectCurrent.add("constructor");
        ObjectCurrentName.add(content.get(i));
        String s=access_mod.getModifier(content.get(i-1));
        AccessModCurrent.add(s);
        othersCurrent.add("");
        obcon=new ObjectConstructors(ObjectCurrentName.peek(),AccessModCurrent.peek());
        while(!content.get(i).equals(")")){
            if(content.get(i).equals("(")||content.get(i).equals(",")) {
                while(content.get(i+1).equals("")) //obcon.param.add(content.get(i+2));
                    i++;
                if(!content.get(i+1).equals(")"))
                 obcon.param.add(content.get(i + 1));
            }
            i++;
        }
        System.out.println(obcon.to_String());
        ob.ListConstructors.add(obcon);

    }
    public void handle_fields(int i) {
        i--;            //pass through ";"
        ObjectCurrent.add("field");
        List<String> substr=new ArrayList<>();
        while(!content.get(i).equals("{")&&!content.get(i).equals("}")&&!content.get(i).equals(";")){
            substr.add(content.get(i));
            i--;
        }
        if(substr.contains("=")){
            i=substr.indexOf("=");
            ObjectCurrentName.add(substr.get(i+1));
            typeCurrent.add(substr.get(i+2));
            for(int j=i+3;j<substr.size();j++) {
                String checkother = othrs.get_Other(substr.get(j));
                String checkaccess= access_mod.getModifier(substr.get(j));
                if(!checkother.equals("")) othersCurrent.add(checkother);
                if(!checkaccess.equals("")) AccessModCurrent.add(checkaccess);
           }
            if(othersCurrent.size()>AccessModCurrent.size()) AccessModCurrent.add("");  // static int a;
            else if(othersCurrent.size()<AccessModCurrent.size()) othersCurrent.add("");     // public int a;
            obfield= new ObjectFields(ObjectCurrentName.pop(),typeCurrent.pop(),AccessModCurrent.pop());
            //System.out.println("hello");
        } else {
            ObjectCurrentName.add(substr.get(0));
            typeCurrent.add(substr.get(1));
            for(int j=2;j<substr.size();j++){
                String checkother = othrs.get_Other(substr.get(j));
                String checkaccess= access_mod.getModifier(substr.get(j));
                if(!checkother.equals("")) othersCurrent.add(checkother);
                if(!checkaccess.equals("")) AccessModCurrent.add(checkaccess);
            }
            if(othersCurrent.size()>AccessModCurrent.size()) AccessModCurrent.add("");  // static int a;
            else if(othersCurrent.size()<AccessModCurrent.size()) othersCurrent.add("");     // public int a;
            obfield= new ObjectFields(ObjectCurrentName.pop(),typeCurrent.pop(),AccessModCurrent.pop());
        }
        ob.ListFields.add(obfield);
        System.out.println(obfield.to_String());
   }
   public void handle_method(int i) {
        ObjectCurrent.add("method");
        ObjectCurrentName.add(content.get(i-1));
        typeCurrent.add(content.get(i-2));
       for(int j=i-3;j>=i-6;j--){
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
       else if(othersCurrent.size()>AccessModCurrent.size()) AccessModCurrent.add("");
       obmethod= new ObjectMethods(ObjectCurrentName.pop(),typeCurrent.pop(),AccessModCurrent.pop(),othersCurrent.pop());
       while(!content.get(i).equals(")")){
           if(content.get(i).equals("(")||content.get(i).equals(","))
               obmethod.param.add(content.get(i+1));
           i++;
       }
       ob.ListMethods.add(obmethod);
       System.out.println(obmethod.to_String());




   }

}
