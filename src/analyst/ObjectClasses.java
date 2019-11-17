package analyst;

import java.util.List;

public class ObjectClasses {
      public String name;
      public Parser.types type;
      public Parser.accessModifiers access_modifier;
      boolean hasParents;
      public List<ObjectClasses> children;
      public List<?> ListFields;
      public List<?> ListMethods;
      public ObjectClasses(){
            name="";
            hasParents=false;

      }
      public void show(){

      }



}
