package ru.masha;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Laptop> laptops = new ArrayList<>();

        for (int i = 0; i <= 9; i++) {
            laptops.add(Laptop.createRandomLaptop());
        }

        for (int i = 0; i < laptops.size(); i++) {
            Laptop currentLaptop = laptops.get(i);
            // if (currentLaptop.diagonal > 12 && currentLaptop.rom >= 8)
            System.out.println(currentLaptop);
        }
    }

}