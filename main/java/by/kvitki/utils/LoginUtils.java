package by.kvitki.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class LoginUtils {
    private static final String aToZ="abcdefghigklmnopqrstuvwxyz1234567890";

    public static String createRandomWord() {
        int wordLength = ThreadLocalRandom.current().nextInt(1, 26);
        StringBuilder randomWord = new StringBuilder();
        Random rand = new Random();

        for(int i = 0; i < wordLength; i++) {
            int randIndex = rand.nextInt(aToZ.length());
            randomWord.append(aToZ.charAt(randIndex));
        }
        return randomWord.toString();
    }

    public static int createRandomCentreId() {
        int idLength = ThreadLocalRandom.current().nextInt(1, 4);
        String randomId = "";
        for (int i = 0; i < idLength; i++){
            randomId += (int) (Math.random() * 10);
        }
        System.out.println(randomId);
        return Integer.parseInt(randomId);

    }

    public static String getRandomEmail() {
        return createRandomWord() + "@mail.com";
    }

    public static String getRandomPassword() {
        return createRandomWord();
    }
}
