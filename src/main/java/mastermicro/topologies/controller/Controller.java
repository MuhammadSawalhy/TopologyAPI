package mastermicro.topologies.controller;

import mastermicro.topologies.topology.Topology;
import mastermicro.topologies.io.FileReader;
import mastermicro.topologies.io.InvalidJSONException;

import org.jetbrains.annotations.Nullable;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    private ArrayList<Topology> topologies = new ArrayList<>();

    public ArrayList<Topology> getTopologies() {
        return topologies;
    }

    private @Nullable Topology getTopology(String topologyId) {
        for (int i = 0; i < topologies.size(); i++)
            if (topologies.get(i).id.equals(topologyId))
                return topologies.get(i);
        return null;
    }

    public void deleteTopology(String id) {
        for (int i = 0; i < topologies.size(); i++)
            if (topologies.get(i).id == id)
                topologies.remove(i);
    }

    public void readTopologyFromFile(String jsonFilePath) throws FileNotFoundException {
        Topology top = Topology.fromJSON(FileReader.readFile(jsonFilePath));
        topologies.add(top);
    }

    public void writeTopologyToFile(String topologyId, String jsonFilePath) throws IOException {
        Topology top = getTopology(topologyId);
        FileWriter writer = new FileWriter(jsonFilePath);
        writer.write(top.toJSON().toString(2));
        writer.close();
    }
}
