package tacos.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import tacos.repository.OrderRepository;

@Service
public class OrderAdminService {

    private OrderRepository orderRepository;

    public OrderAdminService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public long getAllOrders() {
        return orderRepository.count();
    }
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }

//    @PostAuthorize("hasRole('ADMIN')" || " + "returnObject.user.username == authentication.name")
//    public Optional<TacoOrder> getOrder(long id) {
//        return orderRepository.findById(id);
//    }
}
