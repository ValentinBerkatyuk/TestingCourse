package com.plcoding.testingcourse.shopping.domain

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

internal class ShoppingCartTest {

    private lateinit var cart: ShoppingCart

    @BeforeEach
    fun setUp() {
        cart = ShoppingCart()
    }

    @Test
    fun `add multiple products, total price sum is correct`() {
        //GIVEN
        val product = Product(
            id = 0,
            name = "apple",
            price = 3.0
        )
        cart.addProduct(product, 5)

        //ACTION
        val sum = cart.getTotalCost()

        //Assertion
        assertThat(sum).isEqualTo(15.0)
    }

    @Test
    fun `Add product with negative quantity, throws Exception`() {
        val product = Product(
            id = 0,
            name = "Ice cream",
            price = 5.0
        )

        assertFailure {
            cart.addProduct(product, -5)
        }
    }

    @RepeatedTest(10)
    fun `Add product with negative quantity, throws Exception 10 times`() {
        val product = Product(
            id = 0,
            name = "Ice cream",
            price = 5.0
        )

        assertFailure {
            cart.addProduct(product, -5)
        }
    }

    @ParameterizedTest
    @ValueSource(
        ints = [1,2,3,4,5,6]
    )
    fun `add multiple products, total price sum is correct multiply`(quantity: Int) {
        //GIVEN
        val product = Product(
            id = 0,
            name = "apple",
            price = 3.0
        )
        cart.addProduct(product, quantity)

        //ACTION
        val sum = cart.getTotalCost()

        //Assertion
        assertThat(sum).isEqualTo(quantity * 3.0)
    }

    @ParameterizedTest
    @CsvSource(
        "3,9.0",
        "4,12.0",
        "0,0.0",
        "5,15.0",
    )
    fun `add multiple products, total price sum is correct multiply csv`(
        quantity: Int,
        totalSum: Double
    ) {
        //GIVEN
        val product = Product(
            id = 0,
            name = "apple",
            price = 3.0
        )
        cart.addProduct(product, quantity)

        //ACTION
        val sum = cart.getTotalCost()

        //Assertion
        assertThat(sum).isEqualTo(quantity * 3.0)
    }

}