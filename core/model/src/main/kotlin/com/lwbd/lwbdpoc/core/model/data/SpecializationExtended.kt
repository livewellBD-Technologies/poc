package com.lwbd.lwbdpoc.core.model.data

data class SpecializationExtended(
    val bannerUrlRect: String,
    val bannerUrlSqr: String,
    val createdAt: String,
    val id: String,
    val imageUrl: String,
    val isActive: Boolean,
    val name: String,
    val services: List<String>?,
    val trainings: List<String>,
    val updatedAt: String
)