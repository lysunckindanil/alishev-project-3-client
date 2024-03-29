package rest.project.sensor.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Measurement {

    private Integer value;

    private Boolean raining;

    private Date measured;

    private Sensor sensor;
}
