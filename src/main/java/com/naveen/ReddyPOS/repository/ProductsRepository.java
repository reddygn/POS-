package com.naveen.ReddyPOS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naveen.ReddyPOS.entity.ProductsDao;

public interface ProductsRepository extends JpaRepository<ProductsDao, Integer> {

	List<ProductsDao> findAllByCustomerEmail(String customerEmail);

	List<ProductsDao> findAllByTransactionId(String transactionId);

}
