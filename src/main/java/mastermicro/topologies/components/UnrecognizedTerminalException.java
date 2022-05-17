package mastermicro.topologies.components;

public class UnrecognizedTerminalException extends Exception {
    public UnrecognizedTerminalException(String terminal) {
        super(String.format("Trying to access unrecognized terminal %s for a component", terminal));
    }
}
