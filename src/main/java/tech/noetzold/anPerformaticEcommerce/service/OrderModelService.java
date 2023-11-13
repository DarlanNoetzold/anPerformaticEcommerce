package tech.noetzold.anPerformaticEcommerce.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.OrderModel;
import tech.noetzold.anPerformaticEcommerce.repository.OrderRepository;

import java.util.List;
import java.util.UUID;

@Service
@Cacheable("order_model")
public class OrderModelService {

    @Autowired
    OrderRepository orderRepository;

    @Transactional
    public List<OrderModel> findAllOrder(){
        return orderRepository.findAll();
    }

    @Transactional
    public OrderModel findOrderById(UUID id){
        return orderRepository.findById(id).orElse(null);
    }

    @Transactional
    public OrderModel updateOrder(UUID id, OrderModel order){
        order.setOrderId(id);
        return orderRepository.save(order);
    }
    
    @Transactional
    public OrderModel saveOrder(OrderModel order){
        return orderRepository.save(order);
    }

    @Transactional
    public void deleteOrder(UUID id){
        orderRepository.deleteById(id);
    }
}
