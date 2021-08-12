package sg.edu.rp.c346.id20031826.sa_sugarspice;

import java.io.Serializable;

public class Recipe implements Serializable {

    private int id;
    private String name;
    private String ingredients;
    private String method;
    private String tips;

    public Recipe(String name, String ingredients, String method, String tips) {
        this.name = name;
        this.ingredients = ingredients;
        this.method = method;
        this.tips = tips;
    }

    public Recipe(int id, String name, String ingredients, String method, String tips) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.method = method;
        this.tips = tips;
    }

    public int getId() {
        return id;
    }

    public Recipe setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Recipe setName(String title) {
        this.name = name;
        return this;
    }

    public String getIngredients() {
        return ingredients;
    }

    public Recipe setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public Recipe setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getTips() {
        return tips;
    }

    public Recipe setTips(String tips) {
        this.tips = tips;
        return this;
    }

    @Override
    public String toString() {
        return name + "\n" + ingredients + "\n" + method + "\n" + tips;
    }
}
