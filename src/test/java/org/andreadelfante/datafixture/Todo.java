package org.andreadelfante.datafixture;

public class Todo {
    private String text;
    private boolean isChecked;

    public Todo(String text, boolean isChecked) {
        this.text = text;
        this.isChecked = isChecked;
    }

    public String getText() {
        return text;
    }

    public boolean isChecked() {
        return isChecked;
    }
}
