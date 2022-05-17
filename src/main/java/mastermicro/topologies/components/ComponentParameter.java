package mastermicro.topologies.components;

import mastermicro.topologies.io.JSONSerializable;
import org.json.JSONObject;

public class ComponentParameter implements JSONSerializable {
    private double defaultValue;
    private double min;
    private double max;

    public ComponentParameter(double defaultValue, double min, double max) {
        // max has the most priotity, when values conflicts we will use the max value
        this.setMax(max);
        this.setMin(min);
        this.setDefault(defaultValue);
    }

    public double getDefault() { return defaultValue; }
    public double getMin() { return min; }
    public double getMax() { return max; }

    public void setDefault(double defaultValue) {
        if (defaultValue > max)
            defaultValue = max;
        if (defaultValue < min)
            defaultValue = min;
        this.defaultValue = defaultValue;
    }

    public void setMin(double min) {
        if (min > max)
            min = max;
        this.min = min;
        if (defaultValue < min)
            defaultValue = min;
    }

    public void setMax(double max) {
        this.max = max;
        if (defaultValue > max)
            defaultValue = max;
        if (min > max)
            min = max;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("default", defaultValue);
        obj.put("min", min);
        obj.put("max", max);
        return obj;
    }

    public static ComponentParameter fromJSON(String json) {
        JSONObject obj = new JSONObject(json);
        return fromJSON(obj);
    }

    public static ComponentParameter fromJSON(JSONObject obj) {
        return new ComponentParameter(
                obj.getDouble("default"),
                obj.getDouble("min"),
                obj.getDouble("max")
        );
    }
}
