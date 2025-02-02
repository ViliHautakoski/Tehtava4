package main;

import java.io.Serializable;

public class Player implements Serializable {
    String name;
    private static final long serialVersionUID = 122133;

    public Player(String name){
        this.name = name;
    }

    public void attack(Monster target) {
        System.out.println(name+" hyökkää "+target.type+" hirviöön!");
        if(target.health > 0){
            System.out.println("Hirviöllä on "+target.health+" elämää jäljellä.");
        }
    }
}

