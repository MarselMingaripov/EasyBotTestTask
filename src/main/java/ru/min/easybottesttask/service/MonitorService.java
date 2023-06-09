package ru.min.easybottesttask.service;

import ru.min.easybottesttask.model.Monitor;
import ru.min.easybottesttask.model.enums.MonitorDiagonal;

import java.util.List;

public interface MonitorService {

    Monitor addMonitor(Monitor monitor);
    Monitor updateMonitor(Long id, Monitor monitor);
    List<Monitor> findAll();
    Monitor findById(Long id);

    List<Monitor> findAllByDiagonal(MonitorDiagonal diagonal);
}
