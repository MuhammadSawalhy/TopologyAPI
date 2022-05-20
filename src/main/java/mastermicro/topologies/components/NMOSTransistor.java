package mastermicro.topologies.components;

import org.json.JSONObject;
import java.util.Set;

public class NMOSTransistor extends Component {
    public ComponentParameter m_l;
    private Set<String> availableTerminals = Set.of(
        "gate","drain","source"
    );

    public NMOSTransistor(String id, ComponentParameter m_l) {
        super(id);
        this.m_l = m_l;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("id", id);
        obj.put("type", "nmos");
        obj.put("m(l)", m_l.toJSON());
        obj.put("netlist", new JSONObject(netlist));
        return obj;
    }

    @Override
    public Set<String> getAvailableTerminals() {
        return availableTerminals;
    }
}
