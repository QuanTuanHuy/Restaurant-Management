package hust.project.restaurant_management.controller;

import hust.project.restaurant_management.entity.dto.request.*;
import hust.project.restaurant_management.entity.dto.response.Resource;
import hust.project.restaurant_management.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_PAGE_SIZE = "15";

    private final IProductService productService;

    @PostMapping
    public ResponseEntity<Resource> createProduct(@RequestBody CreateProductRequest request) {
        return ResponseEntity.ok(new Resource(productService.createProduct(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getDetailProduct(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(new Resource(productService.getProductDetail(id)));
    }

    @GetMapping
    public ResponseEntity<Resource> getAll(
            @RequestParam(name = "page", defaultValue = DEFAULT_PAGE) Long page,
            @RequestParam(name = "page_size", defaultValue = DEFAULT_PAGE_SIZE) Long pageSize,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "product_type", required = false) String productType,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "price_from", required = false) Double priceFrom,
            @RequestParam(name = "price_to", required = false) Double priceTo,
            @RequestParam(name = "sort_by", defaultValue = "id") String sortBy,
            @RequestParam(name = "sort_type", defaultValue = "desc") String sortType
    ) {
        var filter = GetProductRequest.builder()
                .page(page)
                .pageSize(pageSize)
                .name(name).productType(productType)
                .status(status)
                .priceFrom(priceFrom).priceTo(priceTo)
                .sortBy(sortBy).sortType(sortType)
                .build();
        return ResponseEntity.ok(new Resource(productService.getAllProducts(filter)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource> updateProduct(
            @PathVariable(name = "id") Long id,
            @RequestBody UpdateProductRequest request
    ) {
        return ResponseEntity.ok(new Resource(productService.updateProduct(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Resource> deleteProduct(@PathVariable(name = "id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(new Resource(null));
    }

}
