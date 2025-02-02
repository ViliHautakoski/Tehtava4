package main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
public class App 
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);

        boolean exit = false;
        
        System.out.println("Syötä pelaajan nimi:");

        String playerName = sc.nextLine();

        Player player = new Player(playerName);

        Cave cave = new Cave(player);
        
        while(exit != true){
            System.out.println("1) Lisää luolaan hirviö");
            System.out.println("2) Listaa hirviöt");
            System.out.println("3) Hyökkää hirviöön");
            System.out.println("4) Tallenna peli");
            System.out.println("5) Lataa peli");
            System.out.println("0) Lopeta peli");

            if(sc.hasNext()){
                String stringInput = sc.nextLine();
                int i = Integer.parseInt(stringInput);

                switch(i){
                    case 1:
                        System.out.println("Anna hirviön tyyppi:");
                        String type = sc.nextLine();
                        System.out.println("Anna hirviön elämän määrä numerona:");
                        int health = Integer.parseInt(sc.nextLine());
                        Monster monster = new Monster(type, health);
                        cave.addMonster(monster);
                        break;
                    case 2:
                        cave.listMonsters();
                        break;
                    case 3:
                        System.out.println("Valitse hirviö, johon hyökätä:");
                        cave.listMonsters();
                        int j = Integer.parseInt(sc.nextLine());
                        cave.player.attack(cave.monsterList.get(j-1));
                        Monster targetMonster = cave.monsterList.get(j-1);
                        boolean status = targetMonster.takeDamage(10);
                        if (status == true){
                            cave.removeMonster(targetMonster);
                        }
                        break;
                    case 4:
                        System.out.println("Anna tiedoston nimi, johon peli tallentaa:");
                        String writefilename = sc.nextLine();
                        try {
                        ObjectOutputStream saveGame = new ObjectOutputStream(new FileOutputStream(writefilename));
                        saveGame.writeObject(cave);
                        saveGame.close();
                        System.out.println("Peli tallennettiin tiedostoon "+writefilename+".");
                        } 
                        catch (IOException e) {
                            System.out.println("Tiedostoa ei voitu tallentaa.");
                            e.printStackTrace();
                        }
                        break;
                    case 5:
                        System.out.println("Anna tiedoston nimi, josta peli ladataan:");
                        String readfilename = sc.nextLine();
                        try{
                        ObjectInputStream saveGame = new ObjectInputStream(new FileInputStream(readfilename));
                        cave = (Cave) saveGame.readObject();
                        saveGame.close();
                        System.out.println("Peli ladattu tiedostosta "+readfilename+". Tervetuloa takaisin, "+cave.player.name+".");
                        }
                        catch (IOException e){
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 0:
                        exit = true;
                        break;

                    default:
                        System.out.println("Väärä syöte.");
                    }
            }
        }
        sc.close();
        System.out.println("Peli päättyy. Kiitos pelaamisesta!");
    }
}
