package com.example.grainsilomanagement.controller;

import com.example.grainsilomanagement.entity.GoodsItem;
import com.example.grainsilomanagement.service.GoodsItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goods-items")
@CrossOrigin
public class GoodsItemController {

    private final GoodsItemService service;

    public GoodsItemController(GoodsItemService service) {
        this.service = service;
    }

    @PostMapping
    public GoodsItem createGoodsItem(@RequestBody GoodsItem goodsItem) {
        return service.createGoodsItem(goodsItem);
    }

    @GetMapping
    public List<GoodsItem> getAllGoodsItems() {
        return service.getAllGoodsItems();
    }

    @GetMapping("/{id}")
    public GoodsItem getGoodsItemById(@PathVariable Long id) {
        return service.getGoodsItemById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteGoodsItem(@PathVariable Long id) {
        service.deleteGoodsItem(id);
        return "Goods item deleted successfully";
    }
}