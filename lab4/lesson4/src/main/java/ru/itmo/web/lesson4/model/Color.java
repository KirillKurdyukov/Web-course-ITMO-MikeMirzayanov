package ru.itmo.web.lesson4.model;

public enum Color {
    RED("user-color-red"),
    GREEN("user-color-green"),
    BLUE("user-color-blue");

    private final String cssClass;

    Color(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getCssClass() {
        return cssClass;
    }
}
