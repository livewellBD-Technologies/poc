package com.lwbd.lwbdpoc.core.data.model

import NetworkSpecialization
import com.lwbd.lwbdpoc.core.model.data.SpecializationExtended
import com.lwbd.lwbdpoc.core.network.model.NetworkSpecializationExtended

fun NetworkSpecializationExtended.asExternalModel() = SpecializationExtended(
     bannerUrlRect = bannerUrlRect?:"",
     bannerUrlSqr = bannerUrlSqr?:"",
     createdAt=createdAt?:"",
     id=id?:"",
     imageUrl=imageUrl?:"",
     isActive=isActive?:false,
     name=name?:"",
     trainings=trainings?: emptyList(),
     services = services?: emptyList(),
     updatedAt=updatedAt?:""
)