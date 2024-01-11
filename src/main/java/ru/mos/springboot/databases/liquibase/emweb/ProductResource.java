package ru.mos.springboot.databases.liquibase.emweb;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductResource {

    private final ProductService productService;

    @PostMapping
    public Product save(@RequestBody Product product) {
        return this.productService.save(product);
    }

    @GetMapping
    public Page<Product> findAll(@RequestParam(defaultValue = "0", required = false) Integer page, @RequestParam(defaultValue = "10", required = false) Integer size) {
        return this.productService.findAll(PageRequest.of(page, size));
    }

}
