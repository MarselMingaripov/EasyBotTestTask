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
import ru.min.easybottesttask.model.DesktopComputer;
import ru.min.easybottesttask.model.enums.Form;
import ru.min.easybottesttask.repository.DesktopComputerRepository;
import ru.min.easybottesttask.service.ValidationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DesktopComputerServiceImplTest {

    private final Long ID = 1L;
    private final Form FORM = Form.DESKTOP;
    private final Long SERIAL_NUMBER = 11111111L;
    private final String PRODUCER = "producer";
    private final int PRICE = 1000;
    private final int STOCK_COUNT = 2000;
    private DesktopComputer desktopComputer;

    @Mock
    private DesktopComputerRepository repositoryMock;

    @Mock
    private ValidationService serviceMock;

    @InjectMocks
    private DesktopComputerServiceImpl out;

    @BeforeEach
    public void init(){
        desktopComputer = new DesktopComputer(ID, FORM, SERIAL_NUMBER, PRODUCER, PRICE, STOCK_COUNT);
    }

    @Test
    @DisplayName("Проверка корректного создания компьютера")
    void shouldReturnComputerWhenCreateComputer(){
        when(serviceMock.validate(any(desktopComputer.getClass()))).thenReturn(true);
        when(repositoryMock.save(any())).thenReturn(desktopComputer);
        assertEquals(desktopComputer, out.addComputer(desktopComputer));
    }

    @Test
    @DisplayName("Проверка выброса исключения при создании компьютера")
    void shouldThrowExceptionWhenCreateComputer(){
        when(serviceMock.validate(any(desktopComputer.getClass()))).thenReturn(false);
        assertThrows(MyValidationException.class, () -> out.addComputer(desktopComputer));
    }

    @Test
    @DisplayName("Проверка корректного обновления компьютера")
    void shouldReturnComputerWhenUpdateComputer(){
        when(serviceMock.validate(any(desktopComputer.getClass()))).thenReturn(true);
        when(repositoryMock.findById(any())).thenReturn(Optional.of(desktopComputer));
        when(repositoryMock.save(any())).thenReturn(desktopComputer);
        assertEquals(desktopComputer, out.updateComputer(any(), desktopComputer));
    }

    @Test
    @DisplayName("Проверка выброса исключения при обновлении компьютера")
    void shouldThrowExceptionWhenUpdateComputer(){
        when(serviceMock.validate(any(desktopComputer.getClass()))).thenReturn(false);
        assertThrows(MyValidationException.class, () -> out.updateComputer(any(), desktopComputer));
    }

    @Test
    @DisplayName("Проверка выброса исключения при обновлении компьютера")
    void shouldThrowExceptionWhenUpdateComputerSecond(){
        when(serviceMock.validate(any(desktopComputer.getClass()))).thenReturn(true);
        when(repositoryMock.findById(any())).thenReturn(Optional.empty());
        assertThrows(MyValidationException.class, () -> out.updateComputer(any(), desktopComputer));
    }

    @Test
    @DisplayName("Проверка корректного получения всех компьютеров")
    void shouldReturnListOfComputersWhenFindAll(){
        when(repositoryMock.findAll()).thenReturn(List.of(desktopComputer));
        assertEquals(new ArrayList<>(List.of(desktopComputer)), out.findAll());
    }

    @Test
    @DisplayName("Проверка корректного получения компьютера по идентификатору")
    void shouldReturnComputerWhenFindById(){
        when(repositoryMock.findById(any())).thenReturn(Optional.of(desktopComputer));
        assertEquals(desktopComputer, out.findById(any()));
    }

    @Test
    @DisplayName("Проверка выброса исключения при получении компьютера по идентификатору")
    void shouldThrowExceptionWhenFindById(){
        when(repositoryMock.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> out.findById(any()));
    }

    @Test
    @DisplayName("Проверка корректного получения всех компьютеров по форме")
    void shouldReturnListOfComputersWhenFindAllByForm(){
        when(repositoryMock.findAllByForm(any())).thenReturn(List.of(desktopComputer));
        assertEquals(new ArrayList<>(List.of(desktopComputer)), out.findAllByForm(any()));
    }
}