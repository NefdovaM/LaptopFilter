package ru.masha;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class LaptopShop {
    private final List<Laptop> laptops = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private static final int MENU_EXIT = 0;
    private static final int MENU_FILTER_DIAGONAL = 1;
    private static final int MENU_FILTER_ROM = 2;
    private static final int MENU_FILTER_PRICE = 3;
    private static final int MENU_FILTER_COLOUR = 4;
    private static final int MENU_ACCEPT_FILTERS = 10;
    private static final HashMap<Integer, String> filters = new HashMap<>() {{
        put(MENU_FILTER_DIAGONAL, "Диагональ");
        put(MENU_FILTER_ROM, "Оперативная память");
        put(MENU_FILTER_PRICE, "Цена");
        put(MENU_FILTER_COLOUR, "Цвет");
    }};
    private final HashMap<Integer, Integer> userFilter = new HashMap<>();

    public LaptopShop() {
        for (int i = 0; i <= 9; i++) {
            laptops.add(Laptop.createRandomLaptop());
        }
        for (Laptop filteredLaptop : laptops) {
            System.out.println(filteredLaptop);
        }
    }

    public void open() {
        int userInput = -1;
        do {
            userInput = mainMenu();
            switch (userInput) {
                case MENU_FILTER_DIAGONAL -> {
                    int diagonal = takeInt("Введите диагональ");
                    userFilter.put(MENU_FILTER_DIAGONAL, diagonal);
                }
                case MENU_FILTER_ROM -> {
                    int rom = takeInt("Введите количество оперативной памяти в GB");
                    userFilter.put(MENU_FILTER_ROM, rom);
                }
                case MENU_FILTER_PRICE -> {
                    int price = takeInt("Введите цену в рублях");
                    userFilter.put(MENU_FILTER_PRICE, price);
                }
                case MENU_FILTER_COLOUR -> {
                    int colour = takeColourMenu();
                    userFilter.put(MENU_FILTER_COLOUR, colour);
                }
                case MENU_ACCEPT_FILTERS -> acceptFilters();
                case 100 -> {
                    userFilter.forEach((id, value) -> {
                        System.out.println("введен " + filters.get(id) + " со значением " + value);
                    });
                }
            }
        } while (userInput != MENU_EXIT);
        System.out.println("Good bye");
    }

    private void acceptFilters() {
        List<Laptop> filteredLaptops = new ArrayList<>(laptops);
        userFilter.forEach((id, value) -> {
            switch (id) {
                case MENU_FILTER_DIAGONAL -> filteredLaptops.removeIf((laptop) -> laptop.diagonal != value);
                case MENU_FILTER_ROM -> filteredLaptops.removeIf((laptop) -> laptop.rom < value);
                case MENU_FILTER_PRICE -> filteredLaptops.removeIf((laptop) -> laptop.price > value);
                case MENU_FILTER_COLOUR -> filteredLaptops.removeIf((laptop) -> laptop.colour != value);
            }

        });
        if (filteredLaptops.isEmpty()) {
            System.out.println("Нет по вашим критериям");
            return;
        }
        for (Laptop filteredLaptop : filteredLaptops) {
            System.out.println(filteredLaptop);
        }
    }

    private int takeColourMenu() {
        System.out.println("Выберите цвет");
        Laptop.coloursMap.forEach((id, colour) -> {
            System.out.println(id + " - " + colour);
        });
        return scanner.nextInt();
    }

    private int takeInt(String msg) {
        System.out.println(msg);
        return scanner.nextInt();
    }

    private int mainMenu() {
        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println(MENU_EXIT + " - Выход");
        filters.forEach((id, name) -> {
            System.out.println(id + " - " + name);
        });
        System.out.println(MENU_ACCEPT_FILTERS + " - Применить фильтры");
        return scanner.nextInt();
    }
}
