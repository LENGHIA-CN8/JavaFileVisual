package analyst;

public enum types {
    STRING("String"),
    VOID("void"),
    INT("int"),
    DOUBLE("double");
    private String type;
    types(String type){
        this.type=type;
    }
    public static String  get_type(String type_in){
        for(types t:types.values()){
            if(t.type.equals(type_in)) return t.type;
        }
        return "";
    }

}
