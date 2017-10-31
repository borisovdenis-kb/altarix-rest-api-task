package ru.intodayer.altarixrestapitask.repositries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.intodayer.altarixrestapitask.models.Position;


@Repository
public interface PositionRepositry extends JpaRepository<Position, Long> {

}
