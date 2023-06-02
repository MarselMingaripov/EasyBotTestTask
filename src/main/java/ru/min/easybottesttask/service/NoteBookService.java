package ru.min.easybottesttask.service;

import ru.min.easybottesttask.model.NoteBook;

import java.util.List;

public interface NoteBookService {

    NoteBook addNoteBook(NoteBook noteBook);
    NoteBook updateNoteBook(Long id, NoteBook noteBook);
    List<NoteBook> findAll();
    NoteBook findById(Long id);
}
