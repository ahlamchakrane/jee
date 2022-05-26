package ma.enset.Seance8.mappers;

import ma.enset.Seance8.dtos.CategoryDTO;
import ma.enset.Seance8.dtos.ProductDTO;
import ma.enset.Seance8.entities.Category;
import ma.enset.Seance8.entities.Product;

public interface CatalogMappersService {
	ProductDTO fromProduct(Product product);
	Product fromProductDTO(ProductDTO productDTO);
	CategoryDTO fromCategory(Category category);
	Category fromCategoryDTO(CategoryDTO categoryDTO);
}
