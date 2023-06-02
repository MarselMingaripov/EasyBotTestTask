package ru.min.easybottesttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.min.easybottesttask.model.NoteBook;

@Repository
public interface NoteBookRepository extends JpaRepository<NoteBook, Long> {
}
