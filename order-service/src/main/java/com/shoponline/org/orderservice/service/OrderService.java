package com.shoponline.org.orderservice.service;

import com.shoponline.org.orderservice.dtos.InventoryResponse;
import com.shoponline.org.orderservice.dtos.OrderLineItemsDTO;
import com.shoponline.org.orderservice.dtos.OrderRequestDTO;
import com.shoponline.org.orderservice.exceptions.ProductNotInStockException;
import com.shoponline.org.orderservice.models.Order;
import com.shoponline.org.orderservice.models.OrderLineItems;
import com.shoponline.org.orderservice.repository.IOrderRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService implements IOrderService {

    private final ModelMapper modelMapper = new ModelMapper();
    private final IOrderRepository iOrderRepository;
    private final WebClient.Builder webClientBuilder;

    public OrderService(IOrderRepository iOrderRepository, WebClient.Builder webClientBuilder) {
        this.iOrderRepository = iOrderRepository;
        this.webClientBuilder = webClientBuilder;
    }

    @Transactional
    public void CreateOrder(OrderRequestDTO orderRequestDTO) throws ProductNotInStockException {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> collect = orderRequestDTO.getOrderLineItemsDTOList()
                .stream().map(this::mapToEntity).toList();
        order.setOrderlineItemsList(collect);
        //call inventory serv and place order if it is in stock
        List<String> skuCodeList = order.getOrderlineItemsList().stream()
                .map(OrderLineItems::getSkuCode).toList();

        InventoryResponse[] response = webClientBuilder.build()
                .get()
                .uri("http://inventory-service/api/v1/inventories", uriBuilder
                        -> uriBuilder.queryParam("skuCode", skuCodeList).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class).block();
        assert response != null;
      //log.info("response", response[0].toString());
        boolean isInStock = Arrays.stream(response).allMatch(InventoryResponse::isInStock);
        if(isInStock){
            iOrderRepository.save(order);
        }else
        {
            throw new ProductNotInStockException("product is out of stock!");
        }

        //log that it has been saved
    }

    private OrderLineItems mapToEntity(OrderLineItemsDTO orderLineDTOs) {
        return modelMapper.map(orderLineDTOs, OrderLineItems.class);
    }





}
