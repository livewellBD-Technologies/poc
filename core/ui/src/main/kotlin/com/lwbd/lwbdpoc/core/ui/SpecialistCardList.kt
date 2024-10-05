import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.lwbd.lwbdpoc.core.designsystem.theme.Roboto
import com.lwbd.lwbdpoc.core.model.data.Specialist
import com.lwbd.lwbdpoc.core.ui.R

@Composable
fun SpecialistCardList(
    items: List<Specialist>, modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .padding()
            .height(280.dp)
    ) {

        Text(
            modifier = Modifier.padding(start = 22.dp),
            text = stringResource(R.string.core_ui_our_services),
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 20.sp,
                lineHeight = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Roboto
            )
        )
        Text(
            modifier = Modifier.padding(start = 22.dp),

            text = stringResource(R.string.core_ui_doctor_on_demand),
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color(0xff6B5F86),
                fontSize = 16.sp,
                lineHeight = 23.sp,
                fontWeight = FontWeight.W400,
                fontFamily = Roboto

            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            modifier = Modifier.height(190.dp),
            contentPadding = PaddingValues(horizontal = 22.dp),
            horizontalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            items(items = items, key = { it.id }) { specialist ->
                val backgroundColor = MaterialTheme.colorScheme.background.toArgb()
                val context = LocalContext.current

                SpecialistCardExpanded(
                    specialist = specialist, onClick = {
                        // Handle the onClick event here
                    }, modifier = modifier
                )
            }
        }
    }
}

// Composable to render a specialist card
@Composable
fun SpecialistCardExpanded(
    specialist: Specialist, onClick: () -> Unit, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(0.dp)
    ) {
        // Render the specialist's information
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(
                    RoundedCornerShape(size = 4.dp)
                )
                .aspectRatio(1f)
                .background(Color.Transparent)
        ) {
            AsyncImage(
                modifier = modifier
                    .height(190.dp)
                    .width(190.dp)
                    .background(Color.Black.copy(.3f)),
                model = specialist.specializations.bannerUrlSqr,
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier
                    .height(190.dp)
                    .padding(vertical = 20.dp, horizontal = 14.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = specialist.specializations.name,
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
                )
                Text(
                    text = "৳${specialist.fee}",
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (specialist.nextAvailableSlot == "AVAILABLE NOW") Box(
                        modifier = Modifier
                            .size(7.dp)
                            .background(Color.Green, CircleShape)
                    )
                    if (specialist.nextAvailableSlot == "AVAILABLE NOW")
                        Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = when (specialist.nextAvailableSlot) {
                            "AVAILABLE NOW" -> "Available Now"
                            else -> "Available ${specialist.nextAvailableSlot}"

                        },
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                    )
                }
            }
        }
    }
}

@Composable
fun SpecialistCardExpandedLoader(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(0.dp)
    ) {
        // Render the specialist's information
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(
                    RoundedCornerShape(size = 4.dp)
                )
                .aspectRatio(1f)
                .background(Color.Gray.copy(.3f))
        )
    }
}


sealed interface SpecialistUiState {
    data object Loading : SpecialistUiState
    data class Success(val specialists: List<Specialist>) : SpecialistUiState
}

@Composable
fun SpecialistScreen(specialistUiState: SpecialistUiState, modifier: Modifier = Modifier) {
    when (specialistUiState) {
        is SpecialistUiState.Loading -> {
            Box(modifier = modifier.fillMaxSize()) {
                LazyRow(
                    modifier = Modifier.height(190.dp),
                    contentPadding = PaddingValues(horizontal = 22.dp),
                    horizontalArrangement = Arrangement.spacedBy(7.dp)
                ) {

                    items(3) {
                        SpecialistCardExpandedLoader(modifier = modifier)
                    }

                }
            }
        }

        is SpecialistUiState.Success -> {
            SpecialistCardList(items = specialistUiState.specialists)
        }

        else -> {}
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSpecialistScreen() {


    SpecialistScreen(specialistUiState = SpecialistUiState.Success(listOf()))
}