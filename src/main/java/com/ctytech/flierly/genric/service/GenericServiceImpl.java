package com.ctytech.flierly.genric.service;

import com.ctytech.flierly.FlierlyException;
import com.ctytech.flierly.genric.repository.GenericRepository;
import com.ctytech.flierly.utility.GenericSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("genericServiceImpl")
public class GenericServiceImpl<T, ID> implements GenericService<T, ID> {

    private final GenericRepository<T, ID> repository;

    @Autowired
    public GenericServiceImpl(GenericRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public Page<T> page(Map<String, Object> filters, Integer pageNo, Integer resultsPerPage) throws FlierlyException {

        Pageable pageable = PageRequest.of(pageNo, resultsPerPage);

        return repository.findAll(GenericSpecification.getEntityByFilter(filters), pageable);
    }


}

