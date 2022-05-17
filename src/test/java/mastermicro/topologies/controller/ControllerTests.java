package mastermicro.topologies.controller;

import com.sun.security.auth.module.UnixSystem;
import mastermicro.topologies.topology.Topology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ControllerTests {
    Controller controller;
    String topologyFile = getClass().getClassLoader().getResource("topology.json").getFile();
    String topologyFile_2 = new File(new File(topologyFile).getParent(), "topology-2.json").getAbsolutePath();

    @BeforeEach
    void setup() {
        controller = new Controller();
    }

    @Test
    @DisplayName("should read topology from a .json file")
    void testReadTopologyFromFile() throws FileNotFoundException {
        controller.readTopologyFromFile(topologyFile);
        Topology top = controller.getTopologies().get(0);
        assertEquals("top1", top.id);
        assertEquals("res1", top.components.get(0).id);
    }

    @Test
    @DisplayName("should write a topology to a json file")
    void testWriteTopology() throws IOException {
        controller.readTopologyFromFile(topologyFile);
        controller.writeTopologyToFile("top1", topologyFile_2);
        controller.readTopologyFromFile(topologyFile_2);
        assertEquals(2, controller.getTopologies().size());
    }

    @Test
    @DisplayName("should delete  topology using the id")
    void testDeleteTopology() throws FileNotFoundException {
        controller.readTopologyFromFile(topologyFile);
        controller.getTopologies().get(0).id = "new-id";
        controller.deleteTopology("new-id");
        assertEquals(0, controller.getTopologies().size());
    }
}
