package ru.min.easybottesttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.min.easybottesttask.model.HardDisk;
import ru.min.easybottesttask.model.enums.HardDiskVolume;

import java.util.List;

@Repository
public interface HardDiskRepository extends JpaRepository<HardDisk, Long> {

    List<HardDisk> findAllByVolume(HardDiskVolume hardDiskVolume);
}
