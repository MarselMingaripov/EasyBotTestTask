package ru.min.easybottesttask.service;

import ru.min.easybottesttask.model.DesktopComputer;
import ru.min.easybottesttask.model.enums.Form;

import java.util.List;

public interface DesktopComputerService {

    DesktopComputer addComputer(DesktopComputer computer);
    DesktopComputer updateComputer(Long id, DesktopComputer computer);
    List<DesktopComputer> findAll();
    DesktopComputer findById(Long id);

    List<DesktopComputer> findAllByForm(Form form);
}
