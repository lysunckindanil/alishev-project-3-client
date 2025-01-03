package rest.project.sensor;


import org.springframework.web.client.RestTemplate;
import rest.project.sensor.models.Measurement;
import rest.project.sensor.sevices.MeasurementService;

import java.util.List;

public class SensorApplication {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        MeasurementService measurementService = new MeasurementService();
//        measurementService.addRandomMeasurements(restTemplate, SensorService.getTestSensor(), 100);
        List<Measurement> measurementList = measurementService.getMeasurements(restTemplate);
        measurementService.showMeasurementsTempsGraph(measurementList, 0, 100);
    }

}
