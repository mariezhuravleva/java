package toyshop;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ToyShop {
    private List<Toy> toys;

    public ToyShop() {
        toys = new ArrayList<>();
    }

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public Toy getRandomToy() {
        Random random = new Random();
        double totalWeight = toys.stream().mapToDouble(Toy::getFrequency).sum();
        double randomWeight = random.nextDouble() * totalWeight;

        for (Toy toy : toys) {
            randomWeight -= toy.getFrequency();
            if (randomWeight <= 0) {
                if (toy.getQuantity() > 0) {
                    toy.setQuantity(toy.getQuantity() - 1);
                    return toy;
                }
            }
        }
        return null;
    }

    public void saveToyToFile(Toy toy, String fileName) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write("ID: " + toy.getId() + ", Name: " + toy.getName() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ToyShop toyShop = new ToyShop();

        Toy teddyBear = new Toy(1, "Teddy Bear", 10, 20); // Example toy
        Toy car = new Toy(2, "Car", 5, 15); // Example toy
        Toy doll = new Toy(3, "Doll", 1, 3); // Example toy
        Toy ball = new Toy(4, "Ball", 50, 50); // Example toy
        Toy puzzle = new Toy(5, "Puzzle", 30, 25); // Example toy
        Toy baseballSet = new Toy(6, "Baseball Set", 2, 7); // Example toy
        toyShop.addToy(teddyBear);
        toyShop.addToy(car);
        toyShop.addToy(doll);
        toyShop.addToy(ball);
        toyShop.addToy(puzzle);
        toyShop.addToy(baseballSet);

        // Simulating toy draw 10 times
        for (int i = 0; i < 10; i++) {
            Toy randomToy = toyShop.getRandomToy();
            if (randomToy != null) {
                toyShop.saveToyToFile(randomToy, "winners.txt");
            }
        }
    }
}