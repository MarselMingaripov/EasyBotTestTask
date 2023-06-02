package ru.min.easybottesttask.service;

import ru.min.easybottesttask.model.DesktopComputer;

import java.util.List;

public interface DesktopComputerService {

    DesktopComputer addComputer(DesktopComputer computer);
    DesktopComputer updateComputer(Long id, DesktopComputer computer);
    List<DesktopComputer> findAll();
    DesktopComputer findById(Long id);
}
