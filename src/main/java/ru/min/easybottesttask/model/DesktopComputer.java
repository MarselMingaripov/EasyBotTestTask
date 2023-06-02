package ru.min.easybottesttask.model;

import lombok.Data;
import ru.min.easybottesttask.model.enums.Form;

import javax.persistence.*;

@Entity
@Table(name = "desktop_computer")
@Data
public class DesktopComputer extends Item{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Form form;

    public DesktopComputer(Long serialNumber, String producer, int price, int stockCount, Long id, Form form) {
        super(serialNumber, producer, price, stockCount);
        this.id = id;
        this.form = form;
    }

    public DesktopComputer(Long serialNumber, String producer, int price, int stockCount) {
        super(serialNumber, producer, price, stockCount);
    }

    public DesktopComputer() {
    }
}
