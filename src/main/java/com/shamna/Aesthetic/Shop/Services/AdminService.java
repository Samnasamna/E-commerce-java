package com.shamna.Aesthetic.Shop.Services;

import com.shamna.Aesthetic.Shop.Entities.Login;
import com.shamna.Aesthetic.Shop.Entities.ProductDetails;
import com.shamna.Aesthetic.Shop.Repositories.LoginRepository;
import com.shamna.Aesthetic.Shop.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final ProductRepository productRepository;
    private final LoginRepository loginRepo;

    public AdminService(ProductRepository productRepository, LoginRepository loginRepo) {
        this.productRepository = productRepository;
        this.loginRepo = loginRepo;
    }

    public Optional<ProductDetails> addProducts(ProductDetails product) {
        return Optional.of(productRepository.save(product));
    }

    public Optional<ProductDetails> updateProducts(ProductDetails product) {
        return productRepository.findById(product.getId()).map((newProduct)->{
            newProduct.setDescription(product.getDescription());
            newProduct.setImg(product.getImg());
            newProduct.setName(product.getName());
            newProduct.setPrice(product.getPrice());
            newProduct.setStock(product.isStock());
            return productRepository.save(newProduct);
        });
    }

    public String deleteProduct(int id) {
        if(productRepository.findById(id).isPresent()){
            productRepository.deleteById(id);
            return "deleted";
        }
        else{
            return "product does not exist";
        }
    }

    public List<Login> getProducts() {
        return loginRepo.findAll();
    }
}
