package mastermicro.topologies.components;

import org.json.JSONObject;
import java.util.HashMap;

public interface Component {
    public String id = null;
    public HashMap<String, String> netlist = new HashMap<>(2);

    public void connectTerminal(String terminal, String node);

    public void disconnectTerminal(String terminal);

    /**
     * Determine if this component is connected to a netlist node or not.
     * We need this for the requirement of get all components that are
     * connected to a specific netlist node.
     * @param node name of the node
     */
    public void isConnectedToNode(String node);

    /**
     * Serialize the component in a JSON object to which will item of array of components.
     * Later, all the topologies will be written in a .json file.
     * @return the json obejct that represent the component
     */
    public JSONObject toJSON();
}
