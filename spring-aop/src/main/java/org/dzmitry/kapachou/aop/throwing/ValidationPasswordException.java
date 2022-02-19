package org.dzmitry.kapachou.aop.throwing;

import lombok.Data;

@Data
public class ValidationPasswordException extends RuntimeException {

    private String expiredPassword;

    public ValidationPasswordException(String password) {
        super(String.format("Password [%s] has been already expired!", password));
        this.expiredPassword = password;
    }
}
