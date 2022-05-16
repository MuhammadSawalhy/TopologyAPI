package mastermicro.topologies.components;

public class ComponentParameter {
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
}
