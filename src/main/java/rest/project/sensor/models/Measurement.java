package rest.project.sensor.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Measurement {

    private Integer value;

    private Boolean raining;

    private Sensor sensor;
}
