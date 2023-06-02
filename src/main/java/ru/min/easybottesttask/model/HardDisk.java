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
    private HardDiskVolume volume;

    public HardDisk(Long serialNumber, String producer, int price, int stockCount, Long id, HardDiskVolume volume) {
        super(serialNumber, producer, price, stockCount);
        this.id = id;
        this.volume = volume;
    }

    public HardDisk(Long serialNumber, String producer, int price, int stockCount) {
        super(serialNumber, producer, price, stockCount);
    }

    public HardDisk() {
    }
}
