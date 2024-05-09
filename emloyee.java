package application;
import java.sql.Date;
import java.sql.Connection;
public class emloyee {
    private int empID;
    private int age;
    private int salary;
    private Date birthDate;
    private String firstName;
    private String lastName;
    private long phone;
    private String address;
    private String shopName;

    // Constructor
    public emloyee(int empID, int age, int salary, Date birthDate, String firstName, String lastName, long phone, String address, String shopName) {
        this.empID = empID;
        this.age = age;
        this.salary = salary;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.shopName = shopName;
    }

    
	// Getters and setters for each field
    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}

