package gsmapp;

public enum option {
    Client , Manger;

    private option(){}

    public String value(){
        return name();
    }

    public static option fromvalue(String v){
        return valueOf(v);
    }

}
