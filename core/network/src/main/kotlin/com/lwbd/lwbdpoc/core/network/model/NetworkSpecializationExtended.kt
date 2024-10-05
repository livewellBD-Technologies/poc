package com.lwbd.lwbdpoc.core.network.model


import kotlinx.serialization.Serializable

@Serializable
data class NetworkSpecializationExtended(
    val bannerUrlRect: String?=null,
    val bannerUrlSqr: String?=null,
    val createdAt: String?=null,
    val id: String?=null,
    val imageUrl: String?=null,
    val isActive: Boolean?=null,
    val name: String?=null,
    val services: List<String>?= emptyList(),
    val trainings: List<String>?= emptyList(),
    val updatedAt: String?=null
)