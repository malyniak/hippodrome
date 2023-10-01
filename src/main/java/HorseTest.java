import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


 @RunWith(MockitoJUnitRunner.class)
public class HorseTest {
    public static final String MESSAGE_SECOND_ARGUMENT_NEGATIVE="Speed cannot be negative.";
    public static final String MESSAGE_THIRD_ARGUMENT_NEGATIVE="Distance cannot be negative.";
    @Mock
    private Horse horse;

    @Test
    public void isConstructorNullable() {
     when(horse.getName()==null).thenThrow(IllegalArgumentException.class);
     assertThrows(IllegalArgumentException.class, ()->
              new Horse(null, 0));
    }
    @Test
    public void testExceptionMessage() {
       Exception exception= assertThrows(IllegalArgumentException.class, ()-> {
                new Horse(null,0);
        });
       assertEquals(exception.getMessage(), ("Name cannot be null."));
    }

  @ParameterizedTest
  @ValueSource(strings = {"", " ", "\n", "\t", "\r", " \t\n\r"})
    public void testArgumentSpace(String arg) {
    assertThrows(IllegalArgumentException.class, ()->
            new Horse(arg, 0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\n", "\t", "\r", " \t\n\r"})
    public void testMessageIfArgumentSpace(String arg) {
        Exception exception=assertThrows(IllegalArgumentException.class, ()->
                new Horse(arg, 0));

        assertEquals(exception.getMessage(), "Name cannot be blank.");
    }

    @Test
    public void secondArgumentNegative () {
      when(horse.getSpeed()<0).thenThrow(IllegalArgumentException.class);
      assertThrows(IllegalArgumentException.class, ()->
        new Horse("sad", -2));
    }
    @Test
    public void messageIfSecondArgumentNegative () {
        when(horse.getSpeed()<0).thenThrow(new IllegalArgumentException(MESSAGE_SECOND_ARGUMENT_NEGATIVE));
        assertThrows(IllegalArgumentException.class, ()-> {
            new Horse("dsa", -2);
        }, MESSAGE_SECOND_ARGUMENT_NEGATIVE);
    }
    @Test
    public void thirdArgumentNegative () {
        when(horse.getDistance()<0).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class, ()->
                new Horse("sad", 1,-2));
    }
    @Test
    public void messageIfThirdArgumentNegative () {
        when(horse.getSpeed()<0).thenThrow(new IllegalArgumentException(MESSAGE_THIRD_ARGUMENT_NEGATIVE));
        assertThrows(IllegalArgumentException.class, ()-> {
            new Horse("dsa", 5, -2);
        }, MESSAGE_THIRD_ARGUMENT_NEGATIVE);
    }

}