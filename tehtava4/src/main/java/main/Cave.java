package main;
import java.io.Serializable;
import java.util.ArrayList;

public class Cave implements Serializable {
    ArrayList<Monster> monsterList = new ArrayList<>();
    Player player;
    private static final long serialVersionUID = 4565667;

    public Cave(Player player){
        this.player = player;
    }

    public void addMonster(Monster monster) {
        monsterList.add(monster);
    }

    public void removeMonster(Monster monster) {
        monsterList.remove(monster);
    }

    public void listMonsters() {
        int i = 1;
        if (monsterList != null){
            for (Monster monster : monsterList){
                monster.printInfo(i++);
            }
        }
    }
}


