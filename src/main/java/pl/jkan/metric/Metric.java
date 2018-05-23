package pl.jkan.metric;

import java.util.HashMap;

public class Metric {
    private final String name;
    private final HashMap<String, String> values;

    public Metric(String name, HashMap<String, String> values) {
        this.name = name;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, String> getValues() {
        return values;
    }
}
