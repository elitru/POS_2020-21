/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.eliastrummer.beans;

/**
 *
 * @author root
 */
public class Pizza {
    private final int id;
    private String name;
    private String description;
    private float price;
    private String imageSource;

    public Pizza(int id, String name, String description, float price, String imageSource) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageSource = imageSource;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public String toHTML() {
        String result = "<section class='pizza'>";
           
        result += String.format("<div style='background: url(\"%s\") center no-repeat;' class='pizza-img'></div>", imageSource);
        result += String.format("<div class='pizza-info'><span class='pizza-title'>%s</span><span class='description'>%s</span></div>", name + " " + String.format("€ %.2f", getPrice()), description);
        result += String.format("<input type='number' value='0' name='%s' class='amount' />", "amount_" + id);
        
        return result + "</section>";
    }
}
