package mcm.edu.ph.MorphTech_turnbasedgame;

public class Hero extends GameUnit {

    int exp;
    double statSTR;
    double statAGI;
    double statINT;

    public Hero(){}
    public Hero(String unitName, int baseHealth, int baseMana, int minDPT, int maxDPT, double statSTR, double statAGI, double statINT){

        setUnitName(unitName);
        setBasehealth(baseHealth);
        setBaseMana(baseMana);
        setMinDPT( minDPT);
        setMaxDPT( maxDPT);

        this.statSTR = statSTR;
        this.statAGI = statAGI;
        this.statINT = statINT;

        setLevel(1);
        setBaseArmor(0);

        this.exp = 0;

    }

}

