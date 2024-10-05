@file:Suppress("ktlint:standard:max-line-length")

package com.lwbd.lwbdpoc.core.testing.data


import com.lwbd.lwbdpoc.core.model.data.Specialist
import com.lwbd.lwbdpoc.core.model.data.SpecializationExtended


val specialistTestData: List<Specialist> = listOf(
    Specialist(
        id = "64245104dc683ab14b98de81",
        specializations = SpecializationExtended(
            id = "63009a304cfa23ff4a63f30f",
            name = "Gynecology",
            imageUrl = "https://livewell-bd-dev.s3.ap-south-1.amazonaws.com/specialties/Gynecology.png",
            bannerUrlRect = "https://livewell-bd-dev.s3.ap-south-1.amazonaws.com/1688578974283-669978439.png",
            bannerUrlSqr = "https://livewell-bd-dev.s3.ap-south-1.amazonaws.com/1688578974258-229825068.png",
            createdAt = "2022-08-20T08:24:16.736Z",
            updatedAt = "2023-10-01T13:12:46.934Z",
            trainings = listOf(
                "PGT",
                "FCPS Part-1",
                "FCPS Part-2"
            ),
            services = listOf(
                "Sexual and reproductive health counseling",
                "Cesarean delivery",
                "Hysterectomy",
                "Oophorectomy",
                "Myomectomy",
                "Tubal ligation",
                "Hysteroscopy",
                "Colposcopy",
                "Pelvic organ prolapse repair",
                "Pelvic reconstructive surgery",
                "Endometrial ablation",
                "Pelvic laparotomy",
                "Gynecologic oncology surgeries",
                "Urinary incontinence surgery"
            ),
            isActive = true
        ),
        fee = "200",
        nextAvailableSlot = "AVAILABLE NOW"
    ),
    Specialist(
        id = "64245104dc683ab14b98de82",
        specializations = SpecializationExtended(
            id = "63009a304cfa23ff4a63f30f",
            name = "Gynecology",
            imageUrl = "https://livewell-bd-dev.s3.ap-south-1.amazonaws.com/specialties/Gynecology.png",
            bannerUrlRect = "https://livewell-bd-dev.s3.ap-south-1.amazonaws.com/1688578974283-669978439.png",
            bannerUrlSqr = "https://livewell-bd-dev.s3.ap-south-1.amazonaws.com/1688578974258-229825068.png",
            createdAt = "2022-08-20T08:24:16.736Z",
            updatedAt = "2023-10-01T13:12:46.934Z",
            trainings = listOf(
                "PGT",
                "FCPS Part-1",
                "FCPS Part-2"
            ),
            services = listOf(
                "Sexual and reproductive health counseling",
                "Cesarean delivery",
                "Hysterectomy",
                "Oophorectomy",
                "Myomectomy",
                "Tubal ligation",
                "Hysteroscopy",
                "Colposcopy",
                "Pelvic organ prolapse repair",
                "Pelvic reconstructive surgery",
                "Endometrial ablation",
                "Pelvic laparotomy",
                "Gynecologic oncology surgeries",
                "Urinary incontinence surgery"
            ),
            isActive = true
        ),
        fee = "100",
        nextAvailableSlot = "AVAILABLE NOW"
    )
)

