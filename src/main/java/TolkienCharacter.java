public class TolkienCharacter {

    private int age;
    private String name;
    private Race race;

    public TolkienCharacter(String name, int age, Race race) {
        this.name = name;
        this.age = age;
        this.race = race;
    }

    public int getAge() {
        return age;
    }

    public Race getRace() {
        return race;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "TolkienCharacter{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", race=" + race +
                '}';
    }
}
