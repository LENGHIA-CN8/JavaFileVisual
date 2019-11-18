package analyst;

import java.util.ArrayList;
import java.util.List;

public class ObjectClasses {
      public String name;
      public String othr;
      public String access_modifier;
      boolean hasParents;
      public List<String> children;
      public List<ObjectFields> ListFields;
      public List<ObjectMethods> ListMethods;
      public List<ObjectConstructors> ListConstructors;
      public ObjectClasses (String _name,String _access_modifier,String _othr){
            name=_name;
            access_modifier=_access_modifier;
            othr=_othr;
            hasParents=false;
            children=new ArrayList<>();
            ListFields=new ArrayList<>();
            ListMethods=new ArrayList<>();
            ListConstructors=new ArrayList<>();

      }
      public String to_String(){
            return access_modifier+" "+othr+" "+name;
      }
      public void show(){

      }



}
