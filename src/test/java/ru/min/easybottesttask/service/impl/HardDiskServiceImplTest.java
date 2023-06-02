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
import ru.min.easybottesttask.model.HardDisk;
import ru.min.easybottesttask.model.enums.HardDiskVolume;
import ru.min.easybottesttask.repository.HardDiskRepository;
import ru.min.easybottesttask.service.ValidationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HardDiskServiceImplTest {

    private final Long ID = 1L;
    private final HardDiskVolume VOLUME = HardDiskVolume.ONE_HUNDRED;
    private final Long SERIAL_NUMBER = 11111111L;
    private final String PRODUCER = "producer";
    private final int PRICE = 1000;
    private final int STOCK_COUNT = 2000;
    private HardDisk hardDisk;

    @Mock
    private HardDiskRepository repositoryMock;

    @Mock
    private ValidationService serviceMock;

    @InjectMocks
    private HardDiskServiceImpl out;

    @BeforeEach
    public void init(){
        hardDisk = new HardDisk(ID, VOLUME, SERIAL_NUMBER, PRODUCER, PRICE, STOCK_COUNT);
    }

    @Test
    @DisplayName("Проверка корректного создания жесткого диска")
    void shouldReturnHardDiskWhenCreateHardDisk(){
        when(serviceMock.validate(any(hardDisk.getClass()))).thenReturn(true);
        when(repositoryMock.save(any())).thenReturn(hardDisk);
        assertEquals(hardDisk, out.addHardDisk(hardDisk));
    }

    @Test
    @DisplayName("Проверка выброса исключения при создании жесткого диска")
    void shouldThrowExceptionWhenCreateHardDisk(){
        when(serviceMock.validate(any(hardDisk.getClass()))).thenReturn(false);
        assertThrows(MyValidationException.class, () -> out.addHardDisk(hardDisk));
    }

    @Test
    @DisplayName("Проверка корректного обновления жесткого диска")
    void shouldReturnHardDiskWhenUpdateHardDisk(){
        when(serviceMock.validate(any(hardDisk.getClass()))).thenReturn(true);
        when(repositoryMock.findById(any())).thenReturn(Optional.of(hardDisk));
        when(repositoryMock.save(any())).thenReturn(hardDisk);
        assertEquals(hardDisk, out.updateHardDisk(any(), hardDisk));
    }

    @Test
    @DisplayName("Проверка выброса исключения при обновлении жесткого диска")
    void shouldThrowExceptionWhenUpdateHardDisk(){
        when(serviceMock.validate(any(hardDisk.getClass()))).thenReturn(false);
        assertThrows(MyValidationException.class, () -> out.updateHardDisk(any(), hardDisk));
    }

    @Test
    @DisplayName("Проверка выброса исключения при обновлении жесткого диска")
    void shouldThrowExceptionWhenUpdateHardDiskSecond(){
        when(serviceMock.validate(any(hardDisk.getClass()))).thenReturn(true);
        when(repositoryMock.findById(any())).thenReturn(Optional.empty());
        assertThrows(MyValidationException.class, () -> out.updateHardDisk(any(), hardDisk));
    }

    @Test
    @DisplayName("Проверка корректного получения всех жестких дисков")
    void shouldReturnListOfHardDisksWhenFindAll(){
        when(repositoryMock.findAll()).thenReturn(List.of(hardDisk));
        assertEquals(new ArrayList<>(List.of(hardDisk)), out.findAll());
    }

    @Test
    @DisplayName("Проверка корректного получения жесткого диска по идентификатору")
    void shouldReturnHardDiskWhenFindById(){
        when(repositoryMock.findById(any())).thenReturn(Optional.of(hardDisk));
        assertEquals(hardDisk, out.findById(any()));
    }

    @Test
    @DisplayName("Проверка выброса исключения при получении жесткого диска по идентификатору")
    void shouldThrowExceptionWhenFindById(){
        when(repositoryMock.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> out.findById(any()));
    }

    @Test
    @DisplayName("Проверка корректного получения всех жестких дисков по объему")
    void shouldReturnListOfHardDisksWhenFindAllByForm(){
        when(repositoryMock.findAllByVolume(any())).thenReturn(List.of(hardDisk));
        assertEquals(new ArrayList<>(List.of(hardDisk)), out.findAllByVolume(any()));
    }
}