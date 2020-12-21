package com.kry.heartbeat.controller;

import com.kry.heartbeat.exception.BusinessRuleException;
import com.kry.heartbeat.model.dto.ServiceTrackerDto;
import com.kry.heartbeat.service.ServiceTrackerService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/v1/service-tracker")
@Api(tags = "Service Tracker Operation Api")
@Validated
public class ServiceTrackerController {

    private final ServiceTrackerService serviceTrackerService;

    public ServiceTrackerController(ServiceTrackerService serviceTrackerService) {
        this.serviceTrackerService = serviceTrackerService;
    }

    @Operation(summary = "Since the application provides services on a user basis, " +
            "it starts to work with the user id while taking the related services. " +
            "This operation gets user's service trackers")
    @Parameter(name = "userId", description = "Database id of user.")
    @GetMapping(params = "userId")
    public List<ServiceTrackerDto> getServiceTrackers(@RequestParam Long userId) {
        return serviceTrackerService.getAll(userId);
    }


    //todo check user authory
    @Operation(summary = "returns requested Service Tracker")
    @GetMapping(params = "trackerId")
    public ResponseEntity<ServiceTrackerDto> findById(@RequestParam Long trackerId) {
        return new ResponseEntity<>(serviceTrackerService.findById(trackerId), HttpStatus.OK);
    }

    //todo add validation
    @Operation(summary = "Saves service tracker to db. Returns saved ServiceTrackerDto")
    @PostMapping
    public ResponseEntity<ServiceTrackerDto> addServiceTracker(@RequestBody ServiceTrackerDto serviceTrackerDto) throws BusinessRuleException {
        ServiceTrackerDto trackerDto = serviceTrackerService.save(serviceTrackerDto);
        return new ResponseEntity<>(trackerDto, HttpStatus.CREATED);
    }

    @Operation(summary = "deletes service tracker from db")
    @DeleteMapping(params = "trackerId")
    void delete(@RequestParam Long trackerId) {
        serviceTrackerService.delete(trackerId);

    }
}
