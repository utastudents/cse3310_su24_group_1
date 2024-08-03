package uta.cse3310;

public class Wheel {
    private String[] items = {"Nothing", "Bankrupt", "Free Spin", "Consonant Hint", "Vowel Hint",
                              "100", "150", "200", "250", "300",
                              "350", "400", "450", "500", "550",
                              "600", "700", "800", "900", "1000"};

    public String getRandomItem() {
        int randIndex = (int)(Math.random() * 20);
        return items[randIndex];
    }
}