package analyst;

import java.util.ArrayList;
import java.util.List;

public class ObjectConstructors {
    public String name;
    public String access_modifier;
    public List<String> param;
    ObjectConstructors(String _name,String _access_modifier){
        this.name=_name;
        this.access_modifier=_access_modifier;
        param=new ArrayList<String>();
    }
    public String to_String() {
        String s1;
        if (param.size() > 0) {
            s1 = "(" + param.get(0);
            for (int i = 1; i < param.size(); i++) {
                s1 = s1 + "," + param.get(i);
            }
            s1 += ")";
        } else s1 = "()";
        return access_modifier+" "+ name+" "+ s1;
    }

    }

