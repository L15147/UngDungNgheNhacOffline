package vn.poly.ungdungnghenhacoffline;

import android.media.MediaPlayer;

public class Song {
    private  int imgSong;
    private String tenBaiHat;
    private int File;
    private String tenCaSy;
    public static MediaPlayer mediaPlayer;
    public Song(int imgSong, String tenBaiHat, int file, String tenCaSy) {
        this.imgSong = imgSong;
        this.tenBaiHat = tenBaiHat;
        File = file;
        this.tenCaSy = tenCaSy;
    }

    public int getImgSong() {
        return imgSong;
    }

    public void setImgSong(int imgSong) {
        this.imgSong = imgSong;
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
