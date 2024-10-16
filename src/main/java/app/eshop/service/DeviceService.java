package app.eshop.service;
import app.eshop.controller.NotFoundException;
import app.eshop.dto.DeviceDTO;
import app.eshop.entity.Device;
import app.eshop.entity.Sensor;

import java.util.List;

public interface DeviceService {
    List<Device> getAllDevicesById(Long userId);

    Device getDeviceById(Long id);

    Device createDevice(DeviceDTO deviceDTO);

    Device updateDevice(Long id, DeviceDTO deviceDTO);

    void deleteDevice(Long id);

}
