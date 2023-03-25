package pl.javastart.arch;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
class JobOfferController {

    private final JobOfferService jobOfferService;

    public JobOfferController(JobOfferService jobOfferService) {
        this.jobOfferService = jobOfferService;
    }

 /*   @GetMapping("/offers/{id}")
    Optional<JobOfferDto> getOdderById(@PathVariable Long id) {
        return jobOfferService.getOfferById(id);
    }*/

    @GetMapping("/offers/{id}")
    ResponseEntity<JobOfferDto> getOdderById(@PathVariable Long id) {
        return jobOfferService.getOfferById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
