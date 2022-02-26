package org.dzmitry.kapachou.mvc.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Employee {

    private final Long id = UUID.randomUUID().getMostSignificantBits();
    private final String name;
    private final EmployeePosition position;
}
