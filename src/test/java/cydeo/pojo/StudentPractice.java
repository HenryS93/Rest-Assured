package cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class StudentPractice {

    private String firstName;
    private int batch;
    private String major;
    private Contact contact;
    private String companyName;



}
