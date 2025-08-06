class Fighter {

    boolean isVulnerable() {
        return true;
    }

    int getDamagePoints(Fighter fighter) {
        return 1;
    }
}

class Warrior extends Fighter {

    @Override
    boolean isVulnerable() {
        return false;
    }

    @Override
    public String toString() {
        return "Fighter is a Warrior";
    }

    @Override
    int getDamagePoints(Fighter opponent) {
        return opponent.isVulnerable()? 10 : 6;
    }
}


class Wizard extends Fighter {
    @Override
    public String toString() {
        return "Fighter is a Wizard";
    }

    boolean hasSpellPrepared;

    void prepareSpell() {
        hasSpellPrepared = true;
    }

    @Override
    boolean isVulnerable() {
        return !hasSpellPrepared;
    }

    @Override
    int getDamagePoints(Fighter opponent) {
        return hasSpellPrepared ? 12 : 3;
    }
}
