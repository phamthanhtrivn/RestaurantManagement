package model;


public enum GioiTinh {
    Nam("Nam"),Nu("Nu"),Khac("Khac");

    private String name;

    private GioiTinh(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
