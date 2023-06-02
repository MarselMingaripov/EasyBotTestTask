package ru.min.easybottesttask.service;

import ru.min.easybottesttask.model.HardDisk;
import ru.min.easybottesttask.model.enums.HardDiskVolume;

import java.util.List;

public interface HardDiskService {

    HardDisk addHardDisk(HardDisk hardDisk);
    HardDisk updateHardDisk(Long id, HardDisk hardDisk);
    List<HardDisk> findAll();
    HardDisk findById(Long id);

    List<HardDisk> findAllByVolume(HardDiskVolume hardDiskVolume);
}
