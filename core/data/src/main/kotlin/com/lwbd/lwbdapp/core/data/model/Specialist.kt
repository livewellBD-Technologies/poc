package com.lwbd.lwbdpoc.core.data.model

import com.lwbd.lwbdpoc.core.model.data.Specialist
import com.lwbd.lwbdpoc.core.network.model.NetworkSpecialist

fun NetworkSpecialist.asExternalModel() = Specialist(
    fee = fee,
    id = id,
    nextAvailableSlot = nextAvailableSlot,
    specializations = specializations.asExternalModel()

)