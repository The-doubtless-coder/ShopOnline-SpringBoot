package org.shoponline.org.inventoryservice.service;

import org.shoponline.org.inventoryservice.dtos.InventoryResponse;
import org.shoponline.org.inventoryservice.exceptions.ProductNotAvailableEx;

import java.util.List;

public interface IInventoryService {

    public List<InventoryResponse> StillInStock(List<String> skuCode) throws Exception;
}
