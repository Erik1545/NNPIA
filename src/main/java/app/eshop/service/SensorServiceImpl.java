package app.eshop.service;
import app.eshop.controller.NotFoundException;
import app.eshop.dto.SensorDTO;
import app.eshop.entity.Device;
import app.eshop.entity.Sensor;
import app.eshop.repository.SensorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;
import java.util.List;


@Service
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;

    public SensorServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public void check(String username) {
        Sensor sensor = new Sensor();
        sensor.setSensorName(username);
        sensorRepository.save(sensor);

    }



    @Override
    public List<Sensor> getAllSensorsById(Long deviceId){
        return sensorRepository.findAllByDeviceId(deviceId);
    }

    @Override
    public Sensor createSensor(SensorDTO sensorDTO) {
        Sensor sensor = new Sensor();
        sensor.setSensorName(sensorDTO.getSensorName());
        sensor.setDeviceId(sensorDTO.getDeviceId());
        return sensorRepository.save(sensor);
    }

    @Override
    public Sensor updateSensor(Long id, SensorDTO sensorDTO) {
        Sensor sensor = new Sensor();
        sensor.setId(id);
        sensor.setSensorName(sensorDTO.getSensorName());
        return sensorRepository.save(sensor);
    }

    @Override
    public void deleteSensor(Long id) {
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Sensor %s not found", id)));
        sensorRepository.delete(sensor);
    }
}
