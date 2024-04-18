package hkmu.comps380f.dao;

import hkmu.comps380f.model.Order;
import hkmu.comps380f.model.TicketUser;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    @Resource
    TicketUserRepository tuRepo;
    @Resource
    OrderRepository oRepo;

    @Transactional
    public void addOrder(String username, String result){
        TicketUser user = tuRepo.findById(username).orElse(null);
        Order order = new Order();
        order.setUser(user);
        order.setDetail(result);
        oRepo.save(order);


    }

    @Transactional
    public List<Order> getOrder(String username){
        TicketUser user = tuRepo.findById(username).orElse(null);
        List<Order> orders = oRepo.findByUser(user);

        return orders;
    }
}
