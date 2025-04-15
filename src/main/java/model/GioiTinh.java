package model;

public enum GioiTinh {
    Nam("Nam"),
    Nu("Nữ"),
    Khac("Khác");

    private String tenGioiTinh;

     private GioiTinh(String tenGioiTinh) {
        this.tenGioiTinh = tenGioiTinh;
    }

    public String getTenGioiTinh() {
        return tenGioiTinh;
    }

    public void setTenGioiTinh(String tenGioiTinh) {
        this.tenGioiTinh = tenGioiTinh;
    }
}
