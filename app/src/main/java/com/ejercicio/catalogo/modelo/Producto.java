package com.ejercicio.catalogo.modelo;

public class Producto {

    int id;
    String name;
    String thumbnail_url;
    String price;
    String provider;
    String delivery;

    public Producto(int id, String name, String thumbnail_url, String price, String provider, String delivery) {
        this.id = id;
        this.name = name;
        this.thumbnail_url = thumbnail_url;
        this.price = price;
        this.provider = provider;
        this.delivery = delivery;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }
}
