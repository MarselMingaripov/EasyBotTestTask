package ru.min.easybottesttask.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.min.easybottesttask.model.DesktopComputer;
import ru.min.easybottesttask.model.HardDisk;
import ru.min.easybottesttask.model.Monitor;
import ru.min.easybottesttask.model.NoteBook;
import ru.min.easybottesttask.model.enums.Form;
import ru.min.easybottesttask.model.enums.HardDiskVolume;
import ru.min.easybottesttask.model.enums.MonitorDiagonal;
import ru.min.easybottesttask.model.enums.ScreenSize;

import static org.junit.jupiter.api.Assertions.*;

class ValidationServiceImplTest {

    private ValidationServiceImpl out = new ValidationServiceImpl();

    private DesktopComputer desktopComputer = new DesktopComputer(
            1L, Form.MONOBLOCK, 123L, "producer", 456, 789
    );
    private HardDisk hardDisk = new HardDisk(
            1L, HardDiskVolume.ONE_HUNDRED, 123L, "producer", 456, 789
    );
    private Monitor monitor = new Monitor(
            1L, MonitorDiagonal.TWENTY_FOUR, 123L, "producer", 456, 789
    );
    private NoteBook noteBook = new NoteBook(
            1L, ScreenSize.FIFTEEN, 123L, "producer", 456, 789
    );

    private DesktopComputer desktopComputerIncorrect = new DesktopComputer(
            1L, Form.MONOBLOCK, -123L, "producer", 456, 789
    );
    private HardDisk hardDiskIncorrect = new HardDisk(
            1L, HardDiskVolume.ONE_HUNDRED, -123L, "producer", 456, 789
    );
    private Monitor monitorIncorrect = new Monitor(
            1L, MonitorDiagonal.TWENTY_FOUR, -123L, "producer", 456, 789
    );
    private NoteBook noteBookIncorrect = new NoteBook(
            1L, ScreenSize.FIFTEEN, -123L, "producer", 456, 789
    );

    @Test
    @DisplayName("Прохождение валиидации при корректных значениях компьютера")
    void shouldReturnTrueWhenValidateDesktopComputer(){
        assertTrue(out.validate(desktopComputer));
    }

    @Test
    @DisplayName("Непрохождение валиидации при некорректных значениях компьютера")
    void shouldReturnFalseWhenValidateDesktopComputer(){
        assertFalse(out.validate(desktopComputerIncorrect));
    }

    @Test
    @DisplayName("Прохождение валиидации при корректных значениях жесткого диска")
    void shouldReturnTrueWhenValidateHardDisk(){
        assertTrue(out.validate(hardDisk));
    }

    @Test
    @DisplayName("Непрохождение валиидации при некорректных значениях жесткого диска")
    void shouldReturnFalseWhenValidateYardDisk(){
        assertFalse(out.validate(hardDiskIncorrect));
    }

    @Test
    @DisplayName("Прохождение валиидации при корректных значениях монитора")
    void shouldReturnTrueWhenValidateMonitor(){
        assertTrue(out.validate(monitor));
    }

    @Test
    @DisplayName("Непрохождение валиидации при некорректных значениях монитора")
    void shouldReturnFalseWhenValidateMonitor(){
        assertFalse(out.validate(monitorIncorrect));
    }

    @Test
    @DisplayName("Прохождение валиидации при корректных значениях ноутбука")
    void shouldReturnTrueWhenValidateNoteBook(){
        assertTrue(out.validate(noteBook));
    }

    @Test
    @DisplayName("Непрохождение валиидации при некорректных значениях ноутбука")
    void shouldReturnFalseWhenValidateNoteBook(){
        assertFalse(out.validate(noteBookIncorrect));
    }

}