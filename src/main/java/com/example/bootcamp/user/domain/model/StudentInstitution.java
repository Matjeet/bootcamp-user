package com.example.bootcamp.user.domain.model;

public class StudentInstitution {

    private Long id;
    private Institution institution;
    private Student student;
    private String institutionDetail;
    private String degreeName;

    public StudentInstitution(Long id, Institution institution, Student student, String institutionDetail, String degreeName) {
        this.id = id;
        this.institution = institution;
        this.student = student;
        this.institutionDetail = institutionDetail;
        this.degreeName = degreeName;
    }

    public StudentInstitution() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getInstitutionDetail() {
        return institutionDetail;
    }

    public void setInstitutionDetail(String institutionDetail) {
        this.institutionDetail = institutionDetail;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }
}
