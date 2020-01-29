package com.victorio.fastshopclone.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product (
    val id: Int,
    val name: String,
    val thumbnail: String,
    val price : String,
    @SerializedName("category_id")
    val categoryId: Int
    ) : Serializable


/*
*
* id: "1",
        name: "iPhone 8 Cinza Espacial, com Tela de 4,7”, 4G, 64 GB e Câmera de 12 MP - MQ6G2BR/A",
        thumbnail: "https://www.fastshop.com.br/wcsstore/FastShopCAS/imagens/_AE_Apple/AEMQ6G2BRACNZ/AEMQ6G2BRACNZ_PRD_447_1.jpg",
        price: "3.000,00",
        category_id: "1"
*
* */