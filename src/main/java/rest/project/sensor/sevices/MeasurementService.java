package rest.project.sensor.sevices;

import org.knowm.xchart.XYChart;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import rest.project.sensor.models.Measurement;
import rest.project.sensor.models.MeasurementDTO;
import rest.project.sensor.models.Sensor;
import rest.project.sensor.util.ResponseMessage;

import java.util.Date;
import java.util.List;
import java.util.Random;

import static rest.project.sensor.visualisation.XChartGraphs.getGraph;
import static rest.project.sensor.visualisation.XChartGraphs.printGraph;

public class MeasurementService {
    public void addRandomMeasurements(RestTemplate restTemplate, Sensor sensor, int count) {
        Random random = new Random();
        // Adding measurements
        for (int i = 0; i < count; i++) {
            int rand_value = -100 + random.nextInt(201);
            MeasurementDTO measurement = new MeasurementDTO(rand_value, random.nextBoolean(), sensor);
            HttpEntity<MeasurementDTO> request = new HttpEntity<>(measurement);
            String url = "http://localhost:8080/measurements/add";
            ResponseMessage responseMessage = restTemplate.postForObject(url, request, ResponseMessage.class);
            System.out.println(responseMessage);
        }
    }

    public List<Measurement> getMeasurements(RestTemplate restTemplate) {
        // Getting measurements
        String url = "http://localhost:8080/measurements";
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Measurement>>() {
        }).getBody();
    }

    public void showMeasurementsTempsGraph(List<Measurement> measurements, int indexStart, int indexEnd) {
        measurements.sort((o1, o2) -> o1.getMeasured().after(o2.getMeasured()) ? 1 : -1);
        int[] xAxis = measurements.subList(indexStart, indexEnd).stream().map(Measurement::getMeasured).map(Date::getTime).mapToInt(x -> Math.toIntExact(x % 1000000)).toArray();
        int[] yAxis = measurements.subList(indexStart, indexEnd).stream().map(Measurement::getValue).toList().stream().mapToInt(x -> x).toArray();

        XYChart chart = getGraph("Date", "Temperature", -100, 100);
        printGraph(chart, "temp", xAxis, yAxis);
    }

    public void showMeasurementsTempsGraph(List<Measurement> measurements) {
        showMeasurementsTempsGraph(measurements, 0, measurements.size());
    }
}
