public enum Race {
    HOBBIT("Hobbit"),
    MAIA("Maia"),
    MAN("Man"),
    ELF("Elf")
    ;

    private final String name;

    Race(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}