package tech.noetzold.anPerformaticEcommerce.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.model.Order;
import tech.noetzold.anPerformaticEcommerce.repository.OrderRepository;

import java.util.List;
import java.util.UUID;

@Service
@Cacheable("order")
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Transactional
    public List<Order> findAllOrder(){
        return orderRepository.findAll();
    }

    @Transactional
    public Order findOrderById(UUID id){
        return orderRepository.findById(id).orElse(null);
    }

    @Transactional
    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }

    @Transactional
    public void deleteOrder(UUID id){
        orderRepository.deleteById(id);
    }
}
