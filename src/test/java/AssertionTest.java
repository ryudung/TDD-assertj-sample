import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class AssertionTest {

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

        }).hasMessage("boom!");


        Throwable thrown = catchThrowable();

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
