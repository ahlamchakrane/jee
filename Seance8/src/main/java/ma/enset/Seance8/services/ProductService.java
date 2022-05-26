package ma.enset.Seance8.services;

import java.util.List;
import ma.enset.Seance8.dtos.ProductDTO;

public interface ProductService {
	public ProductDTO save(ProductDTO productDTO);
	public ProductDTO updateProductDTO(ProductDTO productDTO);
	public ProductDTO getProductDTO(String id);
	public void deleteProduct(String id);
	public List<ProductDTO> listProductsDTO();
}
