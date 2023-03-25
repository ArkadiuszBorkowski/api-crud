package pl.javastart.arch.joboffer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/offers")
class JobOfferController {

    private final JobOfferService jobOfferService;

    public JobOfferController(JobOfferService jobOfferService) {
        this.jobOfferService = jobOfferService;
    }

 /*   @GetMapping("/offers/{id}")
    Optional<JobOfferDto> getOdderById(@PathVariable Long id) {
        return jobOfferService.getOfferById(id);
    }*/

    @GetMapping("/{id}")
    ResponseEntity<JobOfferDto> getOdderById(@PathVariable Long id) {
        return jobOfferService.getOfferById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<JobOfferDto> saveOffer(@RequestBody JobOfferDto jobOfferDto) {
        JobOfferDto savedJobOffer = jobOfferService.saveOffer(jobOfferDto);
        URI savedCompanyUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(("/{id}"))
                .buildAndExpand(savedJobOffer.getId())
                .toUri();
        return ResponseEntity.created(savedCompanyUri).body(savedJobOffer);
    }
}
