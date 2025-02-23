package com.example.springmvc.controller;

import com.example.springmvc.model.Product;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class ProductController {

    private final List<Product> productList = new ArrayList<>();
    private final List<Product> cartList = new ArrayList<>();

    @GetMapping("/addproductpage")
    public String showAddProductPage(Model model) {
        model.addAttribute("product", new Product());
        return "addproduct";
    }

    @PostMapping("/products")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addproduct";
        }
        productList.add(product);
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String showProductsPage(Model model) {
        model.addAttribute("products", productList);
        return "products";
    }

    @PostMapping("/removeproduct")
    public String removeProduct(@RequestParam("productNumber") int productNumber) {
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductNumber() == productNumber) {
                iterator.remove();
                break;
            }
        }
        return "redirect:/products";
    }
    @PostMapping("/removefromcart")
    public String removeFromCart(@RequestParam("productNumber") int productNumber) {
        for (int i = 0; i < cartList.size(); i++) {
            if (cartList.get(i).getProductNumber() == productNumber) {
                cartList.remove(i); // Remove only the first matching item
                break; // Exit loop after removing one item
            }
        }
        return "redirect:/cart"; // Stay on cart page after removal
    }


    @PostMapping("/addtocart")
    public String addToCart(@RequestParam("productNumber") int productNumber) {
        for (Product product : productList) {
            if (product.getProductNumber() == productNumber) {
                cartList.add(product);
                break;
            }
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String showCartPage(Model model) {
        double totalPrice = cartList.stream().mapToDouble(Product::getPrice).sum();
        model.addAttribute("cartItems", cartList);
        model.addAttribute("totalPrice", totalPrice);
        return "cart";
    }
//    @GetMapping("/cart")
//    public String showCartPage(Model model) {
//        model.addAttribute("cartItems", cartList);
//        return "cart";
//    }
}
