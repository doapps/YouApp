package me.doapps.beans;

/**
 * Created by Gantz on 3/12/14.
 */
public class Channel_DTO {

    private String id;
    private String nombre;
    private String url;

    private int imagenDrawable;
    private int colorResource;

    private String color;

    public Channel_DTO(String id, String color, int colorResource, int imagenDrawable, String url, String nombre) {
        this.id = id;
        this.color = color;
        this.colorResource = colorResource;
        this.imagenDrawable = imagenDrawable;
        this.url = url;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getColorResource() {
        return colorResource;
    }

    public void setColorResource(int colorResource) {
        this.colorResource = colorResource;
    }

    public int getImagenDrawable() {
        return imagenDrawable;
    }

    public void setImagenDrawable(int imagenDrawable) {
        this.imagenDrawable = imagenDrawable;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
