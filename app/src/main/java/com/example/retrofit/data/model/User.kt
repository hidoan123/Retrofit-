package com.example.retrofit.data.model

data class User(var id: Int, var name: String, var address: String) {
    override fun toString(): String {
        return "User(id = $id, name = $name, address = $address)"
    }

}