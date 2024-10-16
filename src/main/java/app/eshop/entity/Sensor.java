package app.eshop.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sensorName;

    private Long deviceId;



}
