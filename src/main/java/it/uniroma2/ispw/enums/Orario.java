package it.uniroma2.ispw.enums;

public enum Orario {
    FASCIAUNO("12:00-14:00"),
    FASCIADUE("14:00-16:00" ),

    FASCIATRE("16:00-18:00" ),
    FASCIAQUATTRO( "18:00-20:00");


    private final String fasciaOraria;
    private Orario(String string) {
        this.fasciaOraria = string;
    }

    public static Orario fromString (String fasciaOraria) {
        for (Orario type : values()) {
            if (type.getFasciaOraria().equals(fasciaOraria)) {
                return type;
            }
        }
        return null;
    }

    public String getFasciaOraria() {
        return fasciaOraria;
    }
}
