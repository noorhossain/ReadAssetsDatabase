package first.learn.databasereadlearn;

public class ModelStudent {


    String StudentId, Name, RollNumber, Age, Address, PhoneNumber;

    public ModelStudent() {
    }


    public ModelStudent(String studentId, String name, String rollNumber, String age, String address, String phoneNumber) {
        StudentId = studentId;
        Name = name;
        RollNumber = rollNumber;
        Age = age;
        Address = address;
        PhoneNumber = phoneNumber;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRollNumber() {
        return RollNumber;
    }

    public void setRollNumber(String rollNumber) {
        RollNumber = rollNumber;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
}
