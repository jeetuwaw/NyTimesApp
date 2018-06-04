package app.nytimes.promobi.com.nytimesapp.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class Multimedia extends BaseObservable{
    private String height;

    private String subtype;

    private String width;

    private String caption;

    private String copyright;

    private String format;

    private String type;

    private String url;

    @Bindable
    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    @Bindable
    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    @Bindable
    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    @Bindable
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Bindable
    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    @Bindable
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Bindable
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Bindable
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ClassPojo [height = " + getHeight() + ", subtype = " + getSubtype() + ", width = " + getWidth() + ", caption = " + getCaption() + ", copyright = " + getCopyright() + ", format = " + getFormat() + ", type = " + getType() + ", url = " + getUrl() + "]";
    }
}
