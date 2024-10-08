

@file:Suppress("ktlint:standard:max-line-length")

package com.lwbd.lwbdpoc.core.testing.data

import com.lwbd.lwbdpoc.core.model.data.Specialization
import com.lwbd.lwbdpoc.core.model.data.UrgentDoctor


val urgentDoctorTestData: UrgentDoctor = UrgentDoctor(id = "1",
    specializations = Specialization(
        bannerUrlRect = "Banner URL Rect",
        bannerUrlSqr = "Banner URL Sqr",
        imageUrl = "Image URL",
        name = "Doctor",
        createdAt = "2023-05-24T15:46:11.970Z",
        updatedAt = "2023-05-24T15:46:11.970Z",
        trainings = listOf("Training 1", "Training 2"),
        id = "2",
        isActive = true
    ),
    fee = "75")

