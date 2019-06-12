package ua.deti.exprover.models;

import java.io.File;

public class ROV {
    private String ip, name;
    private int imgRes;
    private File imgFile;

    public ROV(String ip, String name, File imgFile) {
        this.ip = ip;
        this.name = name;
        this.imgRes = -1;
        this.imgFile = imgFile;
    }
    public ROV(String ip, String name, int imgRes) {
        this.ip = ip;
        this.name = name;
        this.imgRes = imgRes;
        this.imgFile = null;
    }
    public ROV(String name, int imgRes) {
        this("", name, imgRes);
    }

    public String getIP() {
        return this.ip;
    }
    public String getName() {
        return this.name;
    }
    public int getImgRes() {
        return this.imgRes;
    }
    public File getImgFile() {
        return this.imgFile;
    }

    public void setIP(String ip) {
        this.ip = ip;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }
    public void setImgFile() {
        this.imgFile = imgFile;
    }

    public String toString() {
        return getName() + ", " + getIP() + " - " + ((getImgRes() != -1) ? getImgRes() : getImgFile().getPath());
    }
}
