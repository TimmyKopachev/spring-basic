package org.dzmitry.kapachou.core.circledependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class NotificationProcessor {

    // @Lazy is the fix for Circle Dependency issue
    @Lazy
    @Autowired
    private AuditingProcessor auditingProcessor;

}
