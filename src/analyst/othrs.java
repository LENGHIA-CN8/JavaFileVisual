package analyst;

public enum othrs {
    STATIC("static"),
    INTERFACE("interface"),
    ABSTRACT("abstract"),
    INTERN("intern"),
    FINAL("final");
    private String other;
    othrs(String other){
        this.other=other;
    };
    public static String get_Other(String other_in){
        for(othrs oth:othrs.values()){
            if(other_in.equals(oth.other)) return oth.other;
        }
        return "";
    }
}
