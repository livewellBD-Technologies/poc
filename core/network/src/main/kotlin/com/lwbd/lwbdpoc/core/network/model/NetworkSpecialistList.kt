package com.lwbd.lwbdpoc.core.network.model


import kotlinx.serialization.Serializable

@Serializable
data class NetworkSpecialistList(
    val `data`: List<NetworkSpecialist>,
    val message: String
)