package ru.min.easybottesttask.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.min.easybottesttask.model.DesktopComputer;
import ru.min.easybottesttask.model.Monitor;
import ru.min.easybottesttask.repository.MonitorRepository;
import ru.min.easybottesttask.service.MonitorService;
import ru.min.easybottesttask.service.ValidationService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MonitorServiceImpl implements MonitorService {

    private final MonitorRepository repository;
    private final ValidationService service;

    @Override
    public Monitor addMonitor(Monitor monitor) {
        if (service.validate(monitor)){
            return repository.save(monitor);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Monitor updateMonitor(Long id, Monitor monitor) {
        if (service.validate(monitor) &&
                repository.findById(id).isPresent()){
            Monitor monitorFromBd = repository.findById(id).get();
            monitorFromBd.setDiagonal(monitor.getDiagonal());
            monitorFromBd.setPrice(monitor.getPrice());
            monitorFromBd.setProducer(monitor.getProducer());
            monitorFromBd.setStockCount(monitor.getStockCount());
            monitorFromBd.setSerialNumber(monitor.getSerialNumber());
            return repository.save(monitorFromBd);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<Monitor> findAll() {
        return null;
    }

    @Override
    public Monitor findById(Long id) {
        return null;
    }
}
