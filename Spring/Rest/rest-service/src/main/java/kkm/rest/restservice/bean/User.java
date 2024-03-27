package kkm.rest.restservice.bean;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class User {

    private Integer id;

    @Size(min = 2)
    private String name;

    private Date joinDate;
}
