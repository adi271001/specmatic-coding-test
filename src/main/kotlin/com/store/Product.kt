package com.store

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty


data class Product(
    val id: Int,
    val name: String?,
    val type: String,
    val inventory: Int
)

/*data class ProductDetails(
    val name: String,
    val type: String,
    val inventory: Int
)*/
data class ProductDetails @JsonCreator constructor(
    @JsonProperty("name") val name: String = "",
    @JsonProperty("type") val type: String = "",
    @JsonProperty("inventory") val inventory: Int = 0
)
data class ErrorResponseBody(
    val timestamp: String,
    val status: Int,
    val error: String,
    val path: String
)

data class ProductId(
    val id: Int
)
