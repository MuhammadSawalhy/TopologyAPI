package mastermicro.topologies.topology;

import mastermicro.topologies.components.Component;
import mastermicro.topologies.io.InvalidJSONException;
import mastermicro.topologies.io.JSONSerializable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
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

    public static Topology fromJSON(String json) {
        JSONObject obj = new JSONObject(json);
        return fromJSON(obj);
    }

    public static Topology fromJSON(JSONObject obj) {
        JSONArray components = obj.getJSONArray("components");

        String id = (String) obj.get("id");
        Topology top = new Topology(id);

        for (int i = 0; i < components.toList().size(); i++) {
            JSONObject compJSON = (JSONObject) components.get(i);
            Component comp = Component.fromJSON(compJSON);
            top.components.add(comp);
        }

        return top;
    }
}
