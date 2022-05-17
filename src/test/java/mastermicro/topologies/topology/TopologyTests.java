package mastermicro.topologies.topology;

import mastermicro.topologies.components.*;
import mastermicro.topologies.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TopologyTests {
    Topology top;
    String topologyFile = getClass().getClassLoader().getResource("topology.json").getFile();

    @BeforeEach
    void setup() {
        top = new Topology("top1");

        NMOSTransistor nmos = new NMOSTransistor(
                "nmos1",
                new ComponentParameter(2, 1, 10));
        top.components.add(nmos);

        Resistor res = new Resistor(
                "res1",
                new ComponentParameter(10, 1, 100));
        top.components.add(res);
    }

    @Test
    @DisplayName("should get connected components to a node")
    void testComponentsAreConstant() throws UnrecognizedTerminalException {
        NMOSTransistor nmos = (NMOSTransistor) top.components.get(0);
        nmos.connectTerminal("drain", "VCC");

        List<Component> coms = top.getComponentsConnectToNode("VCC");
        assertSame(nmos, coms.get(0));

        Resistor res = (Resistor) top.components.get(1);
        res.connectTerminal("t1", "VCC");

        coms = top.getComponentsConnectToNode("VCC");
        assertSame(nmos, coms.get(0));
        assertSame(res, coms.get(1));
        assertEquals(2, coms.size());
    }

    @Test
    @DisplayName("toJSON should return value with id")
    void testToJSONid() {
        assertEquals(top.id, top.toJSON().getString("id"));
    }

    @Test
    @DisplayName("toJSON should return value with components")
    void testToJSONComponents() {
        JSONArray comps = top.toJSON().getJSONArray("components");
        JSONObject res = comps.getJSONObject(1);
        assertEquals(top.components.get(1).id, res.getString("id"));
    }

    @Test
    @DisplayName("fromJSON should return value with correct components")
    void testFromJSON() throws FileNotFoundException {
        Topology top = Topology.fromJSON(FileReader.readFile(topologyFile));
        assertEquals("top1", top.id);
        assertEquals(2, top.components.size());
    }
}
