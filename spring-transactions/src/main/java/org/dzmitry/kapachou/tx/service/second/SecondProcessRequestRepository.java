package org.dzmitry.kapachou.tx.service.second;

import org.dzmitry.kapachou.tx.model.ProcessRequest;
import org.springframework.data.repository.CrudRepository;

public interface SecondProcessRequestRepository extends CrudRepository<ProcessRequest, Long> {

}
