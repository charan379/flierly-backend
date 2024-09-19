package com.ctytech.flierly.genric.service;

import com.ctytech.flierly.FlierlyException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GenericService<T, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    void deleteById(ID id);

    Page<T> page(Map<String, Object> filters, Integer pageNo, Integer resultsPerPage) throws FlierlyException;
}
