package mastermicro.topologies.components;

import org.json.JSONObject;

import java.util.Collection;
import java.util.Set;

public class NMOSTransistor extends Component {
    public ComponentParameter m_1;
    private Set<String> availableTerminals = Set.of(
        "gate","drain","source"
    );

    public NMOSTransistor(String id, ComponentParameter m_1) {
        super(id);
        this.m_1 = m_1;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("type", "nmos");
        obj.put("m(1)", m_1.toJSON());
        obj.put("netlist", new JSONObject(netlist));
        return obj;
    }

    @Override
    public Set<String> getAvailableTerminals() {
        return availableTerminals;
    }
}
