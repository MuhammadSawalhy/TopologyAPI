package mastermicro.topologies.io;

import org.json.JSONObject;

public interface JSONSerializable {
    /**
     * Serialize the java Object to a JSONObject type which will be converted to JSON string
     * with `JSONObject.toString(int indent)`.
     *
     * @return the json obejct that represent the java object.
     */
    public JSONObject toJSON();
}
