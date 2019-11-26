package analyst;

public class ObjectFields {
    public String name;
    public String type;
    public String access_modifier;
    public void show(){

    }
    public void show_name(){

    }
    public void show_type(){

    }
    public void show_modifier(){

    }
    public ObjectFields(String _name,String _type,String _access_modifier){
        name=_name;
        type=_type;
        access_modifier=_access_modifier;
    }
    public String to_String(){
        String symbol=access_mod.getSymbol(access_modifier);
        return symbol+" "+name+":"+type;
    }

}
