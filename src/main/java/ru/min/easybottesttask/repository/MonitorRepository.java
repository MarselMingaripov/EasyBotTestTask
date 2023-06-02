package ru.min.easybottesttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.min.easybottesttask.model.Monitor;
import ru.min.easybottesttask.model.enums.MonitorDiagonal;

import java.util.List;

@Repository
public interface MonitorRepository extends JpaRepository<Monitor, Long> {

    List<Monitor> findAllByDiagonal(MonitorDiagonal diagonal);
}
