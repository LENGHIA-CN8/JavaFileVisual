package analyst;

import java.util.ArrayList;
import java.util.List;

public class ObjectMethods {
    public String name;
    public String type;
    public String access_modifier;
    public String othr;
    List<String> param;
    public void show(){

    }
    public void show_name(){

    }
    public void show_type(){

    }
    public void show_modifier(){

    }
    public ObjectMethods(){

    }
    public ObjectMethods(String _name,String _type,String _access_modifier,String _othr){
        name=_name;
        type=_type;
        access_modifier=_access_modifier;
        othr=_othr;
        param=new ArrayList<>();
    }
    public String to_String(){
        String s1;
        String symbol=access_mod.getSymbol(access_modifier);
        if (param.size() > 0) {
            s1 = "(" + param.get(0);
            for (int i = 1; i < param.size(); i++) {
                s1 = s1 + "," + param.get(i);
            }
            s1 += ")";
        } else s1 = "()";
        return symbol+" "+othr+" "+name+" "+s1+":"+type;
    }
}
