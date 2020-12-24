package net.thedudemc.spectrum.config.entry;

import com.google.gson.annotations.Expose;

public class ConfigOption {

    @Expose
    private Object value;
    @Expose
    private String comment;

    public ConfigOption(Object value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
