package analyst;

public enum access_mod {
    PUBLIC("public","+"),
    PRIVATE("private","-"),
    PROTECTED("protected","#");
    private String modifier;
    public String symbol;
    access_mod(String modifier,String symbol){
        this.symbol=symbol;
        this.modifier=modifier;
    }
    public static String getModifier(String modifier_in){
        for(access_mod mod:access_mod.values()){
            if(modifier_in.equals(mod.modifier)) return mod.modifier;
        }
        return "";
    }
    public static String getSymbol(String modifier_in){
        for(access_mod mod:access_mod.values()){
            if(modifier_in.equals(mod.modifier)) return mod.symbol;
        }
        return "";
    }
}
