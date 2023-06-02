package ru.min.easybottesttask.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.min.easybottesttask.exception.MyValidationException;
import ru.min.easybottesttask.model.DesktopComputer;
import ru.min.easybottesttask.model.enums.Form;
import ru.min.easybottesttask.repository.DesktopComputerRepository;
import ru.min.easybottesttask.service.DesktopComputerService;
import ru.min.easybottesttask.service.ValidationService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DesktopComputerServiceImpl implements DesktopComputerService {

    private final DesktopComputerRepository repository;
    private final ValidationService service;

    @Override
    public DesktopComputer addComputer(DesktopComputer computer) {
        if (service.validate(computer)){
            return repository.save(computer);
        } else {
            throw new MyValidationException("Validation error");
        }
    }

    @Override
    public DesktopComputer updateComputer(Long id, DesktopComputer computer) {
        if (service.validate(computer) &&
            repository.findById(id).isPresent()){
            DesktopComputer computerFromBd = repository.findById(id).get();
            computerFromBd.setForm(computer.getForm());
            computerFromBd.setPrice(computer.getPrice());
            computerFromBd.setProducer(computer.getProducer());
            computerFromBd.setStockCount(computer.getStockCount());
            computerFromBd.setSerialNumber(computer.getSerialNumber());
            return repository.save(computerFromBd);
        } else {
            throw new MyValidationException("Error in input data");
        }
    }

    @Override
    public List<DesktopComputer> findAll() {
        return repository.findAll();
    }

    @Override
    public DesktopComputer findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Not found"));
    }

    @Override
    public List<DesktopComputer> findAllByForm(Form form){
        return repository.findAllByForm(form);
    }
}
