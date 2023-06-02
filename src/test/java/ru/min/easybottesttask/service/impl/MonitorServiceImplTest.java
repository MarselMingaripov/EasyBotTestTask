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
import ru.min.easybottesttask.model.Monitor;
import ru.min.easybottesttask.model.enums.MonitorDiagonal;
import ru.min.easybottesttask.repository.MonitorRepository;
import ru.min.easybottesttask.service.ValidationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MonitorServiceImplTest {

    private final Long ID = 1L;
    private final MonitorDiagonal DIAGONAL = MonitorDiagonal.TWENTY_FOUR;
    private final Long SERIAL_NUMBER = 11111111L;
    private final String PRODUCER = "producer";
    private final int PRICE = 1000;
    private final int STOCK_COUNT = 2000;
    private Monitor monitor;

    @Mock
    private MonitorRepository repositoryMock;

    @Mock
    private ValidationService serviceMock;

    @InjectMocks
    private MonitorServiceImpl out;

    @BeforeEach
    public void init(){
        monitor = new Monitor(ID, DIAGONAL, SERIAL_NUMBER, PRODUCER, PRICE, STOCK_COUNT);
    }

    @Test
    @DisplayName("Проверка корректного создания монитора")
    void shouldReturnMonitorWhenCreateMonitor(){
        when(serviceMock.validate(any(monitor.getClass()))).thenReturn(true);
        when(repositoryMock.save(any())).thenReturn(monitor);
        assertEquals(monitor, out.addMonitor(monitor));
    }

    @Test
    @DisplayName("Проверка выброса исключения при создании монитора")
    void shouldThrowExceptionWhenCreateMonitor(){
        when(serviceMock.validate(any(monitor.getClass()))).thenReturn(false);
        assertThrows(MyValidationException.class, () -> out.addMonitor(monitor));
    }

    @Test
    @DisplayName("Проверка корректного обновления монитора")
    void shouldReturnMonitorWhenUpdateMonitor(){
        when(serviceMock.validate(any(monitor.getClass()))).thenReturn(true);
        when(repositoryMock.findById(any())).thenReturn(Optional.of(monitor));
        when(repositoryMock.save(any())).thenReturn(monitor);
        assertEquals(monitor, out.updateMonitor(any(), monitor));
    }

    @Test
    @DisplayName("Проверка выброса исключения при обновлении монитора")
    void shouldThrowExceptionWhenUpdateMonitor(){
        when(serviceMock.validate(any(monitor.getClass()))).thenReturn(false);
        assertThrows(MyValidationException.class, () -> out.updateMonitor(any(), monitor));
    }

    @Test
    @DisplayName("Проверка выброса исключения при обновлении монитора")
    void shouldThrowExceptionWhenUpdateMonitorSecond(){
        when(serviceMock.validate(any(monitor.getClass()))).thenReturn(true);
        when(repositoryMock.findById(any())).thenReturn(Optional.empty());
        assertThrows(MyValidationException.class, () -> out.updateMonitor(any(), monitor));
    }

    @Test
    @DisplayName("Проверка корректного получения всех мониторов")
    void shouldReturnListOfMonitorWhenFindAll(){
        when(repositoryMock.findAll()).thenReturn(List.of(monitor));
        assertEquals(new ArrayList<>(List.of(monitor)), out.findAll());
    }

    @Test
    @DisplayName("Проверка корректного получения монитора по идентификатору")
    void shouldReturnMonitorWhenFindById(){
        when(repositoryMock.findById(any())).thenReturn(Optional.of(monitor));
        assertEquals(monitor, out.findById(any()));
    }

    @Test
    @DisplayName("Проверка выброса исключения при получении монитора по идентификатору")
    void shouldThrowExceptionWhenFindById(){
        when(repositoryMock.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> out.findById(any()));
    }

    @Test
    @DisplayName("Проверка корректного получения всех мониторов по диагонали")
    void shouldReturnListOfMonitorsWhenFindAllByForm(){
        when(repositoryMock.findAllByDiagonal(any())).thenReturn(List.of(monitor));
        assertEquals(new ArrayList<>(List.of(monitor)), out.findAllByDiagonal(any()));
    }
}