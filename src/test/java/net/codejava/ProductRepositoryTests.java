package net.codejava;


import net.codejava.entity.Product;
import net.codejava.reposotory.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProductRepositoryTests {
    @Autowired
    private ProductRepository repo;

    @Test
    public void testAddNew() {
        Product product = new Product();
        product.setName("May Tinh De Ban");
        product.setDescription("Khong co mo ta");
        product.setImage("hieu.img");
        product.setPrice(2022.99);
        product.setQuantity(3);
        Product saveProduct = repo.save(product);

        assertThat(saveProduct).isNotNull();
        assertThat(saveProduct.getId()).isGreaterThan(0);
    }

    @Test
    public void TestListAllProduct() {
        List<Product> products = repo.findAll();

        assertThat(products).hasSizeGreaterThan(0);
       for (Product product : products) {
           System.out.println(product);
       }
    }

    @Test
    public void TestUpdateProduct() {
        Long IDproduct = 1L;
        Optional<Product> optionalProduct = repo.findById(IDproduct);
        Product product = optionalProduct.get();
        product.setName("May Tinh LapTop");
        repo.save(product);

        Product updateProduct = repo.findById(IDproduct).get();
        assertThat(updateProduct.getName()).isEqualTo("May Tinh LapTop");
    }

    @Test
    public void TestGetProduct() {
        Long IDproduct = 3L;
        Optional<Product> optionalProduct = repo.findById(IDproduct);
        assertThat(optionalProduct).isPresent();
        System.out.println(optionalProduct.get());
    }

    @Test
    public void TestDeleteProduct() {
        Long IDproduct = 4L;
        repo.deleteById(IDproduct);
        Optional<Product> optionalProduct = repo.findById(IDproduct);
        assertThat(optionalProduct).isNotPresent();
    }

}
