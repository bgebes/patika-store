package patikastore.models.product;

import lombok.Getter;
import lombok.Setter;
import patikastore.models.brand.Brand;
import patikastore.providers.BrandProvider;
import patikastore.utilities.DataOperations;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
public class Notebook extends Product<Notebook> {
    private int memorySize;
    private double screenSize;
    private int ramSize;

    public static int lastID = -1;

    public Notebook() {
        super();
    }

    public Notebook(String name, double price, double discountRate, int stockAmount, Brand brand,
                    int memorySize, double screenSize, int ramSize) {
        super(++lastID, name, price, discountRate, stockAmount, brand);

        this.memorySize = memorySize;
        this.screenSize = screenSize;
        this.ramSize = ramSize;
    }

    @Override
    public String[] getHeaders() {
        return new String[]{"ID", "Ürün Adı", "Fiyat", "Marka", "Depolama", "Ekran", "RAM"};
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", getId());
        map.put("name", getName());
        map.put("price", getPrice());
        map.put("discountRate", getDiscountRate());
        map.put("stockAmount", getStockAmount());
        map.put("brand", getBrand());
        map.put("memorySize", getMemorySize());
        map.put("screenSize", getScreenSize());
        map.put("ramSize", getRamSize());

        return map;
    }

    @Override
    public Notebook fromMap(Map<String, String> map) {
        return new Notebook(
                map.get("name"),
                Double.parseDouble(map.get("price")),
                Double.parseDouble(map.get("discountRate")),
                Integer.parseInt(map.get("stockAmount")),
                DataOperations.selectItemsByFilter(
                        BrandProvider.brands,
                        (b -> Objects.equals(b.getName(), map.get("brand")))).get(0),
                Integer.parseInt(map.get("memorySize")),
                Double.parseDouble(map.get("screenSize")),
                Integer.parseInt(map.get("ramSize"))
        );
    }

    @Override
    public String[] getKeys() {
        return new String[]{"name", "price", "discountRate", "stockAmount", "brand", "memorySize", "screenSize", "ramSize"};
    }

    @Override
    public Object[] getValues() {
        return new String[]{
                String.valueOf(getId()),
                getName(),
                String.valueOf(getPrice()),
                getBrand().getName(),
                String.valueOf(getMemorySize()),
                String.valueOf(getScreenSize()),
                String.valueOf(getRamSize())};
    }
}
