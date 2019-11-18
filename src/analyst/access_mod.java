package analyst;

public enum access_mod {
    PUBLIC("public"),
    PRIVATE("private"),
    PROTECTED("protected");
    private String modifier;
    access_mod(String modifier){
        this.modifier=modifier;
    }
    public static String getModifier(String modifier_in){
        for(access_mod mod:access_mod.values()){
            if(modifier_in.equals(mod.modifier)) return mod.modifier;
        }
        return "";
    }
}
