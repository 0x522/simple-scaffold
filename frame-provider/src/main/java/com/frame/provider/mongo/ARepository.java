package com.frame.provider.mongo;

import com.frame.provider.model.entity.A;

/**
 * mongodb ops
 *
 * @author Admin
 */
public interface ARepository {

    void saveA(A a);


    void updateA(A a);

    void deleteById(Long id);
}
