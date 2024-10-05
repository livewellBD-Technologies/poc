package com.lwbd.lwbdpoc.core.model.data

data class Specialist(
    val fee: String,
    val id: String,
    val nextAvailableSlot: String,
    val specializations: SpecializationExtended
)