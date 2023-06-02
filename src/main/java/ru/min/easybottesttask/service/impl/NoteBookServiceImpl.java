package ru.min.easybottesttask.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.min.easybottesttask.model.DesktopComputer;
import ru.min.easybottesttask.model.NoteBook;
import ru.min.easybottesttask.repository.NoteBookRepository;
import ru.min.easybottesttask.service.NoteBookService;
import ru.min.easybottesttask.service.ValidationService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NoteBookServiceImpl implements NoteBookService {

    private final NoteBookRepository repository;
    private final ValidationService service;

    @Override
    public NoteBook addNoteBook(NoteBook noteBook) {
        if (service.validate(noteBook)){
            return repository.save(noteBook);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public NoteBook updateNoteBook(Long id, NoteBook noteBook) {
        if (service.validate(noteBook) &&
                repository.findById(id).isPresent()){
            NoteBook noteBookFromBd = repository.findById(id).get();
            noteBookFromBd.setSize(noteBook.getSize());
            noteBookFromBd.setPrice(noteBook.getPrice());
            noteBookFromBd.setProducer(noteBook.getProducer());
            noteBookFromBd.setStockCount(noteBook.getStockCount());
            noteBookFromBd.setSerialNumber(noteBook.getSerialNumber());
            return repository.save(noteBookFromBd);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<NoteBook> findAll() {
        return null;
    }

    @Override
    public NoteBook findById(Long id) {
        return null;
    }
}
