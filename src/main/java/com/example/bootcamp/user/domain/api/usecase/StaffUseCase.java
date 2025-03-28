package com.example.bootcamp.user.domain.api.usecase;

import com.example.bootcamp.user.domain.api.IStaffServicePort;
import com.example.bootcamp.user.domain.exception.*;
import com.example.bootcamp.user.domain.model.DeveloperRol;
import com.example.bootcamp.user.domain.model.Staff;
import com.example.bootcamp.user.domain.model.StaffRol;
import com.example.bootcamp.user.domain.spi.IDeveloperRolPersistencePort;
import com.example.bootcamp.user.domain.spi.IStaffPersistencePort;
import com.example.bootcamp.user.domain.spi.IStaffRolPersistencePort;
import com.example.bootcamp.user.domain.spi.IStudentPersistencePort;

import java.util.Optional;

import static com.example.bootcamp.user.domain.util.StudentConstants.EMPTY_STRING;
import static com.example.bootcamp.user.domain.util.StudentMessage.*;
import static com.example.bootcamp.user.domain.util.Validations.isValidEmail;

public class StaffUseCase implements IStaffServicePort {

    private final IStaffPersistencePort staffPersistencePort;
    private final IDeveloperRolPersistencePort developerRolPersistencePort;
    private final IStaffRolPersistencePort staffRolPersistencePort;
    private final IStudentPersistencePort studentPersistencePort;

    public StaffUseCase(
            IStaffPersistencePort staffPersistencePort,
            IDeveloperRolPersistencePort developerRolPersistencePort,
            IStaffRolPersistencePort staffRolPersistencePort,
            IStudentPersistencePort studentPersistencePort
    ){
        this.staffPersistencePort = staffPersistencePort;
        this.developerRolPersistencePort = developerRolPersistencePort;
        this.staffRolPersistencePort = staffRolPersistencePort;
        this.studentPersistencePort = studentPersistencePort;
    }

    @Override
    public void save(Staff staff) {

        if(!isValidEmail(staff.getEmail())) throw new InvalidEmailException(EMAIL_INVALID_MESSAGE);

        if(staffPersistencePort.findByEmail(staff.getEmail()).isPresent()) throw new StaffMemberExistException(STAFF_MEMBER_EXIST);

        if(studentPersistencePort.findByEmailOrIdentification(staff.getEmail(), EMPTY_STRING).isPresent())
            throw new EmailRegisteredAsAStudentException(EMAIL_REGISTERED_AS_A_STUDENT);

        Optional<DeveloperRol> developerRol = developerRolPersistencePort.findByName(staff.getDeveloperRol().getName());
        if(developerRol.isEmpty()) throw new DeveloperRolNotFoundException(DEVELOPER_ROL_NOT_FOUND);

        Optional<StaffRol> staffRol = staffRolPersistencePort.findByName(staff.getStaffRol().getName());
        if(staffRol.isEmpty()) throw new StaffRolNotFoundException(STAFF_ROL_NOT_FOUND);

        staff.setDeveloperRol(developerRol.get());
        staff.setStaffRol(staffRol.get());

        staffPersistencePort.save(staff);
    }
}
