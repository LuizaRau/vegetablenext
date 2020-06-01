package pl.pila.vegetable.Usteni.repository;

import org.springframework.data.repository.CrudRepository;
import pl.pila.vegetable.Usteni.model.Order;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {


}
