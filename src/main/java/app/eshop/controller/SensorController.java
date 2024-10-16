package app.eshop.controller;
import app.eshop.dto.DeviceDTO;
import app.eshop.dto.SensorDTO;
import app.eshop.entity.Device;
import app.eshop.entity.Sensor;
import app.eshop.security.JWTUtilities;
import app.eshop.service.SensorService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/devices/{deviceId}/sensors")
public class SensorController {

    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }
    @GetMapping
    public List<Sensor> getAllSensorsById(@PathVariable Long deviceId) {
        return sensorService.getAllSensorsById(deviceId);
    }

    @PostMapping
    public Sensor createSensor(@PathVariable Long deviceId, @RequestParam("sensorName") @NotNull String sensorName) {
        SensorDTO sensorDTO = new SensorDTO();
        sensorDTO.setSensorName(sensorName);
        sensorDTO.setDeviceId(deviceId);
        return sensorService.createSensor(sensorDTO);
    }

    @PutMapping("/{id}")
    public Sensor updateSensor(@PathVariable Long id,
                                @RequestParam("sensorName") @NotNull String sensorName){
        SensorDTO sensorDTO = new SensorDTO();
        sensorDTO.setSensorName(sensorName);
        return sensorService.updateSensor(id, sensorDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        sensorService.deleteSensor(id);
    }

}
