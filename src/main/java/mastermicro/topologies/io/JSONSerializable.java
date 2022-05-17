package mastermicro.topologies.io;

import org.json.JSONObject;

public interface JSONSerializable {
    /**
     * Serialize the java object to a JSON.
     * Later, all the topologies will be written in a .json file.
     * @return the json obejct that represent the java object
     */
    public JSONObject toJSON();
}
