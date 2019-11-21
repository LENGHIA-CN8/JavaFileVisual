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
    public ObjectMethods(String _name,String _type,String _access_modifier){
        name=_name;
        type=_type;
        access_modifier=_access_modifier;
        param=new ArrayList<>();
    }
    public String to_String(){
        String s1;
        if (param.size() > 0) {
            s1 = "(" + param.get(0);
            for (int i = 1; i < param.size(); i++) {
                s1 = s1 + "," + param.get(i);
            }
            s1 += ")";
        } else s1 = "()";
        return access_modifier+" "+type+" "+name+" "+s1;
    }
}
