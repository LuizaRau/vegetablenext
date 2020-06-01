package pl.pila.vegetable.Usteni.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.pila.vegetable.Usteni.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {

}
