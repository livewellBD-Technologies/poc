//
//
//package com.lwbd.lwbdpoc.core.ui
//
//import androidx.compose.foundation.Canvas
//import androidx.compose.foundation.ExperimentalFoundationApi
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.horizontalScroll
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalInspectionMode
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.semantics.contentDescription
//import androidx.compose.ui.semantics.onClick
//import androidx.compose.ui.semantics.semantics
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import coil.compose.AsyncImage
//import coil.compose.AsyncImagePainter
//import coil.compose.rememberAsyncImagePainter
//import com.lwbd.lwbdpoc.core.designsystem.R.drawable
//import com.lwbd.lwbdpoc.core.designsystem.component.LwbdIconToggleButton
//import com.lwbd.lwbdpoc.core.designsystem.icon.LwbdIcons
//import com.lwbd.lwbdpoc.core.designsystem.theme.LwbdTheme
//
//import com.lwbd.lwbdpoc.core.model.data.Specialist
//import kotlinx.datetime.Instant
//import kotlinx.datetime.toInstant
//import kotlinx.datetime.toJavaInstant
//import kotlinx.datetime.toJavaZoneId
//import java.time.format.DateTimeFormatter
//import java.time.format.FormatStyle
//import java.util.Locale
//
///**
// * [Specialist] card used on the following screens: HOME, Saved
// */
//
//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun SpecialistCardExpanded(
//    specialist: Specialist,
////    isBookmarked: Boolean,
////    hasBeenViewed: Boolean,
////    onToggleBookmark: () -> Unit,
//    onClick: () -> Unit,
////    onTopicClick: (String) -> Unit,
//    modifier: Modifier = Modifier,
//) {
////    val clickActionLabel = stringResource(R.string.core_ui_card_tap_action)
////    val sharingLabel = stringResource(R.string.core_ui_feed_sharing)
////    val sharingContent = stringResource(
////        R.string.core_ui_feed_sharing_data,
////        spec.title,
////        spec.url,
////    )
////
////    val dragAndDropFlags = if (VERSION.SDK_INT >= VERSION_CODES.N) {
////        View.DRAG_FLAG_GLOBAL
////    } else {
////        0
////    }
//
//    Card(
//        onClick = onClick,
//        shape = RoundedCornerShape(16.dp),
//        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
//        // Use custom label for accessibility services to communicate button's action to user.
//        // Pass null for action to only override the label and not the actual action.
//        modifier = modifier.semantics {
//            onClick(label = "clickActionLabel", action = null)
//        },
//    ) {
//        Column {
////            if (!spec.headerImageUrl.isNullOrEmpty()) {
////                Row {
////                    NewsResourceHeaderImage(spec.headerImageUrl)
////                }
////            }
//            Box(
//                modifier = Modifier.padding(16.dp),
//            ) {
//                AsyncImage(model = specialist.specializations.bannerUrlSqr, contentDescription = null)
//                Column {
//                    Spacer(modifier = Modifier.height(12.dp))
//                    Row {
//                        NewsResourceTitle(
//                            specialist.specializations.name,
//                            modifier = Modifier
//                                .fillMaxWidth((.8f))
////                                .dragAndDropSource {
////                                    detectTapGestures(
////                                        onLongPress = {
////                                            startTransfer(
////                                                DragAndDropTransferData(
////                                                    ClipData.newPlainText(
////                                                        sharingLabel,
////                                                        sharingContent,
////                                                    ),
////                                                    flags = dragAndDropFlags,
////                                                ),
////                                            )
////                                        },
////                                    )
////                                },
//                        )
//                        Spacer(modifier = Modifier.weight(1f))
////                        BookmarkButton(isBookmarked, onToggleBookmark)
//                    }
//                    Spacer(modifier = Modifier.height(14.dp))
//                    Row(verticalAlignment = Alignment.CenterVertically) {
////                        if (!hasBeenViewed) {
////                            NotificationDot(
////                                color = MaterialTheme.colorScheme.tertiary,
////                                modifier = Modifier.size(8.dp),
////                            )
////                            Spacer(modifier = Modifier.size(6.dp))
////                        }
//                        NewsResourceMetaData(specialist.specializations.createdAt.toInstant(), specialist.specializations.trainings.first())
//                    }
//                    Spacer(modifier = Modifier.height(14.dp))
//                    NewsResourceShortDescription(specialist.specializations.services?.first()?:"")
//                    Spacer(modifier = Modifier.height(12.dp))
//                    NewsResourceTopics(
////                        topics = userNewsResource.followableTopics,
//                        onTopicClick = {  },
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun NewsResourceHeaderImage(
//    headerImageUrl: String?,
//) {
//    var isLoading by remember { mutableStateOf(true) }
//    var isError by remember { mutableStateOf(false) }
//    val imageLoader = rememberAsyncImagePainter(
//        model = headerImageUrl,
//        onState = { state ->
//            isLoading = state is AsyncImagePainter.State.Loading
//            isError = state is AsyncImagePainter.State.Error
//        },
//    )
//    val isLocalInspection = LocalInspectionMode.current
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(180.dp),
//        contentAlignment = Alignment.Center,
//    ) {
//        if (isLoading) {
//            // Display a progress bar while loading
//            CircularProgressIndicator(
//                modifier = Modifier
//                    .align(Alignment.Center)
//                    .size(80.dp),
//                color = MaterialTheme.colorScheme.tertiary,
//            )
//        }
//
//        Image(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(180.dp),
//            contentScale = ContentScale.Crop,
//            painter = if (isError.not() && !isLocalInspection) {
//                imageLoader
//            } else {
//                painterResource(drawable.core_designsystem_ic_placeholder_default)
//            },
//            // TODO b/226661685: Investigate using alt text of  image to populate content description
//            // decorative image,
//            contentDescription = null,
//        )
//    }
//}
//
//@Composable
//fun NewsResourceTitle(
//    newsResourceTitle: String,
//    modifier: Modifier = Modifier,
//) {
//    Text(newsResourceTitle, style = MaterialTheme.typography.headlineSmall, modifier = modifier)
//}
//
//
//
//
//
//@Composable
//fun dateFormatted(publishDate: Instant): String = DateTimeFormatter
//    .ofLocalizedDate(FormatStyle.MEDIUM)
//    .withLocale(Locale.getDefault())
//    .withZone(LocalTimeZone.current.toJavaZoneId())
//    .format(publishDate.toJavaInstant())
//
//
//
//@Composable
//fun NewsResourceShortDescription(
//    newsResourceShortDescription: String,
//) {
//    Text(newsResourceShortDescription, style = MaterialTheme.typography.bodyLarge)
//}
//
//@Composable
//fun NewsResourceTopics(
////    topics: List<FollowableTopic>,
//    onTopicClick: (String) -> Unit,
//    modifier: Modifier = Modifier,
//) {
//    Row(
//        // causes narrow chips
//        modifier = modifier.horizontalScroll(rememberScrollState()),
//        horizontalArrangement = Arrangement.spacedBy(4.dp),
//    ) {
////        for (followableTopic in topics) {
////            LwbdTopicTag(
////                followed = followableTopic.isFollowed,
////                onClick = { onTopicClick(followableTopic.topic.id) },
////                text = {
////                    val contentDescription = if (followableTopic.isFollowed) {
////                        stringResource(
////                            R.string.core_ui_topic_chip_content_description_when_followed,
////                            followableTopic.topic.name,
////                        )
////                    } else {
////                        stringResource(
////                            R.string.core_ui_topic_chip_content_description_when_not_followed,
////                            followableTopic.topic.name,
////                        )
////                    }
////                    Text(
////                        text = followableTopic.topic.name.uppercase(Locale.getDefault()),
////                        modifier = Modifier.semantics {
////                            this.contentDescription = contentDescription
////                        },
////                    )
////                },
////            )
////        }
//    }
//}
//
//@Preview("Bookmark Button")
//@Composable
//private fun BookmarkButtonPreview() {
//    LwbdTheme {
//        Surface {
//            BookmarkButton(isBookmarked = false, onClick = { })
//        }
//    }
//}
//
//@Preview("Bookmark Button Bookmarked")
//@Composable
//private fun BookmarkButtonBookmarkedPreview() {
//    LwbdTheme {
//        Surface {
//            BookmarkButton(isBookmarked = true, onClick = { })
//        }
//    }
//}
//
////@Preview("NewsResourceCardExpanded")
////@Composable
////private fun ExpandedNewsResourcePreview(
////    @PreviewParameter(UserNewsResourcePreviewParameterProvider::class)
////    userNewsResources: List<UserNewsResource>,
////) {
////    CompositionLocalProvider(
////        LocalInspectionMode provides true,
////    ) {
////        LwbdTheme {
////            Surface {
////                SpecialistCardExpanded(
////                    spec = userNewsResources[0],
////                    isBookmarked = true,
////                    hasBeenViewed = false,
////                    onToggleBookmark = {},
////                    onClick = {},
////                    onTopicClick = {},
////                )
////            }
////        }
////    }
////}
