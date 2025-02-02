package main;

import java.io.Serializable;

public class Monster implements Serializable {
    String type;
    int health;
    private static final long serialVersionUID = 345633;

    public Monster(String type, int health){
        this.type = type;
        this.health = health;
    }

    public void printInfo(int number) {
        System.out.println(number+": "+type+" / "+health+"HP");
    }

    public boolean takeDamage(int dmg){
        health -= dmg;
        if(health > 0){
            return false;
        }
        else{
            return true;
        }
    }
}
