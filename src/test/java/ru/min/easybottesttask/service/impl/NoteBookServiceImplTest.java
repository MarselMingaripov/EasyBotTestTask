package ru.min.easybottesttask.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.webjars.NotFoundException;
import ru.min.easybottesttask.exception.MyValidationException;
import ru.min.easybottesttask.model.NoteBook;
import ru.min.easybottesttask.model.enums.ScreenSize;
import ru.min.easybottesttask.repository.NoteBookRepository;
import ru.min.easybottesttask.service.ValidationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NoteBookServiceImplTest {

    private final Long ID = 1L;
    private final ScreenSize SIZE = ScreenSize.FIFTEEN;
    private final Long SERIAL_NUMBER = 11111111L;
    private final String PRODUCER = "producer";
    private final int PRICE = 1000;
    private final int STOCK_COUNT = 2000;
    private NoteBook noteBook;

    @Mock
    private NoteBookRepository repositoryMock;

    @Mock
    private ValidationService serviceMock;

    @InjectMocks
    private NoteBookServiceImpl out;

    @BeforeEach
    public void init(){
        noteBook = new NoteBook(ID, SIZE, SERIAL_NUMBER, PRODUCER, PRICE, STOCK_COUNT);
    }

    @Test
    @DisplayName("Проверка корректного ноутбука")
    void shouldReturnMonitorWhenCreateNoteBook(){
        when(serviceMock.validate(any(noteBook.getClass()))).thenReturn(true);
        when(repositoryMock.save(any())).thenReturn(noteBook);
        assertEquals(noteBook, out.addNoteBook(noteBook));
    }

    @Test
    @DisplayName("Проверка выброса исключения при создании ноутбука")
    void shouldThrowExceptionWhenCreateNoteBook(){
        when(serviceMock.validate(any(noteBook.getClass()))).thenReturn(false);
        assertThrows(MyValidationException.class, () -> out.addNoteBook(noteBook));
    }

    @Test
    @DisplayName("Проверка корректного обновления ноутбука")
    void shouldReturnNoteBookWhenUpdateNoteBook(){
        when(serviceMock.validate(any(noteBook.getClass()))).thenReturn(true);
        when(repositoryMock.findById(any())).thenReturn(Optional.of(noteBook));
        when(repositoryMock.save(any())).thenReturn(noteBook);
        assertEquals(noteBook, out.updateNoteBook(any(), noteBook));
    }

    @Test
    @DisplayName("Проверка выброса исключения при обновлении ноутбука")
    void shouldThrowExceptionWhenUpdateNoteBook(){
        when(serviceMock.validate(any(noteBook.getClass()))).thenReturn(false);
        assertThrows(MyValidationException.class, () -> out.updateNoteBook(any(), noteBook));
    }

    @Test
    @DisplayName("Проверка выброса исключения при обновлении ноутбука")
    void shouldThrowExceptionWhenUpdateNoteBookSecond(){
        when(serviceMock.validate(any(noteBook.getClass()))).thenReturn(true);
        when(repositoryMock.findById(any())).thenReturn(Optional.empty());
        assertThrows(MyValidationException.class, () -> out.updateNoteBook(any(), noteBook));
    }

    @Test
    @DisplayName("Проверка корректного получения всех ноутбуков")
    void shouldReturnListOfMonitorWhenFindAll(){
        when(repositoryMock.findAll()).thenReturn(List.of(noteBook));
        assertEquals(new ArrayList<>(List.of(noteBook)), out.findAll());
    }

    @Test
    @DisplayName("Проверка корректного получения ноутбука по идентификатору")
    void shouldReturnMonitorWhenFindById(){
        when(repositoryMock.findById(any())).thenReturn(Optional.of(noteBook));
        assertEquals(noteBook, out.findById(any()));
    }

    @Test
    @DisplayName("Проверка выброса исключения при получении ноутбука по идентификатору")
    void shouldThrowExceptionWhenFindById(){
        when(repositoryMock.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> out.findById(any()));
    }

    @Test
    @DisplayName("Проверка корректного получения всех ноутбуков по размеру дисплея")
    void shouldReturnListOfNoteBooksWhenFindAllByForm(){
        when(repositoryMock.findAllBySize(any())).thenReturn(List.of(noteBook));
        assertEquals(new ArrayList<>(List.of(noteBook)), out.findAllBySize(any()));
    }
}