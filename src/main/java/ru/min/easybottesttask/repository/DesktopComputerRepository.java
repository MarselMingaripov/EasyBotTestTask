package ru.min.easybottesttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.min.easybottesttask.model.DesktopComputer;
import ru.min.easybottesttask.model.enums.Form;

import java.util.List;

@Repository
public interface DesktopComputerRepository extends JpaRepository<DesktopComputer, Long> {

    List<DesktopComputer> findAllByForm(Form form);
}
