package patikastore.providers;

import patikastore.models.brand.Brand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BrandProvider {
    public static List<Brand> brands;

    static {
        brands = new ArrayList<>();

        String[] sBrandList = {"Samsung", "Lenovo", "Apple", "Huawei", "Casper", "Asus", "HP", "Xiaomi", "Monster"};
        brands.addAll(Arrays.stream(sBrandList).map(Brand::new).toList());
    }
}
