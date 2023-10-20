package patikastore.models.product;

import lombok.Getter;
import lombok.Setter;
import patikastore.models.brand.Brand;
import patikastore.utilities.MapOperations;

@Getter
@Setter
public abstract class Product<T> implements MapOperations<String, Object, T> {
    public int id;
    public String name;
    public double price;
    public double discountRate;
    public int stockAmount;
    public Brand brand;

    public Product() {
    }

    public Product(int id, String name, double price, double discountRate, int stockAmount, Brand brand) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discountRate = discountRate;
        this.stockAmount = stockAmount;
        this.brand = brand;
    }

}
