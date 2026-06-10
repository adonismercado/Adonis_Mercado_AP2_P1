package edu.ucne.adonis_mercado_ap2_p1.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AmonestacionEntity (
    @PrimaryKey(autoGenerate = true)
    var amonestacionId: Int = 0,
    val nombres: String,
    val razon: String,
    val monto: String,
)