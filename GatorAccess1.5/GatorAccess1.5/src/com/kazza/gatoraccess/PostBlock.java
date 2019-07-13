package com.kazza.gatoraccess;

public class PostBlock {
    private String creator;
    private String date;
    private String textContent;
    private String serialCode;

    public PostBlock(String creator, String date, String textContent, String serialCode) {
        this.creator = creator;
        this.date = date;
        this.textContent = textContent;
        this.serialCode = serialCode;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getSerialCode() {
        return serialCode;
    }

    public void setSerialCode(String serialCode) {
        this.serialCode = serialCode;
    }
}
