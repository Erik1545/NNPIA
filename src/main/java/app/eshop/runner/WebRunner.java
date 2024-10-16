package app.eshop.runner;
import app.eshop.entity.MyUser;
import app.eshop.entity.Sensor;
import app.eshop.entity.Device;
import app.eshop.repository.SensorRepository;
import app.eshop.repository.DeviceRepository;
import app.eshop.repository.UserRepository;
import app.eshop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class WebRunner implements ApplicationRunner {

    @Autowired
    private final DeviceRepository deviceRepository;

    @Autowired
    private final SensorRepository sensorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        MyUser myUser1 = new MyUser();
        myUser1.setUsername("Erikos");
        myUser1.setEmail("kaegyqooylurwfugnd@poplk.com");
        myUser1.setPassword(passwordEncoder.encode("password"));

        MyUser myUser2 = new MyUser();
        myUser2.setUsername("Eva");
        myUser2.setEmail("kam23214@dcobe.com");
        myUser2.setPassword(passwordEncoder.encode("heslo"));

        MyUser savedUser1 = userRepository.save(myUser1);
        MyUser savedUser2 = userRepository.save(myUser2);

        Device device1 = new Device();
        device1.setUserId(savedUser1.getId());
        device1.setDeviceName("Iron Man");
        device1.setDescription("Nejlepsi");

        Device device2 = new Device();
        device2.setUserId(savedUser2.getId());
        device2.setDeviceName("Spiderman");
        device2.setDescription("Trapák se slipama");

        Device savedDevice1 = deviceRepository.save(device1);
        Device savedDevice2 = deviceRepository.save(device2);

        Sensor sensor1 = new Sensor();
        sensor1.setDeviceId(savedDevice1.getId());
        sensor1.setSensorName("Rakety");

        Sensor sensor2 = new Sensor();
        sensor2.setDeviceId(savedDevice1.getId());
        sensor2.setSensorName("Lasery");

        Sensor sensor3 = new Sensor();
        sensor3.setDeviceId(savedDevice2.getId());
        sensor3.setSensorName("Pavučiny");

        sensorRepository.save(sensor1);
        sensorRepository.save(sensor2);
        sensorRepository.save(sensor3);


    }
}
