package ru.min.easybottesttask.model;

import lombok.Data;
import ru.min.easybottesttask.model.enums.ScreenSize;

import javax.persistence.*;

@Entity
@Table(name = "notebook")
@Data
public class NoteBook extends Item{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ScreenSize size;

    public NoteBook(Long id, ScreenSize size, Long serialNumber, String producer, int price, int stockCount) {
        super(serialNumber, producer, price, stockCount);
        this.id = id;
        this.size = size;
    }

    public NoteBook() {
    }
}
