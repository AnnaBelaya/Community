package ua.belaya.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.belaya.community.domain.Room;

/**
 * @author Anna Belaya
 */

public interface RoomJpaRepository extends JpaRepository<Room, String>{
}
