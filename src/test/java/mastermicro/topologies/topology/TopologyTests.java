package mastermicro.topologies.topology;

import mastermicro.topologies.components.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TopologyTests {
    Topology top;

    @BeforeEach
    void setup() {
        top = new Topology("top1");

        NMOSTransistor nmos = new NMOSTransistor(
                "nmos1",
                new ComponentParameter(2,1,10)
        );
        top.components.add(nmos);

        Resistor res = new Resistor(
                "res1",
                new ComponentParameter(10, 1, 100)
        );
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

}
