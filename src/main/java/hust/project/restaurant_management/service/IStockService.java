package hust.project.restaurant_management.service;

import hust.project.restaurant_management.entity.dto.request.QuantityStockRequest;


public interface IStockService {
    void subtractQuantityStock(QuantityStockRequest request);
}
