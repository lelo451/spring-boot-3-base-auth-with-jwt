package com.uem.contas.service;

import com.uem.contas.model.Person;
import com.uem.contas.model.Release;
import com.uem.contas.repository.PersonRepository;
import com.uem.contas.repository.ReleaseRepository;
import com.uem.contas.repository.UserRepository;
import com.uem.contas.service.exception.NonExistentOrInactivePersonException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReleaseService {

    private static final Logger logger = LoggerFactory.getLogger(ReleaseService.class);

    private PersonRepository personRepository;
    private ReleaseRepository releaseRepository;
    private UserRepository userRepository;

    public Release save(Release release) {
        validatePerson(release);
        return releaseRepository.save(release);
    }

    public Release update(Long id, Release release) {
        Release savedRelease = searchExistingRelease(id);
        if (!release.getPerson().equals(savedRelease.getPerson())) {
            validatePerson(release);
        }

        BeanUtils.copyProperties(release, savedRelease, "id");
        return releaseRepository.save(savedRelease);
    }

    private void validatePerson(Release release) {
        Optional<Person> person = null;
        if (release.getPerson().getId() != null) {
            person = personRepository.findById(release.getPerson().getId());
        }

        if (person.isEmpty() || person.get().isInactive()) {
            throw new NonExistentOrInactivePersonException();
        }
    }

    private Release searchExistingRelease(Long id) {
        return releaseRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

}
