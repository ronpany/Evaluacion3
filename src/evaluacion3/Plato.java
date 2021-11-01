package evaluacion3;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RONNY PANTOJA
 */
public class Plato {
    private String name;
    private float price;
    // La categoria se la va a manejar asi [1=Entrada, 2=Plato Fuerte, 3=Postre, 4=Bebida] 
    private String category;

    public Plato(String name, float price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Plato{" + "name=" + name + ", price=" + price + ", category=" + category + '}';
    }
    
    
}
