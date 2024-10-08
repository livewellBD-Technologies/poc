package com.lwbd.lwbdpoc.core.network.model



import kotlinx.serialization.Serializable

@Serializable
data class NetworkSpecialist(
    val fee: String,
    val id: String,
    val nextAvailableSlot: String,
    val specializations: NetworkSpecializationExtended
)