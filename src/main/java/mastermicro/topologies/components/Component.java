package mastermicro.topologies.components;

import mastermicro.topologies.io.JSONSerializable;
import mastermicro.topologies.topology.Topology;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public abstract class Component implements JSONSerializable {
    public String id;
    public HashMap<String, String> netlist = new HashMap<>(2);

    public Component(String id) {
        this.id = id;
    }

    public void connectTerminal(String terminal, String node) throws UnrecognizedTerminalException {
        validateTerminal(terminal);
        netlist.put(terminal, node);
    };

    public void disconnectTerminal(String terminal) throws UnrecognizedTerminalException {
        validateTerminal(terminal);
        netlist.remove(terminal);
    };

    protected void validateTerminal(String terminal) throws UnrecognizedTerminalException {
        if (!getAvailableTerminals().contains(terminal))
            throw new UnrecognizedTerminalException(terminal);
    }

    public abstract Set<String> getAvailableTerminals();

    /**
     * Determine if this component is connected to a netlist node or not.
     * We need this for the requirement of get all components that are
     * connected to a specific netlist node.
     * @param node name of the node
     */
    public boolean isConnectedToNode(String node) {
        Collection<String> nodes = netlist.values();
        return nodes.contains(node);
    };

    public static Component fromJSON(String json) {
        JSONObject obj = new JSONObject(json);
        return fromJSON(obj);
    }

    public static Component fromJSON(JSONObject obj) {
        String type = (String) obj.get("type");
        String id = (String) obj.get("id");

        switch (type) {
            case "nmos":
                JSONObject mlJSON = (JSONObject) obj.get("m(l)");
                ComponentParameter ml = ComponentParameter.fromJSON(mlJSON);
                NMOSTransistor nmos = new NMOSTransistor(id, ml);
                return nmos;
            case "resistor":
                JSONObject resJSON = (JSONObject) obj.get("resistance");
                ComponentParameter res = ComponentParameter.fromJSON(resJSON);
                NMOSTransistor resistor = new NMOSTransistor(id, res);
                return resistor;
        }

        return null;
    }
}
