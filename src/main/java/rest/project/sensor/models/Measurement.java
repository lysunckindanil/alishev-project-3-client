package rest.project.sensor.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Measurement {

    private Integer value;

    private boolean raining;

    private Sensor sensor;
}
