package com.unibuc.FTR.service.abstractions;

public interface GenericService<T> {
    T save(T dto);
}
