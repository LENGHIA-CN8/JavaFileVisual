package analyst;

public enum othrs {
    STATIC("static",""),
    INTERFACE("interface","< interface >"),
    ABSTRACT("abstract","< abstract >"),
    INTERN("intern",""),
    FINAL("final","");
    private String other;
    private String symbol;
    othrs(String other,String symbol)
    {
        this.symbol=symbol;
        this.other=other;
    };
    public static String get_Other(String other_in){
        for(othrs oth:othrs.values()){
            if(other_in.equals(oth.other)) return oth.other;
        }
        return "";
    }
    public static String get_symbol_Other(String other_in){
        for(othrs oth:othrs.values()){
            if(other_in.equals(oth.other)) return oth.symbol;
        }
        return "";
    }
}
