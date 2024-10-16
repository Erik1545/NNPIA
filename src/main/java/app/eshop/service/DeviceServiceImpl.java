package app.eshop.service;
import app.eshop.controller.NotFoundException;
import app.eshop.dto.DeviceDTO;
import app.eshop.dto.SensorDTO;
import app.eshop.entity.Device;
import app.eshop.entity.Sensor;
import app.eshop.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;


@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;




    @Override
    public List<Device> getAllDevicesById(Long userId){
        return deviceRepository.findAllDevicesByUserId(userId);
    }

    @Override
    public Device getDeviceById(Long id) {
        return deviceRepository.findById(id).orElse(null);
    }

    @Override
    public Device createDevice(DeviceDTO deviceDTO) {
        Device device = new Device();
        device.setDeviceName(deviceDTO.getDeviceName());
        device.setUserId(deviceDTO.getUserId());
        device.setDescription(deviceDTO.getDescription());
        return deviceRepository.save(device);
    }

    @Override
    public Device updateDevice(Long id, DeviceDTO deviceDTO) {
        Device device = new Device();
        device.setId(id);
        device.setDeviceName(deviceDTO.getDeviceName());
        device.setDescription(deviceDTO.getDescription());
        return deviceRepository.save(device);
    }

    @Override
    public void deleteDevice(Long id) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Device %s not found", id)));
        deviceRepository.delete(device);
    }
}
