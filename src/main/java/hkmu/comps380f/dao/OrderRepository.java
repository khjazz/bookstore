package hkmu.comps380f.dao;

import hkmu.comps380f.model.Order;
import hkmu.comps380f.model.TicketUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(TicketUser user);
}