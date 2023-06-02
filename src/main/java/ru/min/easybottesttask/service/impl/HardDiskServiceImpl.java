package ru.min.easybottesttask.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.min.easybottesttask.model.DesktopComputer;
import ru.min.easybottesttask.model.HardDisk;
import ru.min.easybottesttask.repository.HardDiskRepository;
import ru.min.easybottesttask.service.HardDiskService;
import ru.min.easybottesttask.service.ValidationService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HardDiskServiceImpl implements HardDiskService {

    private final HardDiskRepository repository;
    private final ValidationService service;

    @Override
    public HardDisk addHardDisk(HardDisk hardDisk) {
        if (service.validate(hardDisk)){
            return repository.save(hardDisk);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public HardDisk updateHardDisk(Long id, HardDisk hardDisk) {
        if (service.validate(hardDisk) &&
                repository.findById(id).isPresent()){
            HardDisk hardDiskFromBd = repository.findById(id).get();
            hardDiskFromBd.setVolume(hardDisk.getVolume());
            hardDiskFromBd.setPrice(hardDisk.getPrice());
            hardDiskFromBd.setProducer(hardDisk.getProducer());
            hardDiskFromBd.setStockCount(hardDisk.getStockCount());
            hardDiskFromBd.setSerialNumber(hardDisk.getSerialNumber());
            return repository.save(hardDisk);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<HardDisk> findAll() {
        return repository.findAll();
    }

    @Override
    public HardDisk findById(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
