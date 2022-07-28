package org.example.singleton;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ExampleForLombok {

private String name;
private int age;
private String specialty;

    public ExampleForLombok() {
        this.getAge();
    }
}
