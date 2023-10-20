package patikastore.models.product;

import lombok.Getter;
import lombok.Setter;
import patikastore.models.brand.Brand;
import patikastore.providers.BrandProvider;
import patikastore.utilities.DataOperations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
public class SmartPhone extends Product<SmartPhone> {
    private int memorySize;
    private double screenSize;
    private double batteryPower;
    private int ramSize;
    private int cameraPixelSize;
    private SmartPhoneColors color;

    public static int lastID = -1;

    public SmartPhone() {
    }

    public SmartPhone(String name, double price, double discountRate, int stockAmount, Brand brand,
                      int memorySize, double screenSize, double batteryPower, int ramSize, int cameraPixelSize,
                      SmartPhoneColors color) {
        super(++lastID, name, price, discountRate, stockAmount, brand);

        this.memorySize = memorySize;
        this.screenSize = screenSize;
        this.batteryPower = batteryPower;
        this.ramSize = ramSize;
        this.cameraPixelSize = cameraPixelSize;
        this.color = color;
    }

    @Override
    public String[] getHeaders() {
        return new String[]{"ID", "Ürün Adı", "Fiyat", "Marka", "Depolama", "Ekran", "Kamera", "Pil", "RAM", "Renk"};
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
        map.put("batteryPower", getScreenSize());
        map.put("ramSize", getRamSize());
        map.put("cameraPixelSize", getScreenSize());
        map.put("color", getColor());

        return map;
    }

    @Override
    public SmartPhone fromMap(Map<String, String> map) {
        return new SmartPhone(
                map.get("name"),
                Double.parseDouble(map.get("price")),
                Double.parseDouble(map.get("discountRate")),
                Integer.parseInt(map.get("stockAmount")),
                DataOperations.selectItemsByFilter(
                        BrandProvider.brands,
                        (b -> Objects.equals(b.getName(), map.get("brand")))).get(0),
                Integer.parseInt(map.get("memorySize")),
                Double.parseDouble(map.get("screenSize")),
                Double.parseDouble(map.get("batteryPower")),
                Integer.parseInt(map.get("ramSize")),
                Integer.parseInt(map.get("cameraPixelSize")),
                DataOperations.selectItemsByFilter(
                        Arrays.stream(SmartPhoneColors.values()).toList(),
                        (spc -> Objects.equals(spc.name(), map.get("color")))).get(0)
        );
    }

    @Override
    public String[] getKeys() {
        return new String[]{"name", "price", "discountRate", "stockAmount", "brand", "memorySize",
                "screenSize", "batteryPower", "ramSize", "cameraPixelSize", "color"};
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
                String.valueOf(getCameraPixelSize()),
                String.valueOf(getBatteryPower()),
                String.valueOf(getRamSize()),
                getColor().toString(),
        };
    }
}
