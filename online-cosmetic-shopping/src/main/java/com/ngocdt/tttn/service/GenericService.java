package com.ngocdt.tttn.service;

import java.util.List;

public interface GenericService<T,K>  {
    List<T> showAll();
    T showOne(K id);
    T update(T dto);
    T create(T dto);
    void delete(K id);
}
