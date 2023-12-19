package org.shoponline.org.inventoryservice.service;

import org.shoponline.org.inventoryservice.dtos.InventoryResponse;
import org.shoponline.org.inventoryservice.exceptions.ProductNotAvailableEx;
import org.shoponline.org.inventoryservice.models.Inventory;
import org.shoponline.org.inventoryservice.repository.IInventoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class InventoryService implements IInventoryService{

    private final IInventoryRepository iInventoryRepository;

    public InventoryService(IInventoryRepository iInventoryRepository) {
        this.iInventoryRepository = iInventoryRepository;
    }

    public List<InventoryResponse> StillInStock(List<String> skuCode) throws Exception {
//        List<Inventory> bySkuCode = iInventoryRepository.findBySkuCodeIn(skuCode);
//        if(bySkuCode.isEmpty()){
//            throw new Exception("no product available for provided skuCodes");
//        }
        return GetInventoryInfo(skuCode);

//        return bySkuCode.stream().map(Inventory->
//            InventoryResponse.builder()
//                    .skuCode(Inventory.getSkuCode())
//                    .isInStock(Inventory.getQuantity()>0).build()).toList();

//        if(bySkuCode.isEmpty()){
//            throw new ProductNotAvailableEx(
//                    "product of code::" + skuCode + " is not available"
//            );
//        }
//        if(bySkuCode.get().getQuantity() > 0){
//            return true;
//        }
//        return false;
    }

    private List<InventoryResponse> GetInventoryInfo(List<String> skuCodes){
        var inventoryResponses = new ArrayList<InventoryResponse>();
        for(String value: skuCodes){
            Optional<Inventory> bySkuCode = iInventoryRepository.findBySkuCode(value);
            if(bySkuCode.isEmpty()){
                inventoryResponses.add(new InventoryResponse(false, value, false));
            }
            bySkuCode.ifPresent(inventory -> inventoryResponses
                    .add(new InventoryResponse(true, value, inventory.getQuantity() > 0)));

        }
        return inventoryResponses;
    }
}
