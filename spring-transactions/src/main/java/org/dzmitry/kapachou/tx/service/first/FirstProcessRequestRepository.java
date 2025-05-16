package org.dzmitry.kapachou.tx.service.first;

import org.dzmitry.kapachou.tx.model.ProcessRequest;
import org.springframework.data.repository.CrudRepository;

public interface FirstProcessRequestRepository extends CrudRepository<ProcessRequest, Long> {
}
