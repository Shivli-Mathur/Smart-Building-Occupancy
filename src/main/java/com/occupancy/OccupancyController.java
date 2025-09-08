package com.occupancy;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/occupancy")
@CrossOrigin(origins = "*")  // important for frontend calls
public class OccupancyController {

    private final OccupancyRepository repository;

    public OccupancyController(OccupancyRepository repository) {
        this.repository = repository;
    }

    // Get all rooms + total
    @GetMapping
    public Map<String, Object> getOccupancy() {
        Map<String, Object> response = new HashMap<>();
        int total = 0;
        for (Occupancy o : repository.findAll()) {
            response.put(o.getRoomId(), o.getCount());
            total += o.getCount();
        }
        response.put("total", total);
        return response;
    }

    // Update a room count
    @PostMapping("/update")
    public Occupancy updateRoom(@RequestBody Occupancy request) {
        Occupancy room = repository.findByRoomId(request.getRoomId());
        if (room == null) {
            room = new Occupancy();
            room.setRoomId(request.getRoomId());
        }
        room.setCount(request.getCount());
        return repository.save(room);
    }
    // Simple health check endpoint
    @GetMapping("/status")
    public String status() {
        return "Backend is running ✅";
    }

}
