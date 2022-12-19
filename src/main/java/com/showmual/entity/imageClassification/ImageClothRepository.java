package com.showmual.entity.imageClassification;

public class ImageClothRepository {

    private String image;
    private String small_category;
    private int qty;
    private int big;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSmall_category() {
        return small_category;
    }

    public void setSmall_category(String small_category) {
        this.small_category = small_category;
    }

    public int getBig() {
        return big;
    }

    public void setBig(int big) {
        this.big = big;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

}
