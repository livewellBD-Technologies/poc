import kotlinx.serialization.Serializable

// Doctor Data Class (with Specialization nested inside)
@Serializable
data class NetworkUrgentDoctor(
    val id: String,
    val specializations: NetworkSpecialization,  // Nested specialization object
    val fee: String
)
