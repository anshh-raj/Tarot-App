package com.example.tarot.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

data class TCardsItem(
    val description: String,
    val image: String,
    val name: String
)

@Entity(tableName = "tarot_table")
data class TarotCards(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),

    @ColumnInfo(name = "tarot_title")
    val title: String,

    @ColumnInfo(name = "tarot_description1")
    val description1: String,

    @ColumnInfo(name = "tarot_image1")
    val image1: String,

    @ColumnInfo(name = "tarot_name1")
    val name1: String,

    @ColumnInfo(name = "tarot_description2")
    val description2: String,

    @ColumnInfo(name = "tarot_image2")
    val image2: String,

    @ColumnInfo(name = "tarot_name2")
    val name2: String,

    @ColumnInfo(name = "tarot_description3")
    val description3: String,

    @ColumnInfo(name = "tarot_image3")
    val image3: String,

    @ColumnInfo(name = "tarot_name3")
    val name3: String
)