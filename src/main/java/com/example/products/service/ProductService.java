package com.example.products.service;

import com.example.products.entity.Product;
import com.example.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Récupérer tous les produits avec pagination
//    public List<Product> getProducts(int page, int perPage) {
//        return productRepository.findAll(PageRequest.of(page, perPage)).getContent();
//    }
public Page<Product> getProducts(Pageable pageable) {
    return productRepository.findAll(pageable);
}


    // Récupérer un produit par ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Ajouter un nouveau produit
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // Mettre à jour un produit
    public Optional<Product> updateProduct(Long id, Product product) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setImage(product.getImage());
                    existingProduct.setPrice(product.getPrice());
                    existingProduct.setRating(product.getRating());
                    return productRepository.save(existingProduct);
                });
    }

    // Supprimer un produit
    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }


//    public Page<Product> getProducts(int page, int size) {
//        Pageable pageable = PageRequest.of(page, size); // Correct usage
//        return productRepository.findAll(pageable);
//    }
}
