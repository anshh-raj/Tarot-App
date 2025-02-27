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

    @ColumnInfo(name = "tarot_description")
    val description: String,

    @ColumnInfo(name = "tarot_image")
    val image: String,

    @ColumnInfo(name = "tarot_name")
    val name: String
)