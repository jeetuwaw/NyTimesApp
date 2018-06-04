package app.nytimes.promobi.com.nytimesapp.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.SerializedName;

public class Results extends BaseObservable {
    @SerializedName("abstract")
    private String absString;

    private String created_date;

    private String published_date;

    private String[] geo_facet;

    private String[] per_facet;

    private String subsection;

    private String kicker;

    private String section;

    private String url;

    private String[] des_facet;

    private String title;

    private Multimedia[] multimedia;

    private String byline;

    private String updated_date;

    private String short_url;

    private String[] org_facet;

    private String item_type;

    private String material_type_facet;

    public String getAbstract() {
        return absString;
    }

    public void setAbstract(String absString) {
        this.absString = absString;
    }

    @Bindable
    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    @Bindable
    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    @Bindable
    public String[] getGeo_facet() {
        return geo_facet;
    }

    public void setGeo_facet(String[] geo_facet) {
        this.geo_facet = geo_facet;
    }

    @Bindable
    public String[] getPer_facet() {
        return per_facet;
    }

    public void setPer_facet(String[] per_facet) {
        this.per_facet = per_facet;
    }

    @Bindable
    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    @Bindable
    public String getKicker() {
        return kicker;
    }

    public void setKicker(String kicker) {
        this.kicker = kicker;
    }

    @Bindable
    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @Bindable
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Bindable
    public String[] getDes_facet() {
        return des_facet;
    }

    public void setDes_facet(String[] des_facet) {
        this.des_facet = des_facet;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Bindable
    public Multimedia[] getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(Multimedia[] multimedia) {
        this.multimedia = multimedia;
    }

    @Bindable
    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    @Bindable
    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }

    @Bindable
    public String getShort_url() {
        return short_url;
    }

    public void setShort_url(String short_url) {
        this.short_url = short_url;
    }

    @Bindable
    public String[] getOrg_facet() {
        return org_facet;
    }

    public void setOrg_facet(String[] org_facet) {
        this.org_facet = org_facet;
    }

    @Bindable
    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    @Bindable
    public String getMaterial_type_facet() {
        return material_type_facet;
    }

    public void setMaterial_type_facet(String material_type_facet) {
        this.material_type_facet = material_type_facet;
    }

    @Override
    public String toString() {
        return "ClassPojo [abstract = " + getAbstract() + ", created_date = " + getCreated_date() + ", published_date = " + getPublished_date() + ", geo_facet = " + getGeo_facet() + ", per_facet = " + getPer_facet() + ", subsection = " + getSubsection() + ", kicker = " + getKicker() + ", section = " + getSection() + ", url = " + getUrl() + ", des_facet = " + getDes_facet() + ", title = " + getTitle() + ", multimedia = " + getMultimedia() + ", byline = " + getByline() + ", updated_date = " + getUpdated_date() + ", short_url = " + getShort_url() + ", org_facet = " + getOrg_facet() + ", item_type = " + getItem_type() + ", material_type_facet = " + getMaterial_type_facet() + "]";
    }
}
