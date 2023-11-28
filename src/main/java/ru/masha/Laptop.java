package ru.masha;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Laptop {
    public final int diagonal;
    public final int rom;
    public final int price;

    public Laptop(int diagonal, int rom, int price) {
        this.diagonal = diagonal;
        this.rom = rom;
        this.price = price;
    }

    @Override
    public String toString() {
        return "диагональ = " + getPrettyDiagonal() + ", оперативная память = " + gerPrettyRom() + ", цена = " + getPrettyPrice();
    }

    public String getPrettyDiagonal() {
        return diagonal + "'";
    }

    public String gerPrettyRom() {
        return rom + "GB";
    }

    public String getPrettyPrice() {
        return price + " ₽";
    }

    public static Laptop createRandomLaptop() {
        List<Integer> diagonals = new ArrayList<>() {{
            add(11);
            add(13);
            add(15);
            add(17);
        }};
        Random rnd = new Random();
        int diagonalIndex = rnd.nextInt(diagonals.size());
        return new Laptop(
                diagonals.get(diagonalIndex),
                (int) Math.pow(2, rnd.nextInt(7)),
                rnd.nextInt(100_000)
        );
    }
}
