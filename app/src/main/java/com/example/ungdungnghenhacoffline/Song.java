package com.example.ungdungnghenhacoffline;

public class Song {
    private String tenBaiHat;
    private int File;
    private String tenCaSy;

    public Song(String tenBaiHat, int file, String tenCaSy) {
        this.tenBaiHat = tenBaiHat;
        File = file;
        this.tenCaSy = tenCaSy;
    }

    public String getTenBaiHat() {
        return tenBaiHat;
    }

    public void setTenBaiHat(String tenBaiHat) {
        this.tenBaiHat = tenBaiHat;
    }

    public int getFile() {
        return File;
    }

    public void setFile(int file) {
        File = file;
    }

    public String getTenCaSy() {
        return tenCaSy;
    }

    public void setTenCaSy(String tenCaSy) {
        this.tenCaSy = tenCaSy;
    }
}
