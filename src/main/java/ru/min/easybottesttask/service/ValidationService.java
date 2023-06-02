package ru.min.easybottesttask.service;

import ru.min.easybottesttask.model.DesktopComputer;
import ru.min.easybottesttask.model.HardDisk;
import ru.min.easybottesttask.model.Monitor;
import ru.min.easybottesttask.model.NoteBook;

public interface ValidationService {

    boolean validate(DesktopComputer computer);
    boolean validate(NoteBook noteBook);
    boolean validate(Monitor monitor);
    boolean validate(HardDisk hardDisk);
}
