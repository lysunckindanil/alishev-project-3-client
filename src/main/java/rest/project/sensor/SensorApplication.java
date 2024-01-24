package rest.project.sensor;


import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import rest.project.sensor.models.Measurement;
import rest.project.sensor.models.Sensor;
import rest.project.sensor.util.ResponseMessage;

import java.util.List;
import java.util.Random;

public class SensorApplication {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
    }

    private static void addMeasurements(RestTemplate restTemplate) {
        Random random = new Random();
        // Adding measurements
        for (int i = 0; i < 1000; i++) {
            int rand_value = -100 + random.nextInt(201);
            Measurement measurement = new Measurement(rand_value, random.nextBoolean(), getSensor());
            HttpEntity<Measurement> request = new HttpEntity<>(measurement);
            String url = "http://localhost:8080/measurements/add";
            ResponseMessage responseMessage = restTemplate.postForObject(url, request, ResponseMessage.class);
            System.out.println(responseMessage);
        }
    }

    private static List<Measurement> getMeasurements(RestTemplate restTemplate) {
        // Getting measurements
        String url = "http://localhost:8080/measurements";
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Measurement>>() {
        }).getBody();
    }

    private static void registerSensor(RestTemplate restTemplate) {
        // Sensor's registration
        HttpEntity<Sensor> request_sensor = new HttpEntity<>(getSensor());
        String url_sensor = "http://localhost:8080/sensors/registration";
        ResponseMessage responseMessageRegistration = restTemplate.postForObject(url_sensor, request_sensor, ResponseMessage.class);
        System.out.println(responseMessageRegistration);
    }

    private static Sensor getSensor() {
        return new Sensor("test sensor");
    }

}
