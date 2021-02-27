package mcm.edu.ph.MorphTech_turnbasedgame;

public class GameUnit {

    private int level;
    private String unitName;
    private int basehealth;
    private int baseMana;
    private int baseArmor;
    private int minDPT;
    private int maxDPT;

    public GameUnit() {}

    public GameUnit(String unitName, int baseMana, int basehealth, int minDPT, int maxDPT) {
        this.unitName = unitName;
        this.basehealth = basehealth;
        this.baseMana = baseMana;
        this.minDPT = minDPT;
        this.maxDPT = maxDPT;
        level = 0;
        baseArmor = 0;

    }

    public int getLevel() {
        return this.level;
    }

    public String getUnitName() {
        return unitName;
    }

    public int getbaseHealth() {
        return basehealth;
    }

    public int getubaseMana() {
        return baseMana;
    }

    public int getBaseArmor() {
        return baseArmor;
    }

    public int getMinDPT() {
        return minDPT;
    }

    public int getMaxDPT() {
        return maxDPT;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public void setBasehealth(int basehealth) {
        this.basehealth = basehealth;
    }

    public void setBaseMana(int baseMana) {
        this.baseMana = baseMana;
    }

    public void setBaseArmor(int baseArmor) {
        this.baseArmor = baseArmor;
    }

    public void setMinDPT(int minDPT) {
        this.minDPT = minDPT;
    }

    public void setMaxDPT(int maxDPT) {
        this.maxDPT = maxDPT;
    }
}
