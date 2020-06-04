package pl.pila.vegetable.Usteni.repository;

import org.springframework.data.repository.CrudRepository;
import pl.pila.vegetable.Usteni.model.OrderProduct;
import pl.pila.vegetable.Usteni.model.Product;

import java.util.List;
import java.util.Set;


public interface OrderProductRepository extends CrudRepository<OrderProduct, Integer> {

    List<OrderProduct> findOrderProductsByOrder_Id(Integer id);

}
