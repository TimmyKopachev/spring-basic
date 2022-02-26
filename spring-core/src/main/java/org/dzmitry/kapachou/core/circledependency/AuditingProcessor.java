package org.dzmitry.kapachou.core.circledependency;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuditingProcessor {

    @Autowired
    private NotificationProcessor notificationProcessor;

}
