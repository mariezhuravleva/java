package attestation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

class UserData {
    private String lastName;
    private String firstName;
    private String middleName;
    private String birthDate;
    private long phoneNumber;
    private char gender;

    public UserData(String lastName, String firstName, String middleName, String birthDate, long phoneNumber, char gender) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public String toString() {
        return "<"+ lastName + "><" + firstName + "><" + middleName + "><" + birthDate + "><" + phoneNumber + "><" + gender + ">";
    }

    public String getLastName() {
        return lastName;
    }
}

class UserDataException extends Exception {
    public UserDataException(String message) {
        super(message);
    }
}

class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            String[] data = scanner.nextLine().split(" ");

            if (data.length != 6) {
                throw new UserDataException("Неверное количество данных");
            }

            String lastName = data[0];
            String firstName = data[1];
            String middleName = data[2];
            String birthDate = data[3];
            long phoneNumber;
            char gender;

            try {
                phoneNumber = Long.parseLong(data[4]);
            } catch (NumberFormatException e) {
                throw new UserDataException("Неверный формат номера телефона");
            }

            if (!data[5].equals("m") && !data[5].equals("f")) {
                throw new UserDataException("Неверный формат пола");
            } else {
                gender = data[5].charAt(0);
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            dateFormat.setLenient(false);

            try {
                dateFormat.parse(birthDate);
            } catch (ParseException e) {
                throw new UserDataException("Неверный формат даты рождения");
            }

            UserData user = new UserData(lastName, firstName, middleName, birthDate, phoneNumber, gender);
            writeFile(user);
            scanner.close();
        } catch (UserDataException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static void writeFile(UserData user) {
        String fileName = user.getLastName() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(user.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}