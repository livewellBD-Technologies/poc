package com.lwbd.lwbdpoc.core.data.model

import NetworkSpecialization
import com.lwbd.lwbdpoc.core.model.data.Specialization

fun NetworkSpecialization.asModel() = Specialization(
     bannerUrlRect = bannerUrlRect?:"",
     bannerUrlSqr = bannerUrlSqr?:"",
     createdAt=createdAt?:"",
     id=id?:"",
     imageUrl=imageUrl?:"",
     isActive=isActive?:false,
     name=name?:"",
     trainings=trainings?: emptyList(),
     updatedAt=updatedAt?:""
)