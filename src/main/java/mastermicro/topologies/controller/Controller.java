package mastermicro.topologies.controller;

import mastermicro.topologies.topology.Topology;
import mastermicro.topologies.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    private ArrayList<Topology> topologies = new ArrayList<>();

    /**
     * Get the the topologies that exists. Not to be confused by the
     * topologies stored in files, I mean the topologies in the memory.
     * 
     * @return array list of the topologies
     */
    public ArrayList<Topology> getTopologies() {
        return topologies;
    }

    /*
     * Search in the current loaded and stored topologies in the memory gived
     * the id (identifier) of the topology.
     * 
     * @param topologyId the topology identifier.
     */
    private Topology getTopology(String topologyId) {
        for (int i = 0; i < topologies.size(); i++)
            if (topologies.get(i).id.equals(topologyId))
                return topologies.get(i);
        return null;
    }

    /*
     * Delete a topology from the memory by its id (identifier). If no
     * topology exists with this id it won't do anything. Not to be confused by the
     * topologies stored in files, I mean the topologies in the memory.
     *
     * @param id the topology identifier.
     */
    public void deleteTopology(String id) {
        for (int i = 0; i < topologies.size(); i++)
            if (topologies.get(i).id == id)
                topologies.remove(i);
    }

    /**
     * Read a topology from a json file, and store it in the memory.
     *
     * @param jsonFilePath a relative or full path to the json file.
     */
    public void readTopologyFromFile(String jsonFilePath) throws FileNotFoundException {
        Topology top = Topology.fromJSON(FileReader.readFile(jsonFilePath));
        topologies.add(top);
    }

    /**
     * Write a topology (by its id) to a json file.
     *
     * @param topologyId   the topology id.
     * @param jsonFilePath a relative or full path to the json file.
     */
    public void writeTopologyToFile(String topologyId, String jsonFilePath) throws IOException {
        Topology top = getTopology(topologyId);
        FileWriter writer = new FileWriter(jsonFilePath);
        writer.write(top.toJSON().toString(2));
        writer.close();
    }
}
