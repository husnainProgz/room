package com.example.reactiveprog.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "repo")
data class Repo (
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id") val id:String,
    @ColumnInfo(name = "name")
    @SerializedName("name") val name:String,
    @ColumnInfo(name = "language")
    @SerializedName("language") val lang:String?,
    @ColumnInfo(name = "description")
    @SerializedName("description") val desc:String?
)
