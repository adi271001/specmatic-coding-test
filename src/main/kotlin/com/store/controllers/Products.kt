package com.store.controllers

import com.store.ErrorResponseBody
import com.store.ProductDetails
import com.store.ProductService
import java.time.LocalDateTime

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/products")
class Products(private val productService: ProductService) {

    @GetMapping
    fun getProductsByType(@RequestParam type: String): ResponseEntity<Any> {
        return try {
            val products = productService.getProductsByType(type)
            ResponseEntity.ok(products)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(ErrorResponseBody(
                timestamp = java.time.LocalDateTime.now().toString(),
                status = 400,
                error = e.message ?: "Invalid type parameter",
                path = "/products"
            ))
        }
    }

    @PostMapping
    fun createProduct(@RequestBody productDetails: ProductDetails?): ResponseEntity<Any> {
        return try {
            if (productDetails == null || productDetails.inventory == null) {
                throw IllegalArgumentException("Inventory field is required")
            }

            val productId = productService.createProduct(productDetails)
            ResponseEntity.status(201).body(productId)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(
                ErrorResponseBody(
                    timestamp = LocalDateTime.now().toString(),
                    status = 400,
                    error = e.message ?: "Invalid product details",
                    path = "/products"
                )
            )
        }}
}
