package mastermicro.topologies.controller;

import mastermicro.topologies.topology.Topology;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
        StringBuilder json = new StringBuilder();
        try (Scanner input = new Scanner(new File(jsonFilePath))) {
            while (input.hasNextLine())
                json.append(input.nextLine()).append("\n");
        }

        Topology top = Topology.fromJSON(json.toString());
        topologies.add(top);
    }

    public void writeTopologyToFile(String topologyId, String jsonFilePath) throws IOException {
        Topology top = getTopology(topologyId);
        FileWriter writer = new FileWriter(jsonFilePath);
        writer.write(top.toJSON().toString(2));
        writer.close();
    }
}
