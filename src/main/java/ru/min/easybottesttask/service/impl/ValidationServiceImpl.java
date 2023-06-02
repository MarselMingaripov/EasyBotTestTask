package ru.min.easybottesttask.service.impl;

import org.springframework.stereotype.Service;
import ru.min.easybottesttask.model.DesktopComputer;
import ru.min.easybottesttask.model.HardDisk;
import ru.min.easybottesttask.model.Monitor;
import ru.min.easybottesttask.model.NoteBook;
import ru.min.easybottesttask.service.ValidationService;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Override
    public boolean validate(DesktopComputer computer) {
        return (computer.getSerialNumber() > 0 &&
                computer.getPrice() > 0 &&
                computer.getStockCount() > -1 &&
                !computer.getProducer().equals(null));
    }

    @Override
    public boolean validate(NoteBook noteBook) {
        return (noteBook.getSerialNumber() > 0 &&
                noteBook.getPrice() > 0 &&
                noteBook.getStockCount() > -1 &&
                !noteBook.getProducer().equals(null));
    }

    @Override
    public boolean validate(Monitor monitor) {
        return (monitor.getSerialNumber() > 0 &&
                monitor.getPrice() > 0 &&
                monitor.getStockCount() > -1 &&
                !monitor.getProducer().equals(null));
    }

    @Override
    public boolean validate(HardDisk hardDisk) {
        return (hardDisk.getSerialNumber() > 0 &&
                hardDisk.getPrice() > 0 &&
                hardDisk.getStockCount() > -1 &&
                !hardDisk.getProducer().equals(null));
    }
}
