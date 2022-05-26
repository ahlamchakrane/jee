package ma.enset.Seance8.web;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import ma.enset.Seance8.dtos.ProductDTO;
import ma.enset.Seance8.services.ProductService;

@RestController
@AllArgsConstructor


public class ProductRestController {
	private ProductService productService;
	@GetMapping(path= "/products")
	public List<ProductDTO> productList(){
		return productService.listProductsDTO();
	}
	@GetMapping(path = "/products/{id}")
	public ProductDTO getProducts(@PathVariable(name = "id") String id){
		return productService.getProductDTO(id);
	}
	@PostMapping(path = "products")
	public ProductDTO saveProduct(@RequestBody ProductDTO productDTO) {
		return productService.save(productDTO);
	}
	@PutMapping(path = "products/{id}")
	public ProductDTO updateProduct(@RequestBody ProductDTO productDTO, @PathVariable String id) {
		productDTO.setId(id);
		return productService.updateProductDTO(productDTO);
	}
	@DeleteMapping(path = "products/{id}")
	public void deleteProduct(@PathVariable String id) {
		productService.deleteProduct(id);
	}
}
