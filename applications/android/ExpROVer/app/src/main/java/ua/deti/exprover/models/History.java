package ua.deti.exprover.models;

import java.util.Calendar;
import java.util.Locale;

import static android.icu.util.Calendar.MONTH;

public class History {
    private String title, location;
    private int bookmarks, hours, minutes, seconds;
    private Calendar date;

    public History(String title, Calendar date, int duration, int bookmarks, String location) {
        this.title = title;
        this.date = date;

        hours = duration / 3600;
        minutes = (duration % 3600) / 60;
        seconds = duration % 60;

        this.bookmarks = bookmarks;
        this.location = location;

    }

    public History(String title, Calendar date, int duration, int bookmarks) {
        this(title, date, duration, bookmarks, "No location");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(int bookmarks) {
        this.bookmarks = bookmarks;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public String getDate() {
        String s = String.format("%s %s %s",
                date.get(Calendar.DAY_OF_MONTH),
                date.getDisplayName(MONTH, Calendar.SHORT, Locale.getDefault()),
                date.get(Calendar.YEAR)
        );
        return s;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getDurationString() {
        return String.format("%02d:%02d:%02d", getHours(), getMinutes(), getSeconds());
    }
}
