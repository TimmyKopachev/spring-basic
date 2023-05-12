package org.dzmitry.kapachou.mvc.model;

import lombok.*;

import java.util.UUID;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Employee {

    private Long id = UUID.randomUUID().getMostSignificantBits();
    private String name;
    private EmployeePosition position;
}
