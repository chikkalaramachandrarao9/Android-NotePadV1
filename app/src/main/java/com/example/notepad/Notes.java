package com.example.notepad;

public class Notes {
    private String title,note;

    Notes(String title,String note)
    {
        this.title = title;
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
