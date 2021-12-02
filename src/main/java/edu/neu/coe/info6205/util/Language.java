package edu.neu.coe.info6205.util;

public enum Language {
    ENGLISH("en"), CHINESE("ch");

    Language(String language)
    {
        this.language=language;
    }

    public String getLanguage() {
        return language;
    }
    String language;
}

