package it.uniroma2.ispw.enums;

public enum Orario {
    FASCIAUNO(1,"12:00-14:00"),
    FASCIADUE(2,"14:00-16:00" ),

    FASCIATRE(3,"16:00-18:00" ),
    FASCIAQUATTRO(4, "18:00-20:00");





    private final int id;
    private final String fasciaOraria;
    private Orario(int id, String string) {
        this.id = id;
        this.fasciaOraria = string;
    }

    public static Orario fromInt(int id) {
        for (Orario type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getFasciaOraria() {
        return fasciaOraria;
    }
}
