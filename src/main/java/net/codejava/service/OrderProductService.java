package net.codejava.service;

import net.codejava.entity.OrderProduct;
import net.codejava.entity.Product;
import net.codejava.reposotory.OderProductRepository;
import net.codejava.reposotory.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductService {

    @Autowired
    private OderProductRepository oderProductRepo;

    @Autowired
    private ProductRepository productRepo;

    public List<OrderProduct> listCartItems() {
        return (List<OrderProduct>) oderProductRepo.findAll();
    }

    public Integer addProduct(Long productId, Integer quantity) {
        Integer addedQuantity = quantity;
        Product product = productRepo.findById(productId).get();
        OrderProduct orderProduct = oderProductRepo.findByProduct(product);

        if (orderProduct != null) {
            addedQuantity = orderProduct.getQuantity() + quantity;
            orderProduct.setQuantity(addedQuantity);

        } else {
            orderProduct = new OrderProduct();
            orderProduct.setQuantity(quantity);
            orderProduct.setProduct(product);
        }
        oderProductRepo.save(orderProduct);

        return addedQuantity;

    }


}
