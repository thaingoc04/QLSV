package com.example.nguyenthingu;

import java.io.Serializable;

public class ThiSinh implements Serializable {
    private String SBD;
    private String hoTen;
    private float Toan, Ly, Hoa;

    public ThiSinh()  {
    }

    public ThiSinh(String SBD, String hoTen, float toan, float ly, float hoa) {
        this.SBD = SBD;
        this.hoTen = hoTen;
        Toan = toan;
        Ly = ly;
        Hoa = hoa;
    }

    public String getSBD() {
        return SBD;
    }

    public void setSBD(String SBD) {
        this.SBD = SBD;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public float getToan() {
        return Toan;
    }

    public void setToan(float toan) {
        Toan = toan;
    }

    public float getLy() {
        return Ly;
    }

    public void setLy(float ly) {
        Ly = ly;
    }

    public float getHoa() {
        return Hoa;
    }

    public void setHoa(float hoa) {
        Hoa = hoa;
    }

    public float diemTB(){
        return (float) (Toan + Ly + Hoa);
    }
}
