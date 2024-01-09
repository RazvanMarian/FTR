package com.unibuc.FTR.service.abstractions;

public interface GenericService<T> {
    T save(T dto);

    //plus other basic CRUD operations
}
