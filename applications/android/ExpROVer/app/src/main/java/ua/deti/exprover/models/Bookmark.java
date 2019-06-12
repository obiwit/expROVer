package ua.deti.exprover.models;

public class Bookmark {
    private int time, hours, minutes, seconds;   // in seconds
    private String title;


    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bookmark(int time, String title) {
        this.time = time;
        this.title = title;

        hours = time / 3600;
        minutes = (time % 3600) / 60;
        seconds = time % 60;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public String getDurationString() {
        return String.format("%02d:%02d:%02d", getHours(), getMinutes(), getSeconds());
    }

}
