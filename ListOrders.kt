/**
Order #1
Noodles: $10
Total: $10

Order #2
Noodles: $10
Vegetables Chef's Choice: $5
Total: $15
 */

open class Items(val name: String, val price: Int)

class Noodles : Items("Noodles", 10) {
    override fun toString(): String {
        return name
    }
}

class Vegetables(private vararg val toppings: String) : Items("Vegetables", 5) {
    override fun toString(): String {
        return if (toppings.isEmpty()) {
            "$name Chef's Choice"
        } else {
            name + "" + toppings.joinToString()
        }
    }
}

fun main() {
    val ordersList = mutableListOf<Order>()

    // Add an item to an order
    val order1 = Order(1)
    order1.addItem(Noodles())
    ordersList.add(order1)

    // Add multiple items individually
    val order2 = Order(2)
    order2.addItem(Noodles())
    order2.addItem(Vegetables())
    ordersList.add(order2)

    // Add a list of items at one time
    val order3 = Order(3)
    val items = listOf(Noodles(), Vegetables("Carrots", "Beans", "Celery"))
    order3.addAll(items)
    ordersList.add(order3)

    // Use builder pattern
    val order4 = Order(4)
        .addItem(Noodles())
        .addItem(Vegetables("Cabbage", "Onion"))
    ordersList.add(order4)

    // Create and add order directly
    ordersList.add(
        Order(5)
            .addItem(Noodles())
            .addItem(Noodles())
            .addItem(Vegetables("Spinach"))
    )

    // Print out each order
    for (order in ordersList) {
        order.print()
        println()
    }
}

class Order(private val orderNumber: Int) {
    private val itemList = mutableListOf<Items>()

    fun addItem(newItem: Items): Order {
        itemList.add(newItem)
        return this
    }

    fun addAll(newItems: List<Items>): Order {
        itemList.addAll(newItems)
        return this
    }

    fun print() {
        println("Order Number #$orderNumber")
        var total = 0
        for (item in itemList) {
            println("$item : $${item.price}")
            total += item.price
        }
        println("Total: $${total}")
    }
}