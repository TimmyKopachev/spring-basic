package org.dzmitry.kapachou.mvc.exception;

import lombok.Data;

@Data
public class WarningMessage {

    String protocol;
    String message;

    public WarningMessage(String protocol, String message) {
        this.protocol = protocol;
        this.message = message;
    }
}
