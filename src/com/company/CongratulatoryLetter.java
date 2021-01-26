package com.company;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CongratulatoryLetter {

    public static void printLetter(Player player, int position) {
        String name = player.getNameAndSurname();
        name = name.replaceAll(" ", "");

        try {
            Path path = createPath("/" + name + ".txt");
            Files.createFile(path);
            BufferedWriter writer = Files.newBufferedWriter(path);

            writer.write(player.getNameAndSurname() + "\n");
            writer.write(player.getTown() + "\n\n");

            char gender = player.getGender();

            if(gender == 'K') {
                writer.write("Dear Miss " + player.getNameAndSurname() + "\n");
            } else {
                writer.write("Dear Sir " + player.getNameAndSurname() + "\n");
            }

            writer.write("We congratulate achieving " + position + " place in our tournament.\n");
            writer.write("Tournament organizer");

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Path createPath(String finalDirectory) {
        String pathCreator = "/Users/krystian/Desktop/PodstawyJavy/Card_Tournament" +
                finalDirectory;
        return Path.of(pathCreator);
    }

}
