package ru.min.easybottesttask.model;

import lombok.Data;
import ru.min.easybottesttask.model.enums.MonitorDiagonal;

import javax.persistence.*;

@Entity
@Table(name = "monitor")
@Data
public class Monitor extends Item{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private MonitorDiagonal diagonal;

    public Monitor(Long serialNumber, String producer, int price, int stockCount, Long id, MonitorDiagonal diagonal) {
        super(serialNumber, producer, price, stockCount);
        this.id = id;
        this.diagonal = diagonal;
    }

    public Monitor() {
    }
}
