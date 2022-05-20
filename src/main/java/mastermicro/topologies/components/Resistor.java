package mastermicro.topologies.components;

import org.json.JSONObject;
import java.util.Set;

public class Resistor extends Component {
    public ComponentParameter resistance;
    private Set<String> availableTerminals = Set.of(
        "t1", "t2"
    );

    public Resistor(String id, ComponentParameter resistance) {
        super(id);
        this.resistance = resistance;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("id", id);
        obj.put("type", "resistor");
        obj.put("resistance", resistance.toJSON());
        obj.put("netlist", new JSONObject(netlist));
        return obj;
    }

    @Override
    public Set<String> getAvailableTerminals() {
        return availableTerminals;
    }
}
