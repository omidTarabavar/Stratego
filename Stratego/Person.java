package Stratego;
/**
 * class player person ke az class Players ers bari mikone
 * in class amal marbot be player person bazi ro barrasi mikone
 */
public class Person extends Players{
    /**
     * constructor
     * color player ro set mikone ( ya red ya blue -- bastegi be radiButton entekhabi player dare )
     */
    public Person(){
        color = "Red";
    }

    /**
     * name player ro moshakhas mikone - azash to jahaye mokhtalefi estefade khahim kard
     * @return name player -- person to in class
     */
    @Override
    public String toString() {
        return "Person";
    }


}
