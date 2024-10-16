package app.eshop.repository;
import app.eshop.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
    List<Sensor> findAllByDeviceId(Long deviceId);
}
