package analyst;

import java.util.ArrayList;
import java.util.List;

public class ObjectClasses {
      public String name;
      public String othr;
      public String access_modifier;
      boolean hasParents;
      public List<String> children;
      public List<String> parent;
      public List<ObjectFields> ListFields;
      public List<String> StringFields;
      public List<ObjectMethods> ListMethods;
      public List<String> StringMethods;
      public List<ObjectConstructors> ListConstructors;
      public List<String> StringConstructors;

      public ObjectClasses (String _name,String _access_modifier,String _othr){
            name=_name;
            access_modifier=_access_modifier;
            othr=_othr;
            hasParents=false;
            parent=new ArrayList<>();
            children=new ArrayList<>();
            ListFields=new ArrayList<>();
            ListMethods=new ArrayList<>();
            ListConstructors=new ArrayList<>();
            StringConstructors=new ArrayList<>();
            StringFields=new ArrayList<>();
            StringMethods=new ArrayList<>();

      }
      public String to_String(){
            String symbol=othrs.get_symbol_Other(othr);

//            for(ObjectFields f:ListFields){
//               StringFields.add(f.to_String());
//            }
//            for(ObjectMethods m:ListMethods){
//                  StringMethods.add(m.to_String());
//            }
//            for(ObjectConstructors c:ListConstructors){
//                  StringConstructors.add(c.to_String());
//            }
            return name+" "+symbol;
      }
      public void SetStringForProperties(){
            for(ObjectFields f:ListFields){
                  StringFields.add(f.to_String());
            }
            for(ObjectMethods m:ListMethods){
                  StringMethods.add(m.to_String());
            }
            for(ObjectConstructors c:ListConstructors){
                  StringConstructors.add(c.to_String());
            }
      }
      public void show(){

      }
}
