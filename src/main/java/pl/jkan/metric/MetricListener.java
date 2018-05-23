package pl.jkan.metric;

import pl.jkan.ecommerce.sales.domain.basket.ItemAdded;
import pl.jkan.ecommerce.system.Subscriber;

import java.util.HashMap;

public class MetricListener implements Subscriber<ItemAdded> {
    private MetricCollector metricCollector;

    public MetricListener(MetricCollector metricCollector) {
        this.metricCollector = metricCollector;
    }

    @Override
    public void handle(ItemAdded event) {
        HashMap<String, String> values = new HashMap<>();
        values.put("value", "1");
        metricCollector.handle(new Metric("item_added_to_basket", values));
    }
}
