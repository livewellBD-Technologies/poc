import kotlinx.serialization.Serializable

// Specialization Data Class
@Serializable
data class NetworkSpecialization(
    val id: String?=null,
    val name: String?=null,
    val imageUrl: String?=null,
    val bannerUrlRect: String?=null,
    val bannerUrlSqr: String?=null,
    val createdAt: String?=null,
    val updatedAt: String?=null,
    val trainings: List<String>?=null,
    val isActive: Boolean?=null
)