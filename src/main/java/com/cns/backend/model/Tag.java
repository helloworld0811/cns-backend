package com.cns.backend.model;

import java.util.List;

/**
 * Created by hku on 03.04.17.
 */
public class Tag {

    private static final long serialVersionUID = 3214332122563L;

  private String language;

  private String text;

  private List<String> sysnonyms;


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getSysnonyms() {
        return sysnonyms;
    }

    public void setSysnonyms(List<String> sysnonyms) {
        this.sysnonyms = sysnonyms;
    }
}
