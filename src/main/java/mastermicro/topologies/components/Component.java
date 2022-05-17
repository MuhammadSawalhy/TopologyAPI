package mastermicro.topologies.components;

import mastermicro.topologies.io.JSONSerializable;

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
}
