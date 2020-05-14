package com.lenercab.ribbonclient;



public class Product {


    private Long id;
    private String category;
    private String provider;
    private String description;
    private double purchasePrice;
    private double salePrice;


    public Product(Long id, String category, String provider, String description, double purchasePrice, double salePrice) {
        this.id = id;
        this.category = category;
        this.provider = provider;
        this.description = description;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", provider='" + provider + '\'' +
                ", description='" + description + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", salePrice=" + salePrice +
                '}';
    }
}
