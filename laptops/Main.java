package laptops;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Set<Laptop> laptops = createLaptops();

        Map<String, Object> filters = getFilters();
        Set<Laptop> filteredLaptops = filterLaptops(laptops, filters);

        if (filteredLaptops.isEmpty()) {
            System.out.println("Не найдено ноутбуков по заданным критериям.");
        } else {
            System.out.println("Filtered Laptops:");
            for (Laptop laptop : filteredLaptops) {
                System.out.println(laptop);
            }
        }
    }

    private static Set<Laptop> createLaptops() {
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("Dell 2303", 8, 512, "Windows", "Black", 15.6));
        laptops.add(new Laptop("ASUS AX89", 16, 1024, "Windows", "Silver", 14.0));
        laptops.add(new Laptop("Acer Turbo",32, 2048, "Ubuntu", "Blue", 13.3));
        
        return laptops;
    }

    private static Map<String, Object> getFilters() {
        Scanner scanner = new Scanner(System.in);
        Map<String, Object> filters = new HashMap<>();
    
        System.out.println("Выберите критерий для фильтрации (или введите 0 для завершения):");
        System.out.println("1 - RAM");
        System.out.println("2 - HDD");
        System.out.println("3 - OS");
        System.out.println("4 - Цвет");
        System.out.println("5 - Размер экрана");
    
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equals("0")) {
                break;
            }
    
            switch (input) {
                case "1":
                    System.out.println("Введите минимальный объем RAM:");
                    int ram = Integer.parseInt(scanner.nextLine().trim());
                    filters.put("RAM", ram);
                    break;
                case "2":
                    System.out.println("Введите минимальный объем HDD:");
                    int hdd = Integer.parseInt(scanner.nextLine().trim());
                    filters.put("HDD", hdd);
                    break;
                case "3":
                    System.out.println("Введите операционную систему:");
                    String os = scanner.nextLine().trim();
                    filters.put("OS", os);
                    break;
                case "4":
                    System.out.println("Введите цвет:");
                    String color = scanner.nextLine().trim();
                    filters.put("color", color);
                    break;
                case "5":
                    System.out.println("Введите минимальный размер экрана:");
                    double screenSize = Double.parseDouble(scanner.nextLine().trim());
                    filters.put("screenSize", screenSize);
                    break;
                default:
                    System.out.println("Некорректный ввод.");
                    break;
            }
            System.out.println("Выберите еще один критерий (или введите 0 для завершения):");
        }
    
        scanner.close();
        return filters;
    }
    

    private static Set<Laptop> filterLaptops(Set<Laptop> laptops, Map<String, Object> filters) {
    for (Map.Entry<String, Object> entry : filters.entrySet()) {
        String key = entry.getKey();
        Object value = entry.getValue();

        if (key.equals("RAM")) {
            laptops = laptops.stream()
                    .filter(laptop -> laptop.getRamSize() >= (int) value)
                    .collect(Collectors.toSet());
        } else if (key.equals("HDD")) {
            laptops = laptops.stream()
                    .filter(laptop -> laptop.getHddSize() >= (int) value)
                    .collect(Collectors.toSet());
        } else if (key.equals("OS")) {
            laptops = laptops.stream()
                    .filter(laptop -> laptop.getOperatingSystem().equals(value))
                    .collect(Collectors.toSet());
        } else if (key.equals("color")) {
            laptops = laptops.stream()
                    .filter(laptop -> laptop.getColor().equals(value))
                    .collect(Collectors.toSet());
        } else if (key.equals("screenSize")) {
            laptops = laptops.stream()
                    .filter(laptop -> laptop.getScreenSize() >= (double) value)
                    .collect(Collectors.toSet());
        }   
    }

    return laptops;
    }
}