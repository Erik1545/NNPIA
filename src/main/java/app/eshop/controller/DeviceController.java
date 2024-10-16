package app.eshop.controller;
import app.eshop.dto.DeviceDTO;
import app.eshop.entity.Device;
import app.eshop.entity.MyUser;
import app.eshop.security.JWTUtilities;
import app.eshop.service.DeviceService;
import app.eshop.service.UserService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/api/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private JWTUtilities jwtUtilities;

    @Autowired
    private UserService userService;


    @GetMapping
    public List<Device> getAllDeviceById(@RequestHeader("Authorization") String token) {
        String username = jwtUtilities.extractUsername(token.substring(7));
        MyUser myUser = userService.getUserByUsername(username);
        return deviceService.getAllDevicesById(myUser.getId());
    }

    @PostMapping
    public Device createDevice(@RequestParam("deviceName") @NotNull String deviceName, @RequestParam("description") String description, @RequestHeader("Authorization") String token) {
        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setDeviceName(deviceDTO.getDeviceName());
        deviceDTO.setDescription(deviceDTO.getDescription());
        String username = jwtUtilities.extractUsername(token.substring(7));
        MyUser myUser = userService.getUserByUsername(username);
        deviceDTO.setUserId(myUser.getId());
        return deviceService.createDevice(deviceDTO);
    }

    @GetMapping("/{id}")
    public Device getAllDeviceById(@RequestHeader("Authorization") String token, @PathVariable Long id){
        return deviceService.getDeviceById(id);
    }

    @PutMapping("/{id}")
    public Device updateDevice(@PathVariable Long id,
                               @RequestParam("deviceName") @NotNull String deviceName, @RequestParam("description") String description){
        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setDeviceName(deviceName);
        deviceDTO.setDescription(description);
        return deviceService.updateDevice(id, deviceDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        deviceService.deleteDevice(id);
    }


}
