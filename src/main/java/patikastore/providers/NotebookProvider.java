package patikastore.providers;

import patikastore.models.brand.Brand;
import patikastore.models.product.Notebook;
import patikastore.utilities.DataOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NotebookProvider {
    public static List<Notebook> notebooks;

    static {
        List<Brand> brands = BrandProvider.brands;

        notebooks = new ArrayList<>();

        notebooks.add(new Notebook("HUAWEI Matebook 14", 7000, 0, 100,
                DataOperations.selectItemsByFilter(brands, (brand -> Objects.equals(brand.getName(), "Huawei"))).get(0),
                512, 14.0, 16));

        notebooks.add(new Notebook("LENOVO V14 IGL", 3699, 0, 100,
                DataOperations.selectItemsByFilter(brands, (brand -> Objects.equals(brand.getName(), "Lenovo"))).get(0),
                1024, 14.0, 8));

        notebooks.add(new Notebook("ASUS Tuf Gaming", 8199, 0, 100,
                DataOperations.selectItemsByFilter(brands, (brand -> Objects.equals(brand.getName(), "Asus"))).get(0),
                2048, 15.6, 32));
    }
}
