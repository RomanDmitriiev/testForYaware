package testPackege;

import com.github.javafaker.Faker;
import net.bytebuddy.utility.RandomString;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String getRandomTestMail(int domainLength) {
        String randomString = RandomString.make(domainLength);
        return randomString + "@testmail.com";
    }

    private String getValidFirstName() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        return firstName;
    }

    private String getValidMiddleName() {
        Faker faker = new Faker();
        String mioddleName = faker.name().firstName();
        return mioddleName;
    }

    private String getValidLastName() {
        Faker faker = new Faker();
        String lastName = faker.name().lastName();
        return lastName;
    }

    private String getGroupName() {
        Faker faker = new Faker();
        String groupName = faker.company().name();
        return groupName;
    }

    public List<String> getEmployee() {
        List<String> employee = new ArrayList<>();
        employee.add(getValidFirstName());
        employee.add(getValidMiddleName());
        employee.add(getValidLastName());
        employee.add(getRandomTestMail(11));
        employee.add(getGroupName());
        return employee;
    }
}
