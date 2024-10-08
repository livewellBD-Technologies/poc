package com.lwbd.lwbdpoc.core.data.model


import NetworkUrgentDoctor
import com.lwbd.lwbdpoc.core.model.data.UrgentDoctor

fun NetworkUrgentDoctor.asExternalModel() = UrgentDoctor(
    id = id,
    specializations = specializations.asModel(),
    fee = fee
)