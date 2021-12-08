package internal.domain.entity;

public class Status {
    private int hitPoint;
    private int strenth;
    // private int defense;
    // private int luck;

    public Status(
            int hitPoint,
            int strength
    // int defense,
    // int luck
    ) {
        this.hitPoint = hitPoint;
        this.strenth = strength;
    }

    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    public int getHitPoint() {
        return this.hitPoint;
    }

    public int getStrength() {
        return this.strenth;
    }

    public void setStrength(int strength) {
        this.strenth = strength;
    }
}
