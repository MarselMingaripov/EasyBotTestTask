package ru.min.easybottesttask.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.min.easybottesttask.exception.MyValidationException;
import ru.min.easybottesttask.model.Monitor;
import ru.min.easybottesttask.model.enums.MonitorDiagonal;
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
            throw new MyValidationException("Validation error");
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
            throw new MyValidationException("Error in input data");
        }
    }

    @Override
    public List<Monitor> findAll() {
        return repository.findAll();
    }

    @Override
    public Monitor findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Not found"));
    }

    @Override
    public List<Monitor> findAllByDiagonal(MonitorDiagonal diagonal){
        return repository.findAllByDiagonal(diagonal);
    }
}
