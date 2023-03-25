package pl.javastart.arch;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class JobOfferService {

    private JobOfferRepository jobOfferRepository;
    private JobOfferDtoMapper jobOfferDtoMapper;

    public JobOfferService(JobOfferRepository jobOfferRepository, JobOfferDtoMapper jobOfferDtoMapper) {
        this.jobOfferRepository = jobOfferRepository;
        this.jobOfferDtoMapper = jobOfferDtoMapper;
    }

    Optional<JobOfferDto> getOfferById(Long id) {
        return jobOfferRepository.findById(id).map(jobOfferDtoMapper::map);
    }
}
