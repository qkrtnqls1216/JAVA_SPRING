package com.springboot.jpa.data.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.data.dao.ProductDAO;
import com.springboot.jpa.data.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.Optional;


// ProductDAOImpl 클래스를 스프링이 관리하는 빈으로 등록 
// 빈으로 등록된 객체는 다른 클래스가 인터페이스를 가지고 의존성 주입을 받을 때 이 구현체를 찾아 주입 
@Component
public class ProductDAOImpl implements ProductDAO {
    
    private ProductRepository productRepository;

    @Autowired
    public ProductDAOImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Product insertProduct(Product product){
        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    @Override
    public Product selectProduct(Long number){
        Product selectedProduct = productRepository.getById(number);

        return selectedProduct;
    }

    @Override
    public Product updateProductName(Long number, String name) throws Exception{
        Optional<Product> selectedProduct = productRepository.findById(number);
        Product updatedProduct;

        if (selectedProduct.isPresent()){
             Product product = selectedProduct.get();

            product.setName(name);
            product.setUpdateAt(LocalDateTime.now());

            updatedProduct = productRepository.save(product);
        }else{
            throw new Exception();
        }

        return updatedProduct;
    }

    @Override
    public void deleteProduct(Long number) throws Exception{
        Optional<Product> selectedProduct = productRepository.findById(number);

        if (selectedProduct.isPresent()){
             Product product = selectedProduct.get();
             productRepository.delete(product);
        }else{
            throw new Exception();
        }
    }

}
