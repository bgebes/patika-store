package patikastore.utilities;

import patikastore.models.brand.Brand;
import patikastore.models.product.Notebook;
import patikastore.models.product.Product;
import patikastore.models.product.SmartPhone;
import patikastore.providers.BrandProvider;
import patikastore.providers.NotebookProvider;
import patikastore.providers.SmartPhoneProvider;

import java.time.temporal.ValueRange;
import java.util.*;

public class UserInterface {
    public static final String storeName = "PatikaStore";
    public static final Scanner scanner = new Scanner(System.in);

    public static void start() {
        int request;

        while (true) {
            String mainMenu = String.format("%s Ürün Yönetim Paneli !\n", storeName) +
                    "1 - Notebook İşlemleri\n" +
                    "2 - Cep Telefonu İşlemleri\n" +
                    "3 - Marka Listele\n" +
                    "0 - Çıkış Yap\n" +
                    "Tercihiniz: ";

            System.out.print(mainMenu);
            request = Integer.parseInt(scanner.nextLine().trim());
            if (ValueRange.of(0, 3).isValidIntValue(request)) {
                switch (request) {
                    case 1 -> listTransactionsMessages(new Notebook());
                    case 2 -> listTransactionsMessages(new SmartPhone());
                    case 3 -> {
                        printBrands();
                        start();
                        return;
                    }
                    case 0 -> System.exit(0);
                }

                break;
            }
        }
    }

    public static <T> void listTransactionsMessages(T t) {
        int request;

        while (true) {
            String itemName;
            List<?> list;
            if (t instanceof Notebook) {
                itemName = "Notebook";
                list = NotebookProvider.notebooks;
            } else if (t instanceof SmartPhone) {
                itemName = "Cep Telefonu";
                list = SmartPhoneProvider.smartPhones;
            } else {
                throw new NullPointerException();
            }

            String transactionsMessage = String.format("1- %s Ekle\n", itemName) +
                    String.format("2- %s Listesini Getir\n", itemName) +
                    String.format("3- %s Filtreleyerek Listesini Getir\n", itemName) +
                    String.format("4- %s Sil\n", itemName) +
                    "0- Ana Menüye Dön\n" +
                    "Tercihiniz: ";

            System.out.print(transactionsMessage);
            request = Integer.parseInt(scanner.nextLine().trim());
            if (ValueRange.of(0, 4).isValidIntValue(request)) {
                switch (request) {
                    case 1 -> addItemTransaction(list, itemName);
                    case 2 -> printItemList(list, itemName);
                    case 3 -> printFilteredItemList(list, itemName);
                    case 4 -> deleteItemTransaction(list, itemName);
                    case 0 -> {
                        start();
                        return;
                    }
                }

                listTransactionsMessages(t);
                break;
            }
        }
    }

    public static <T> void addItemTransaction(List<T> list, String itemName) {
        System.out.printf("------ Yeni %s Bilgileri ------\n", itemName);

        if (list.isEmpty() || !(list.get(0) instanceof Notebook || list.get(0) instanceof SmartPhone)) {
            throw new NullPointerException();
        }

        String[] keys = ((Product<?>) list.get(0)).getKeys();
        Map<String, String> map = new HashMap<>();
        for (String key : keys) {
            System.out.printf("%s: ", key);

            String value = scanner.nextLine();
            map.put(key, value);
        }

        T item = (T) ((Product<?>) list.get(0)).fromMap(map);
        DataOperations.createItem(list, item);

        printFilteredItemList(list, itemName);
    }

    public static <T> void printItemList(List<T> list, String itemName) {
        StringBuilder itemsMessage = new StringBuilder();
        StringBuilder formatBuilder = new StringBuilder();

        if (list.isEmpty() || !(list.get(0) instanceof Notebook || list.get(0) instanceof SmartPhone)) {
            throw new NullPointerException();
        }

        itemsMessage.append(String.format("%s Listesi\n\n", itemName));
        itemsMessage.append("----------------------------------------------------------------------------------------------------\n");

        String[] keys = ((Product<?>) list.get(0)).getHeaders();
        for (String key : keys) {
            String itemFormat = "|\t%s\t";

            itemsMessage.append(String.format(itemFormat, key));
            formatBuilder.append(itemFormat);
        }
        itemsMessage.append("\n----------------------------------------------------------------------------------------------------\n");

        for (T item : list) {
            itemsMessage.append(String.format(formatBuilder + "\n", ((Product<?>) item).getValues()));
        }
        itemsMessage.append("----------------------------------------------------------------------------------------------------\n");
        System.out.print(itemsMessage);
    }

    public static <T> void printFilteredItemList(List<T> list, String itemName) {
        int request1;

        while (true) {
            String filtersMessage = "1- ID Numarasına Göre Filtrele\n" +
                    "2- Markasına Göre Filtrele\n" +
                    "0- Ana Menüye Dön\n" +
                    "Tercihiniz: ";

            System.out.print(filtersMessage);
            request1 = Integer.parseInt(scanner.nextLine().trim());
            if (ValueRange.of(0, 2).isValidIntValue(request1)) {
                if (request1 == 0) {
                    start();
                    return;
                }

                break;
            }
        }

        String filterMessage = String.format("%s: ", request1 == 1 ? "ID Numarası" : "Marka");
        System.out.print(filterMessage);

        List<T> filteredList = new ArrayList<>();

        if (request1 == 1) {
            int request2 = scanner.nextInt();

            T product = DataOperations.selectItem(list, request2);
            filteredList.add(product);
        } else {
            String request2 = scanner.nextLine();

            List<T> filteredListByBrand = DataOperations.selectItemsByFilter(list,
                    (x) -> Objects.equals(((Product<?>) x).getBrand().getName(), request2));
            filteredList.addAll(filteredListByBrand);
        }

        if (filteredList.isEmpty()) {
            System.out.println("Verilen bilgilere göre ürün bulunamadı!\n");
            printFilteredItemList(list, itemName);
        } else {
            printItemList(filteredList, itemName);
        }
    }

    public static <T> void deleteItemTransaction(List<T> list, String itemName) {
        printItemList(list, itemName);

        System.out.printf("Silmek istediğiniz %s için ID numarasını giriniz: ", itemName);

        int request = Integer.parseInt(scanner.nextLine().trim());

        try {
            DataOperations.deleteItem(list, request);
        } catch (Exception e) {
            deleteItemTransaction(list, itemName);
        }
    }

    public static <T> void printBrands() {
        StringBuilder brandList = new StringBuilder();
        brandList.append("----------- MARKALAR -----------\n");

        for (Brand b : BrandProvider.brands) {
            brandList.append(String.format("- %s\n", b.getName()));
        }

        System.out.print(brandList);
    }
}
