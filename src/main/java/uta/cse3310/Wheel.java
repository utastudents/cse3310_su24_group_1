package uta.cse3310;
import java.util.Random;

public class Wheel {
    private String[] item = {"money", "bankrupt_another_player", "other"};


    public String getRandomItem() {

            Random random = new Random();
           int random_number = random.nextInt(2); // randome nubmer between 0~2
           String random_item = item[random_number];
    
            System.out.print(random_item);
            return random_item;
            
        
    }
}