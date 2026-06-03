package edu.ucne.adonis_mercado_ap2_p1.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BorrameEntity (
    @PrimaryKey(autoGenerate = true)
    var borrameId: Int = 0
)