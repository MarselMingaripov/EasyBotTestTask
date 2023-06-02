package ru.min.easybottesttask.model;

import ru.min.easybottesttask.model.enums.MonitorDiagonal;

import javax.persistence.*;

@Entity
@Table(name = "monitor")
public class Monitor extends Item{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private MonitorDiagonal diagonal;

    public Monitor(Long serialNumber, String producer, int price, int stockCount, Long id, MonitorDiagonal diagonal) {
        super(serialNumber, producer, price, stockCount);
        this.id = id;
        this.diagonal = diagonal;
    }

    public Monitor(Long serialNumber, String producer, int price, int stockCount) {
        super(serialNumber, producer, price, stockCount);
    }

    public Monitor() {
    }
}
