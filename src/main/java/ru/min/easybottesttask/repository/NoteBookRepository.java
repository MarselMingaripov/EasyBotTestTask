package ru.min.easybottesttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.min.easybottesttask.model.NoteBook;
import ru.min.easybottesttask.model.enums.ScreenSize;

import java.util.List;

@Repository
public interface NoteBookRepository extends JpaRepository<NoteBook, Long> {

    List<NoteBook> findAllBySize(ScreenSize screenSize);
}
