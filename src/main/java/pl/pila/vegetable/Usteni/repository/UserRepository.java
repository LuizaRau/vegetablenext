package pl.pila.vegetable.Usteni.repository;

import org.springframework.data.repository.CrudRepository;
import pl.pila.vegetable.Usteni.model.Users;

public interface UserRepository extends CrudRepository<Users, Long> {
}