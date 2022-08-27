package derakhshani.arad.entity;

public class Student {
    private Integer id;
    private String firstName;
    private String lastName;
    private Float score;
    private Integer studentNumber;
    private String address;
    private String nationalCode;
    private Boolean isDeleted;
    private Integer year;

    public Student() {
    }

    public Student(String firstName, String lastName, Float score, Integer studentNumber, String address, String nationalCode, Boolean isDeleted, Integer year) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.score = score;
        this.studentNumber = studentNumber;
        this.address = address;
        this.nationalCode = nationalCode;
        this.isDeleted = isDeleted;
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", score=" + score +
                ", studentNumber=" + studentNumber +
                ", address='" + address + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", isDeleted=" + isDeleted +
                ", year=" + year +
                '}';
    }
}
