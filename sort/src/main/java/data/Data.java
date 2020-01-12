package data;

import lombok.Getter;
import lombok.Setter;

public final class Data {
    @Getter @Setter
    private String firstName = "";
    @Getter @Setter
    private String lastName = "";
    @Getter @Setter
    private int age = 0;

    public Data(final String firstName, final String lastName, final int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "{firstName : " + this.firstName + ", lastName : " + lastName + ", age : " + age + "}";
    }
}
