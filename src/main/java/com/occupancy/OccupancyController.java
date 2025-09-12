package com.occupancy;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
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

    // Endpoint for frontend: returns all rooms with occupancy and maxCapacity
    @GetMapping("/rooms")
    public List<Map<String, Object>> getRooms() {
        List<Map<String, Object>> rooms = new ArrayList<>();
        // Example: you can replace this with a DB table for room maxCapacity
        Map<String, Integer> maxCapacities = new HashMap<>();
        maxCapacities.put("room1", 10);
        maxCapacities.put("room2", 10);
        maxCapacities.put("room3", 10);
        maxCapacities.put("room4", 10);
        maxCapacities.put("room5", 10);
        maxCapacities.put("room6", 10);
        maxCapacities.put("room7", 10);
        maxCapacities.put("room8", 10);
        maxCapacities.put("room9", 10);
        maxCapacities.put("room10", 10);

        for (Occupancy o : repository.findAll()) {
            Map<String, Object> room = new HashMap<>();
            room.put("id", o.getRoomId());
            room.put("name", formatRoomName(o.getRoomId()));
            room.put("currentOccupancy", o.getCount());
            room.put("maxCapacity", maxCapacities.getOrDefault(o.getRoomId(), 10));
            rooms.add(room);
        }
        // Add any rooms that exist in maxCapacities but not in DB
        for (String roomId : maxCapacities.keySet()) {
            boolean exists = rooms.stream().anyMatch(r -> r.get("id").equals(roomId));
            if (!exists) {
                Map<String, Object> room = new HashMap<>();
                room.put("id", roomId);
                room.put("name", formatRoomName(roomId));
                room.put("currentOccupancy", 0);
                room.put("maxCapacity", maxCapacities.get(roomId));
                rooms.add(room);
            }
        }
        return rooms;
    }

    private String formatRoomName(String roomId) {
        // Convert "room1" to "Room 1"
        if (roomId.startsWith("room")) {
            String number = roomId.substring(4);
            return "Room " + number;
        }
        return roomId;
    }

    // Add person to specific room
    @PostMapping("/rooms/{roomId}/add")
    public Map<String, Object> addPersonToRoom(@PathVariable String roomId) {
        Occupancy room = repository.findByRoomId(roomId);
        if (room == null) {
            room = new Occupancy();
            room.setRoomId(roomId);
            room.setCount(0);
        }
        room.setCount(room.getCount() + 1);
        repository.save(room);
        
        Map<String, Object> response = new HashMap<>();
        response.put("roomId", roomId);
        response.put("newCount", room.getCount());
        response.put("success", true);
        return response;
    }

    // Remove person from specific room
    @PostMapping("/rooms/{roomId}/remove")
    public Map<String, Object> removePersonFromRoom(@PathVariable String roomId) {
        Occupancy room = repository.findByRoomId(roomId);
        if (room == null || room.getCount() <= 0) {
            Map<String, Object> response = new HashMap<>();
            response.put("roomId", roomId);
            response.put("newCount", 0);
            response.put("success", false);
            response.put("message", "Room is already empty");
            return response;
        }
        room.setCount(room.getCount() - 1);
        repository.save(room);
        
        Map<String, Object> response = new HashMap<>();
        response.put("roomId", roomId);
        response.put("newCount", room.getCount());
        response.put("success", true);
        return response;
    }

    // Simple health check endpoint
    @GetMapping("/status")
    public String status() {
        return "Backend is running ✅";
    }

}