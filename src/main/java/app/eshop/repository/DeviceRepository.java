package app.eshop.repository;
import app.eshop.entity.Device;
import app.eshop.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findAllDevicesByUserId(Long userId);

}
