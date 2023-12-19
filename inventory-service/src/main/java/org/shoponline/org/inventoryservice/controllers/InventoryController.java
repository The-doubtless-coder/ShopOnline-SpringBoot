package org.shoponline.org.inventoryservice.controllers;

import org.shoponline.org.inventoryservice.dtos.InventoryResponse;
import org.shoponline.org.inventoryservice.exceptions.ProductNotAvailableEx;
import org.shoponline.org.inventoryservice.service.IInventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventories")
public class InventoryController {
private final IInventoryService iInventoryService;

    public InventoryController(IInventoryService iInventoryService) {
        this.iInventoryService = iInventoryService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
    public List<InventoryResponse> IsInStock(@RequestParam(name = "skuCode") List<String> skuCode){
        try {
        return iInventoryService.StillInStock(skuCode);
        } catch (ProductNotAvailableEx ex) {
         //log
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "error: " + ex.getMessage() , ex);
        }catch (Exception ex){
            //log
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "error: " + ex.getMessage(), ex);
        }
    }



}
