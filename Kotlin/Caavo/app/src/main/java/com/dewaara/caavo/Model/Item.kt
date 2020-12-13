package com.dewaara.caavo.Model

class Item(var image: String) {
    var price: String? = null
    var name: String? = null
    var description: String? = null
    var id: String? = null
    override fun toString(): String {
        return "ClassPojo [thumbnail = $image, price = $price, name = $name, description = $description, id = $id]"
    }
}