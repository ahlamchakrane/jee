package ma.enset.Seance8.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import ma.enset.Seance8.dtos.CategoryDTO;
import ma.enset.Seance8.dtos.ProductDTO;
import ma.enset.Seance8.entities.Category;
import ma.enset.Seance8.entities.Product;

@Service
public class CatalogMappersImpl implements CatalogMappersService {
	@Override
	public ProductDTO fromProduct(Product product) {
		ProductDTO productDTO = new ProductDTO();
		BeanUtils.copyProperties(product, productDTO);
		productDTO.setCategoryDTO(fromCategory(product.getCategory()));
		return productDTO;
	}
	@Override
	public Product fromProductDTO(ProductDTO productDTO) {
		Product product = new Product();
		BeanUtils.copyProperties(productDTO, product);
		product.setCategory(fromCategoryDTO(productDTO.getCategoryDTO()));
		return product;
	}
	@Override
	public CategoryDTO fromCategory(Category category) {
		CategoryDTO categoryDTO = new CategoryDTO();
		BeanUtils.copyProperties(category, categoryDTO);
		return categoryDTO;
	}
	@Override
	public Category fromCategoryDTO(CategoryDTO categoryDTO) {
		Category category = new Category();
		BeanUtils.copyProperties(categoryDTO, category);
		return category;
	}
}
