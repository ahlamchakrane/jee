package ma.enset.Seance8.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.enset.Seance8.entities.Product;

public interface ProductRepository extends JpaRepository<Product,String>{

}
