package net.codejava.reposotory;

import net.codejava.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    public Long countById(Long id);
}
