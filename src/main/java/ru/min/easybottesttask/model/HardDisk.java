package ru.min.easybottesttask.model;

import lombok.Data;
import ru.min.easybottesttask.model.enums.HardDiskVolume;

import javax.persistence.*;

@Entity
@Table(name = "desktop_computer")
@Data
public class HardDisk extends Item{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private HardDiskVolume volume;

    public HardDisk(Long id, HardDiskVolume volume, Long serialNumber, String producer, int price, int stockCount) {
        super(serialNumber, producer, price, stockCount);
        this.id = id;
        this.volume = volume;
    }

    public HardDisk() {
    }
}
