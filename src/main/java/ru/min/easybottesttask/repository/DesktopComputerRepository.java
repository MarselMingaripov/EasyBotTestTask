package ru.min.easybottesttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.min.easybottesttask.model.DesktopComputer;

@Repository
public interface DesktopComputerRepository extends JpaRepository<DesktopComputer, Long> {
}
