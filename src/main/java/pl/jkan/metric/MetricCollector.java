package pl.jkan.metric;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;

import java.util.concurrent.TimeUnit;

public class MetricCollector {
    InfluxDB influxDB;
    private String dbName;

    public MetricCollector(InfluxDB influxDB, String dbName) {
        this.influxDB = influxDB;
        this.dbName = dbName;
    }

    public void handle(Metric metric) {
        influxDB.createDatabase(dbName);
        influxDB.setDatabase(dbName);
        influxDB.disableBatch();

        Point.Builder pointBuilder = Point.measurement(metric.getName())
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS);

        metric.getValues().entrySet()
                .stream()
                .forEach(entry -> pointBuilder.addField(entry.getKey(), entry.getValue()));

        influxDB.write(pointBuilder.build());
    }
}
