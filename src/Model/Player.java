package Model;

/**
 *  Player class
 */
public class Player {
    private String color,name;
    int turn=0;

    /**
     * <b>Constructor</b>
     * Creates a player and set color and name for him
     * @param color
     * @param name
     */
    public Player(String color,String name){
        this.color=color;
        this.name=name;
    }

    /**
     * <b>Transformer</b>Sets  player's name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <b>Accessor</b>Gets player's name
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * <b>Accessor</b>Gets player's color
     * @return color
     */
    public String getColor() {
        return color;
    }


}
