package com.looped.foodordering.repository;

import com.looped.foodordering.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User: looped
 * DateTime: 2022/11/28 15:56
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

}
