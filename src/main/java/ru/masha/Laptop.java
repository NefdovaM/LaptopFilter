package ru.masha;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Laptop {
    public final int diagonal;
    public final int rom;
    public final int price;
    public final int colour;
    public final int producer;
    final static HashMap<Integer, String> coloursMap = new HashMap<>() {{
        put(1, "Серебрянный");
        put(2, "Черный");
        put(3, "Белый");
    }};
    final static HashMap<Integer, String> producersMap = new HashMap<>() {{
        put(1, "Lenovo");
        put(2, "ASUS");
        put(3, "HP");
        put(4, "Honor");
    }};

    public Laptop(int diagonal, int rom, int price, int colour, int producer) {
        this.diagonal = diagonal;
        this.rom = rom;
        this.price = price;
        this.colour = colour;
        this.producer = producer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("диагональ = ").append(getPrettyDiagonal());
        sb.append(", оперативная память = ").append(gerPrettyRom());
        sb.append(", цена = ").append(getPrettyPrice());
        sb.append(", цвет = ").append(getPrettyColour());
        sb.append(", производитель = ").append(getPrettyProducer());
        return sb.toString();
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

    public static boolean hasColour(int colour) {
        return coloursMap.containsKey(colour);
    }

    public static boolean hasProducer(int producer) {
        return  producersMap.containsKey(producer);
    }

    public String getPrettyColour() {
        if (coloursMap.containsKey(colour)) {
            return coloursMap.get(colour);
        }
        return "Неизвестный цвет";
    }

    public String getPrettyProducer() {
        if(producersMap.containsKey(producer)){
            return producersMap.get(producer);
        }
        return "Такой производитель отсутствует";
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
                rnd.nextInt(100_000),
                rnd.nextInt(1, coloursMap.size() + 1),
                rnd.nextInt(1, producersMap.size() + 1)
        );
    }
}
