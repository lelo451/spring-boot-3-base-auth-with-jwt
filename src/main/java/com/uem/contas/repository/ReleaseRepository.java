package com.uem.contas.repository;

import com.uem.contas.model.Release;
import com.uem.contas.repository.release.ReleaseRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReleaseRepository extends JpaRepository<Release, Long>, ReleaseRepositoryQuery {

    List<Release> findByDueDateLessThanEqualAndDateOfPaymentIsNull(LocalDate date);

}
