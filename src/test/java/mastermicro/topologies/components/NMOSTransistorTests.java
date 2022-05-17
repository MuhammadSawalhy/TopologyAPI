package mastermicro.topologies.components;

import org.json.JSONObject;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class NMOSTransistorTests {
    NMOSTransistor nmos;

    @BeforeEach
    void setup() {
        var m_1 = new ComponentParameter(2,1,10);
        nmos = new NMOSTransistor("nmos-id", m_1);
    }

    @Test
    @DisplayName("should inherit from Component abstarct class")
    void testBaseClass() {
        assertEquals(Component.class, NMOSTransistor.class.getSuperclass());
    }

    @Test
    @DisplayName("should have m_1 parameter")
    void testParameter() {
        assertEquals(ComponentParameter.class, nmos.m_1.getClass());
    }

    @Test
    @DisplayName("should connectTerminal correctly when terminal is correctly chosen")
    void testConnectTerminal() throws UnrecognizedTerminalException {
        String node = "VCC";
        nmos.connectTerminal("gate", node);
        assertEquals(node, nmos.netlist.get("gate"));
        nmos.connectTerminal("drain", node);
        assertEquals(node, nmos.netlist.get("drain"));
        nmos.connectTerminal("source", node);
        assertEquals(node, nmos.netlist.get("source"));
    }

    @Test
    @DisplayName("isConnected to node should return true when it is connected")
    void testIsConnectToNode() throws UnrecognizedTerminalException {
        String node = "VCC";
        assertFalse(nmos.isConnectedToNode(node));
        nmos.connectTerminal("gate", node);
        assertTrue(nmos.isConnectedToNode(node));
        nmos.connectTerminal("drain", node);
        assertTrue(nmos.isConnectedToNode(node));
    }

    @Test
    @DisplayName("should throw when connecting terminal that doesn't exist")
    void testConnectWrongTerminal() {
        assertThrows(UnrecognizedTerminalException.class, () -> {
            nmos.connectTerminal("asdf", "VCC");
        }, "asdf");
    }

    @Test
    @DisplayName("Should disconnect whether or not the terminal is connected")
    void testDisconnectTerminal() throws UnrecognizedTerminalException {
        nmos.connectTerminal("gate", "VCC");
        nmos.disconnectTerminal("gate");
        assertEquals(null, nmos.netlist.get("gate"));
    }

    @Test
    @DisplayName("Should throw when disconnecting unrecognized terminal")
    void testDisconnectWrongTerminal() {
        assertThrows(UnrecognizedTerminalException.class, () -> {
            nmos.disconnectTerminal("asdf");
        }, "asdf");
    }

    @Test
    @DisplayName("toJSON should return with type=nmos")
    void testToJSONtype() {
        String type = (String)nmos.toJSON().get("type");
        assertEquals("nmos", type);
    }

    @Test
    @DisplayName("toJSON should return with id")
    void testToJSONid() {
        String id = (String)nmos.toJSON().get("id");
        assertEquals(nmos.id, id);
    }

    @Test
    @DisplayName("toJSON should return with m(1) parameter contains a 'default' property")
    void testToJSONM1Parameter() throws UnrecognizedTerminalException {
        nmos.m_1.setDefault(3);
        JSONObject m_1 = (JSONObject) nmos.toJSON().get("m(1)");
        assertEquals(3.0, m_1.get("default"));
    }

    @Test
    @DisplayName("toJSON should return with netlist with correct values")
    void testToJSONNetlist() throws UnrecognizedTerminalException {
        String node = "VCC";
        nmos.connectTerminal("gate", node);
        JSONObject netlist = (JSONObject) nmos.toJSON().get("netlist");
        assertEquals(node, netlist.get("gate"));
    }
}
