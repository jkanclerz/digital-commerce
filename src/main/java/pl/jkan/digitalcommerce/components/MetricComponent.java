package pl.jkan.digitalcommerce.components;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.jkan.ecommerce.canonicalmodel.Identifier;
import pl.jkan.ecommerce.sales.domain.basket.ItemAdded;
import pl.jkan.ecommerce.system.EventBus;
import pl.jkan.ecommerce.system.SystemUserContext;
import pl.jkan.ecommerce.system.infrastructure.InMemorySystemUserContext;
import pl.jkan.ecommerce.system.infrastructure.InternalEventBus;
import pl.jkan.metric.MetricCollector;
import pl.jkan.metric.MetricListener;

@Component
public class MetricComponent {

    @Autowired
    private EventBus eventBus;


    private MetricCollector metricCollector() {
        InfluxDB influxDB = InfluxDBFactory.connect(System.getenv("METRIC_DB_ADDRESS"));
        return new MetricCollector(influxDB, System.getenv("METRIC_DB_NAME"));
    }

    @Bean
    private MetricListener metricListener() {
        MetricListener metricListener = new MetricListener(metricCollector());

        eventBus.addEventListener(ItemAdded.class, metricListener);

        return metricListener;
    }
}


