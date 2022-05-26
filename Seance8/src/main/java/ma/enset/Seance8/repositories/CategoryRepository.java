package ma.enset.Seance8.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import ma.enset.Seance8.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
