package net.codejava.service;

import net.codejava.entity.Order;
import net.codejava.entity.Product;
import net.codejava.handle.ProductNotFoundException;
import net.codejava.reposotory.OrderRepository;
import net.codejava.reposotory.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    public List<Order> listAll() {
        return (List<Order>) repo.findAll();
    }

    public void save(Order order) {
        repo.save(order);
    }


    public void delete(Long id) throws ProductNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new ProductNotFoundException("Cound not find any order with id" + id);
        }
        repo.deleteById(id);
    }
}
