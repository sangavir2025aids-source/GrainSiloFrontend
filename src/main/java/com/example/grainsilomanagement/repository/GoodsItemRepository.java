package com.example.grainsilomanagement.repository;

import com.example.grainsilomanagement.entity.GoodsItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsItemRepository extends JpaRepository<GoodsItem, Long> {
}