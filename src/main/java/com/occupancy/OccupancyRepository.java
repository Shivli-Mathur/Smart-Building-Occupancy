package com.occupancy;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OccupancyRepository extends JpaRepository<Occupancy, Long> {
    Occupancy findByRoomId(String roomId);
}
