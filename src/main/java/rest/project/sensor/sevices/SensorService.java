package rest.project.sensor.sevices;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import rest.project.sensor.models.Sensor;
import rest.project.sensor.util.ResponseMessage;

public class SensorService {
    public void registerSensor(RestTemplate restTemplate, Sensor sensor) {
        // Sensor's registration
        HttpEntity<Sensor> request_sensor = new HttpEntity<>(sensor);
        String url_sensor = "http://localhost:8080/sensors/registration";
        ResponseMessage responseMessageRegistration = restTemplate.postForObject(url_sensor, request_sensor, ResponseMessage.class);
        System.out.println(responseMessageRegistration);
    }

    public static Sensor getTestSensor() {
        return new Sensor("test sensor");
    }

}
