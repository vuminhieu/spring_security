package net.codejava.service;


import net.codejava.entity.Product;
import net.codejava.handle.ProductNotFoundException;
import net.codejava.reposotory.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;

    public List<Product> listAll() {
        return (List<Product>) repo.findAll();
    }

    public void save(Product product) {
        repo.save(product);
    }

    public Product get(Long id) throws ProductNotFoundException {
        Optional<Product> product = repo.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new ProductNotFoundException("Cound not find any product with id" + id);
    }

    public void delete(Long id) throws ProductNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new ProductNotFoundException("Cound not find any product with id" + id);
        }
        repo.deleteById(id);
    }
}
