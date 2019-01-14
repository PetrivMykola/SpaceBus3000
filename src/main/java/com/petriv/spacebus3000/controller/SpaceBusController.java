package com.petriv.spacebus3000.controller;

import com.petriv.spacebus3000.model.DirectionRequest;
import com.petriv.spacebus3000.service.SpaceBusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
public class SpaceBusController {

    private final SpaceBusService spaceBusService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Set<String> getSpaceports() {
        return spaceBusService.getSpacePorts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public boolean isConnection(@RequestBody DirectionRequest directionRequest) {
        return spaceBusService.isConnection(directionRequest.getFrom(), directionRequest.getTo());
    }
}


