package mastermicro.topologies.components;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ComponentParameterTests {

    @Test
    @DisplayName("should have the same values which passed to the constructor")
    void testContructor() {
        ComponentParameter par = new ComponentParameter(2.5,2,3);
        assertEquals(2.5, par.getDefault());
        assertEquals(2, par.getMin());
        assertEquals(3, par.getMax());
    }


    @Test
    @DisplayName("setters and getters should work")
    void testSettersAndGetter() {
        ComponentParameter par = new ComponentParameter(2.5,2,3);
        par.setDefault(2.3);
        assertEquals(2.3, par.getDefault());
        par.setMin(2.3);
        assertEquals(2.3, par.getMin());
        par.setMax(2.3);
        assertEquals(2.3, par.getMax());
    }

    @Test
    @DisplayName("defaultValue should always be greater than min")
    void testDefaultGreaterThanMin() {
        ComponentParameter par = new ComponentParameter(1,2,3);
        assertEquals(2, par.getDefault());
        par.setDefault(0);
        assertEquals(2, par.getDefault());
        par.setMin(2.5);
        assertEquals(2.5, par.getDefault());
    }

    @Test
    @DisplayName("defaultValue should always be less than max")
    void testDefaultLessThanMax() {
        ComponentParameter par = new ComponentParameter(5,2,3);
        assertEquals(3, par.getDefault());
        par.setDefault(12);
        assertEquals(3, par.getDefault());
        par.setMax(2.5);
        assertEquals(2.5, par.getDefault());
    }

    @Test
    @DisplayName("min should always be less than max")
    void testMinIsLessThanMax() {
        ComponentParameter par = new ComponentParameter(2.5,2,3);
        assertEquals(2, par.getMin());
        assertEquals(3, par.getMax());
        par.setMax(1);
        assertEquals(1, par.getDefault());
        assertEquals(1, par.getMax());
        assertEquals(1, par.getMin());
    }
}