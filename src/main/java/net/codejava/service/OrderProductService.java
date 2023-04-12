package net.codejava.service;

import net.codejava.entity.OrderProduct;
import net.codejava.entity.Product;
import net.codejava.entity.User;
import net.codejava.reposotory.OderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductService {

    @Autowired
    private OderProductRepository oderProductRepo;

    public List<OrderProduct> listCartItems() {
        return (List<OrderProduct>) oderProductRepo.findAll();
    }



}
