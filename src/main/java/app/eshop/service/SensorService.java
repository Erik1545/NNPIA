package app.eshop.service;
import app.eshop.dto.SensorDTO;
import app.eshop.entity.Sensor;
import java.util.List;

public interface SensorService {
    void check(String username);

    List<Sensor> getAllSensorsById(Long deviceId);

    Sensor createSensor(SensorDTO sensorDTO);

    Sensor updateSensor(Long id, SensorDTO sensorDTO);

    void deleteSensor(Long id);
}
