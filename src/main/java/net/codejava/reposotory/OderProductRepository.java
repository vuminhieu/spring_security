package net.codejava.reposotory;

import net.codejava.entity.OrderProduct;
import net.codejava.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OderProductRepository extends JpaRepository<OrderProduct, Long> {

}
