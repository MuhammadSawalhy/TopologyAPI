package mastermicro.topologies;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class MainTests {
  @Test
  @DisplayName("Main.i should equals 0")
  void testStaticI() {
    assertEquals(0, Main.i);
  }
}
