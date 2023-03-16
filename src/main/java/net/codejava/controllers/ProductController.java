package net.codejava.controllers;

import net.codejava.entity.Product;
import net.codejava.handle.ProductNotFoundException;
import net.codejava.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/admin/products")
    public String ShowProductList(Model model) {
        List<Product> listProducts = service.listAll();
        model.addAttribute("listProducts", listProducts);

        return "admin/products/index";
    }

    @GetMapping("/admin/products/new")
    public String showNewForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("pageTitle", "Add New Product");
        return "admin/products/form";
    }

    @PostMapping("/admin/products/save")
    public String saveUser(Product product, RedirectAttributes ra) {
        service.save(product);
        ra.addFlashAttribute("message", "The product has been saved successfully.");
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/edit/{id}")
    public String ShowFormEdit(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        try {
            Product product = service.get(id);
            model.addAttribute("product", product);
            model.addAttribute("pageTitle", "Edit Product (ID: " + id + ")");

            return "admin/products/form";
        }
        catch (ProductNotFoundException ex) {
            ra.addFlashAttribute("message", ex.getMessage() );
            return "redirect:/admin/products";
        }
    }

    @GetMapping("/admin/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The Product ID " + id + " has been deleted.");
        } catch (ProductNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/admin/products";
    }
}
