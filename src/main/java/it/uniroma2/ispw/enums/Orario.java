package it.uniroma2.ispw.enums;

public enum Orario {
    FASCIAUNO(1),
    FASCIADUE(2),

    FASCIATRE(3),
    FASCIAQUATTRO(4);





    private final int id;

    private Orario( int id) {
        this.id = id;
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
}
