package org.example;

public class Designer extends Person{
    private String style;
    public Designer(String name, String birthDate, String style){
        super(name, birthDate);
        this.style=style;
    }

    public String getStyle() {
        return style;
    }
}
