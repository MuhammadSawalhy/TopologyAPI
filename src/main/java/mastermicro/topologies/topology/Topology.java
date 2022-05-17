package mastermicro.topologies.topology;

import mastermicro.topologies.components.Component;
import mastermicro.topologies.io.JSONSerializable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Topology implements JSONSerializable {
    public String id;
    public final ArrayList<Component> components = new ArrayList<>();

    public Topology(String id) {
        this.id = id;
    }

    /**
     * Get all the connected components to a specific node in the netlist.
     * @param node the id of the node to search with
     * @return list of all the components
     */
    public List<Component> getComponentsConnectToNode(String node) {
        ArrayList<Component> connectedComponents = new ArrayList<>(3);
        for (Component c : components)
            if (c.isConnectedToNode(node))
                connectedComponents.add(c);
        return connectedComponents;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("id", id);

        JSONArray comps = new JSONArray();
        for (Component c : components)
        comps.put(c.toJSON());

        obj.put("components", comps);
        return obj;
    }
}
