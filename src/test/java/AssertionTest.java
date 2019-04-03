import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class AssertionTest {

    private final TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, Race.HOBBIT);
    private final TolkienCharacter sam = new TolkienCharacter("Sam", 38, Race.HOBBIT);
    private final TolkienCharacter legolas = new TolkienCharacter("Legolas", 1000, Race.ELF);
    private final TolkienCharacter aragorn = new TolkienCharacter("Aragorn", 87, Race.MAN);
    private final TolkienCharacter boromir = new TolkienCharacter("Boromir", 37, Race.MAN);
    private final TolkienCharacter sauron = new TolkienCharacter("Sauron", 50000, Race.MAIA);

    private List<TolkienCharacter> fellowshipOfTheRing;

    @Before
    public void before() {
        fellowshipOfTheRing = Arrays.asList(frodo, sam, legolas, aragorn, boromir);
    }


    @Test
    public void test1() {

        assertThat(frodo.getName()).isEqualTo("Frodo");
        assertThat(frodo).isNotEqualTo(sauron);
    }

    @Test
    public void test2() {

        assertThat(frodo.getName()).startsWith("Fro")
                .endsWith("do")
                .isEqualToIgnoringCase("frodo");
    }

    @Test
    public void test3() {

        assertThat(fellowshipOfTheRing).hasSize(5)
                .contains(frodo, sam)
                .doesNotContain(sauron);
    }

    @Test
    public void test4() {

        assertThat(frodo.getAge()).as("check %s's age", frodo.getName()).isEqualTo(33);
    }

    @Test
    public void test5() {

        assertThatThrownBy(() -> {
            throw new Exception("boom!");
        }).hasMessage("boom!");


        Throwable thrown = catchThrowable(() -> {
            throw new Exception("boom!");
        });

        assertThat(thrown).hasMessageContaining("boom");

    }

    @Test
    public void test6() {

        assertThat(fellowshipOfTheRing).extracting(TolkienCharacter::getName)
                .doesNotContain("Sauron", "Elrond");


        assertThat(fellowshipOfTheRing).filteredOn(character -> character.getName().contains("o"))
                .containsOnly(aragorn, frodo, legolas, boromir);

        assertThat(fellowshipOfTheRing).filteredOn(character -> character.getName().contains("o"))
                .containsOnly(aragorn, frodo, legolas, boromir)
                .extracting(character -> character.getRace().getName())
                .contains("Hobbit", "Elf", "Man");
    }
}
