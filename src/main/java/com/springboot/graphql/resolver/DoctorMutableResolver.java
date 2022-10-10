package com.springboot.graphql.resolver;
import com.springboot.graphql.model.Department;
import com.springboot.graphql.model.Doctor;
import com.springboot.graphql.repository.DepartmentRepository;
import com.springboot.graphql.repository.DoctorRepository;
import com.springboot.graphql.request.DoctorInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorMutableResolver implements GraphQLMutationResolver {


    DoctorRepository doctorRepository;
    DepartmentRepository departmentRepository;


    @Autowired
    public DoctorMutableResolver(DoctorRepository doctorRepository, DepartmentRepository departmentRepository ) {
        this.doctorRepository = doctorRepository;
        this.departmentRepository = departmentRepository;
    }

    public Doctor newDoctor(DoctorInput doctorInput) {
        Department department = departmentRepository.findById(doctorInput.getDepartmentId()).get();

        return doctorRepository.save(new Doctor(null, doctorInput.getFirstName(), doctorInput.getLastName(),
                doctorInput.getPosition(), doctorInput.getAge(), doctorInput.getSalary(),
                doctorInput.getBirthday(),
                department));
    }

    public Doctor updateDoctor(Integer id, DoctorInput doctorInput) {
        Department department = departmentRepository.findById(doctorInput.getDepartmentId()).get();

        return doctorRepository.save(new Doctor(id, doctorInput.getFirstName(), doctorInput.getLastName(),
                doctorInput.getPosition(), doctorInput.getAge(), doctorInput.getSalary(),
                doctorInput.getBirthday(),
                department));
    }

    public boolean deleteDoctor(Integer id){
        doctorRepository.deleteById(id);
        return true;
    }

}
