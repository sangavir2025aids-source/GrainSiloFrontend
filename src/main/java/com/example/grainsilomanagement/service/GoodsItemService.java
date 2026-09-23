package com.example.grainsilomanagement.service;

import com.example.grainsilomanagement.entity.GoodsItem;
import com.example.grainsilomanagement.repository.GoodsItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsItemService {

    private final GoodsItemRepository repository;

    public GoodsItemService(GoodsItemRepository repository) {
        this.repository = repository;
    }

    public GoodsItem createGoodsItem(GoodsItem goodsItem) {

        if (goodsItem.getStockQuantity() == null) {
            goodsItem.setStockQuantity(0);
        }

        return repository.save(goodsItem);
    }

    public List<GoodsItem> getAllGoodsItems() {
        return repository.findAll();
    }

    public GoodsItem getGoodsItemById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteGoodsItem(Long id) {
        repository.deleteById(id);
    }
}