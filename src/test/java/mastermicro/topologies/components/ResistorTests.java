package mastermicro.topologies.components;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import mastermicro.topologies.io.FileReader;
import mastermicro.topologies.io.InvalidJSONException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;

public class ResistorTests {
    Resistor resistor;
    String componentFile = getClass().getClassLoader().getResource("component-resistor.json").getFile();

    @BeforeEach
    void setup() {
        ComponentParameter r = new ComponentParameter(2,1,10);
        resistor = new Resistor("resistor-id", r);
    }

    @Test
    @DisplayName("should inherit from Component abstarct class")
    void testBaseClass() {
        assertEquals(Component.class, resistor.getClass().getSuperclass());
    }

    @Test
    @DisplayName("should have m_1 parameter")
    void testParameter() {
        assertEquals(ComponentParameter.class, resistor.resistance.getClass());
    }

    @Test
    @DisplayName("should connectTerminal correctly when terminal is correctly chosen")
    void testConnectTerminal() throws UnrecognizedTerminalException {
        String node = "VCC";
        resistor.connectTerminal("t1", node);
        assertEquals(node, resistor.netlist.get("t1"));
        node = "GND";
        resistor.connectTerminal("t2", node);
        assertEquals(node, resistor.netlist.get("t2"));
    }

    @Test
    @DisplayName("should throw when connecting terminal that doesn't exist")
    void testConnectWrongTerminal() {
        assertThrows(UnrecognizedTerminalException.class, () -> {
            resistor.connectTerminal("asdf", "VCC");
        }, "asdf");
    }

    @Test
    @DisplayName("Should disconnect whether or not the terminal is connected")
    void testDisconnectTerminal() throws UnrecognizedTerminalException {
        resistor.connectTerminal("t1", "VCC");
        resistor.disconnectTerminal("t1");
        assertEquals(null, resistor.netlist.get("t1"));
    }

    @Test
    @DisplayName("Should throw when disconnecting unrecognized terminal")
    void testDisconnectWrongTerminal() {
        assertThrows(UnrecognizedTerminalException.class, () -> {
            resistor.disconnectTerminal("asdf");
        }, "asdf");
    }

    @Test
    @DisplayName("toJSON should return with type=resistor")
    void testToJSONtype() {
        String type = (String) resistor.toJSON().get("type");
        assertEquals("resistor", type);
    }

    @Test
    @DisplayName("toJSON should return with id")
    void testToJSONid() {
        String id = (String)resistor.toJSON().get("id");
        assertEquals(resistor.id, id);
    }

    @Test
    @DisplayName("toJSON should return with resistance parameter contains a 'default' property")
    void testToJSONresistanceParameter() throws UnrecognizedTerminalException {
        resistor.resistance.setDefault(3);
        JSONObject resistance = (JSONObject) resistor.toJSON().get("resistance");
        assertEquals(3.0, resistance.get("default"));
    }

    @Test
    @DisplayName("toJSON should return with netlist with correct values")
    void testToJSONNetlist() throws UnrecognizedTerminalException {
        String node = "VCC";
        resistor.connectTerminal("t1", node);
        JSONObject netlist = (JSONObject) resistor.toJSON().get("netlist");
        assertEquals(node, netlist.get("t1"));
    }

    @Test
    @DisplayName("fromJSON should return value with correct components")
    void testFromJSON() throws FileNotFoundException {
        Resistor res = (Resistor) Component.fromJSON(FileReader.readFile(componentFile));
        assertEquals("res1", res.id);
        assertEquals(100, res.resistance.getDefault());
        assertEquals("vdd", res.netlist.get("t1"));
    }
}
