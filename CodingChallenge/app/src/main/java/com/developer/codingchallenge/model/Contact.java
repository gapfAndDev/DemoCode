package com.developer.codingchallenge.model;

public class Contact {

    private int itemNumber;
    private String title;
    private String content;

    public int getItemNumber() {
        return itemNumber - 1;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    private Contact(Builder builder) {
        itemNumber = builder.itemNumber;
        title = builder.title;
        content = builder.content;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String title;
        private String content;
        private int itemNumber;

        private Builder() {
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder content(String val) {
            content = val;
            return this;
        }

        public Contact build() {
            return new Contact(this);
        }

        public Builder itemNumber(int val) {
            itemNumber = val;
            return this;
        }
    }
}
