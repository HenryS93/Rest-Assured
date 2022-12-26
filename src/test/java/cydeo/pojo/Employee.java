package cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name") // THIS: Annotation comes from Jackson Library it will bind the response with our instance variable to get data
    private String lastName;

    private int salary;













}
