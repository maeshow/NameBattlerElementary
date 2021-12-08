package internal.domain.entity;

public class Player {
    private final int id;
    private final String name;
    private int hitPoint;
    // private int strenth;
    // private int defense;
    // private int luck;

    public Player(
            int id,
            String name,
            int hitPoint
    // int strength,
    // int defense,
    // int luck
    ) {
        this.id = id;
        this.name = name;
        this.hitPoint = hitPoint;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    public int getHitPoint() {
        return this.hitPoint;
    }
}
