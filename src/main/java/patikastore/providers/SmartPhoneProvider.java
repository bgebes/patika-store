package patikastore.providers;

import patikastore.models.brand.Brand;
import patikastore.models.product.SmartPhone;
import patikastore.models.product.SmartPhoneColors;
import patikastore.utilities.DataOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SmartPhoneProvider {
    public static List<SmartPhone> smartPhones;

    static {
        List<Brand> brands = BrandProvider.brands;

        smartPhones = new ArrayList<>();

        smartPhones.add(new SmartPhone("SAMSUNG GALAXY A51", 3199, 0, 100,
                DataOperations.selectItemsByFilter(brands, (brand -> Objects.equals(brand.getName(), "Samsung"))).get(0),
                128, 6.5, 4000, 6, 32, SmartPhoneColors.Siyah));

        smartPhones.add(new SmartPhone("Iphone 11 64 GB", 7379, 0, 100,
                DataOperations.selectItemsByFilter(brands, (brand -> Objects.equals(brand.getName(), "Apple"))).get(0),
                64, 6.1, 3046, 6, 5, SmartPhoneColors.Mavi));

        smartPhones.add(new SmartPhone("Redmi Note 10 Pro 8 GB", 4012, 0, 100,
                DataOperations.selectItemsByFilter(brands, (brand -> Objects.equals(brand.getName(), "Xiaomi"))).get(0),
                128, 6.5, 4000, 12, 35, SmartPhoneColors.Kırmızı));
    }
}
