package ru.intodayer.altarixrestapitask.repositories;

import org.springframework.stereotype.Repository;
import ru.intodayer.altarixrestapitask.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

}
