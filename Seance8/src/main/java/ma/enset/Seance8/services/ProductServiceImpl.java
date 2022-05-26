package ma.enset.Seance8.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.enset.Seance8.dtos.ProductDTO;
import ma.enset.Seance8.entities.Product;
import ma.enset.Seance8.mappers.CatalogMappersService;
import ma.enset.Seance8.repositories.ProductRepository;

@Service
@Transactional 
public class ProductServiceImpl implements ProductService {
	private ProductRepository productRepository;
	private CatalogMappersService catalogMappers;
	
	public ProductServiceImpl(ProductRepository productRepository, CatalogMappersService catalogMappers) {
		this.productRepository = productRepository;
		this.catalogMappers = catalogMappers;
	}
	@Override
	public ProductDTO save(ProductDTO productDTO) {
		/*Product product = new Product();
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		Category category = new Category();
		category.setId(productDTO.getCategory().getId());
		category.setName(productDTO.getCategory().getName());
		product.setCategory(category);
		Product savedProduct = productRepository.save(product);*/
		
		Product product = catalogMappers.fromProductDTO(productDTO);
		product.setId(UUID.randomUUID().toString());
		Product savedProduct = productRepository.save(product);
		return catalogMappers.fromProduct(savedProduct);
	}
	@Override
	public List<ProductDTO> listProductsDTO(){
		List<Product> products = productRepository.findAll();
		List<ProductDTO> productDTOs= 
				products.stream().map(p->catalogMappers.fromProduct(p))
				.collect(Collectors.toList());
		return productDTOs;
	}
	@Override
	public ProductDTO updateProductDTO(ProductDTO productDTO) {
		Product product = catalogMappers.fromProductDTO(productDTO);
		Product savedProduct = productRepository.save(product);
		return catalogMappers.fromProduct(savedProduct);
	}
	@Override
	public ProductDTO getProductDTO(String id) {
		Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
		return catalogMappers.fromProduct(product);
	}
	@Override
	public void deleteProduct(String id) {
		productRepository.deleteById(id);
	}
	
	
	
}
