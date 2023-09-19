package com.uem.contas.repository.release;

import com.uem.contas.dto.ReleaseStatisticsCategoryDto;
import com.uem.contas.dto.ReleaseStatisticsDayDto;
import com.uem.contas.dto.ReleaseStatisticsPersonDto;
import com.uem.contas.model.Release;
import com.uem.contas.repository.filter.ReleaseFilter;
import com.uem.contas.repository.projection.ReleaseSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface ReleaseRepositoryQuery {

    List<ReleaseStatisticsPersonDto> byPerson(LocalDate start, LocalDate end);
    List<ReleaseStatisticsCategoryDto> byCategory(LocalDate referenceMonth);
    List<ReleaseStatisticsDayDto> byDay(LocalDate referenceMonth);

    Page<Release> filter(ReleaseFilter filter, Pageable pageable);
    Page<ReleaseSummary> summarize(ReleaseFilter filter, Pageable pageable);

}
