package analyst;

import java.util.ArrayList;
import java.util.List;

public class ObjectClasses {
      public String name;
      public String type;
      public String access_modifier;
      boolean hasParents;
      public List<String> children;
      public List<String> ListFields;
      public List<String> ListMethods;
      public List<String> ListConstructors;
      public ObjectClasses(){
            name="";
            type="";
            access_modifier="";
            hasParents=false;
            children=new ArrayList<>();
            ListFields=new ArrayList<>();
            ListMethods=new ArrayList<>();
            ListConstructors=new ArrayList<>();

      }
      public void show(){

      }



}
