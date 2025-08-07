package monedas.api.aplicacion.servicios;

import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.cloudwatch.model.Dimension;
import software.amazon.awssdk.services.cloudwatch.model.MetricDatum;
import software.amazon.awssdk.services.cloudwatch.model.PutMetricDataRequest;
import software.amazon.awssdk.services.cloudwatch.model.StandardUnit;

public class AWSServicio {

    public static void publicarMetrica(String nombreMetrica, double valor) {
        CloudWatchClient cloudWatchClient = CloudWatchClient.builder().build();

        Dimension dimension = Dimension.builder()
                .name("ServiceName")
                .value("api-monedas")
                .build();

        MetricDatum datum = MetricDatum.builder()
                .metricName(nombreMetrica)
                .unit(StandardUnit.COUNT)
                .value(valor)
                .dimensions(dimension)
                .build();

        PutMetricDataRequest request = PutMetricDataRequest.builder()
                .namespace("api-monedas-metrica-personalizada") // Tu propio espacio de nombres
                .metricData(datum)
                .build();

        cloudWatchClient.putMetricData(request);
        cloudWatchClient.close();
    }

}
