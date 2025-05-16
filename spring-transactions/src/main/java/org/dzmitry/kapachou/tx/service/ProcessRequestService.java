package org.dzmitry.kapachou.tx.service;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dzmitry.kapachou.tx.model.ProcessRequest;
import org.dzmitry.kapachou.tx.service.first.FirstProcessRequestRepository;
import org.dzmitry.kapachou.tx.service.second.SecondProcessRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ProcessRequestService {

    final FirstProcessRequestRepository firstRepository;
    final SecondProcessRequestRepository secondRepository;

    @PostConstruct
    public void init() {
        // init mock process request.
        var pr = firstRepository.save(buildProcessRequest());
        // validate
        firstRepository.findById(pr.getId())
                .orElseThrow(() -> new RuntimeException("Could not save mock ProcessRequest."));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void distributedProcessRequestUpdate() {
        firstRepository.findAll()
                .forEach(pr -> {
                    firstRepository.delete(pr);
                    pr.setStatus(ProcessRequest.RequestStatus.APPROVED);
                    secondRepository.save(pr);
                });

        log.info("Check that first-repo does not have ProcessRequest entity.");
        firstRepository.findAll().forEach(pr -> log.info("{}", pr));

        log.info("Check that second-repo contains updatedProcessRequest entity.");
        secondRepository.findAll().forEach(pr -> log.info("{}", pr));
    }

    private static ProcessRequest buildProcessRequest() {
        ProcessRequest pr = new ProcessRequest();
        pr.setStatus(ProcessRequest.RequestStatus.OPEN);
        pr.setDescription("Request to learn distributed transactions cross multiple DB clusters.");
        return pr;
    }
}
