package ru.min.easybottesttask.model;

import ru.min.easybottesttask.model.enums.ScreenSize;

import javax.persistence.*;

@Entity
@Table(name = "notebook")
public class NoteBook extends Item{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ScreenSize size;

    public NoteBook(Long serialNumber, String producer, int price, int stockCount, Long id, ScreenSize size) {
        super(serialNumber, producer, price, stockCount);
        this.id = id;
        this.size = size;
    }

    public NoteBook(Long serialNumber, String producer, int price, int stockCount) {
        super(serialNumber, producer, price, stockCount);
    }

    public NoteBook() {
    }
}
