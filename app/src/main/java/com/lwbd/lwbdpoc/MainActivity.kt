package com.lwbd.lwbdpoc

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.metrics.performance.JankStats
import com.lwbd.lwbdpoc.core.data.repository.DoctorRepository
import com.lwbd.lwbdpoc.core.data.util.NetworkMonitor
import com.lwbd.lwbdpoc.core.data.util.TimeZoneMonitor
import com.lwbd.lwbdpoc.core.designsystem.theme.LwbdTheme
import com.lwbd.lwbdpoc.core.ui.LocalTimeZone
import com.lwbd.lwbdpoc.ui.LwbdApp
import com.lwbd.lwbdpoc.ui.rememberLwbdAppState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @Inject
    lateinit var lazyStats: dagger.Lazy<JankStats>

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    @Inject
    lateinit var timeZoneMonitor: TimeZoneMonitor


    @Inject
    lateinit var doctorRepository: DoctorRepository

//    val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        println("onCreate init")
        super.onCreate(savedInstanceState)

//        var uiState: MainActivityUiState by mutableStateOf(Loading)

        // Update the uiState
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

            }
        }

        // Keep the splash screen on-screen until the UI state is loaded. This condition is
        // evaluated each time the app needs to be redrawn so it should be fast to avoid blocking
        // the UI.
        splashScreen.setKeepOnScreenCondition {
            false
        }

        // Turn off the decor fitting system windows, which allows us to handle insets,
        // including IME animations, and go edge-to-edge
        // This also sets up the initial system bar style based on the platform theme
        enableEdgeToEdge()
        println("************************************")
        println("onCreate edge to edge enabled")

        setContent {
            val darkTheme = false

            // Update the edge to edge configuration to match the theme
            // This is the same parameters as the default enableEdgeToEdge call, but we manually
            // resolve whether or not to show dark theme using uiState, since it can be different
            // than the configuration's dark theme value based on the user preference.
            println("************************************")
            println("onCreate de effect is going to be started")
            DisposableEffect(darkTheme) {
                println("************************************")
                println("onCreate de effect is started")
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(
                        Color.TRANSPARENT,
                        Color.TRANSPARENT,
                    ) { darkTheme },
                    navigationBarStyle = SystemBarStyle.auto(
                        lightScrim,
                        darkScrim,
                    ) { darkTheme },
                )
                println("************************************")
                println("onCreate edge to edge configured")
                onDispose {
                    println("************************************")
                    println("onCreate de effect is disposed")
                }
            }

            val appState = rememberLwbdAppState(
                networkMonitor = networkMonitor,
                timeZoneMonitor = timeZoneMonitor,
            )
            println("************************************")
            println("onCreate current time zone")
            val currentTimeZone by appState.currentTimeZone.collectAsStateWithLifecycle()
            println("************************************")
            println("onCreate current time zone initialized")
            CompositionLocalProvider(
                LocalTimeZone provides currentTimeZone,
            ) {
                LwbdTheme(
                    darkTheme = darkTheme,
                    androidTheme = false,
                    disableDynamicTheming = true,
                ) {
                    LwbdApp(appState)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        lazyStats.get().isTrackingEnabled = true
    }

    override fun onPause() {
        super.onPause()
        lazyStats.get().isTrackingEnabled = false
    }
}


/**
 * The default light scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=35-38;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
//private val lightScrim = android.graphics.Color.argb(0xff, 0xFF, 0xFF, 0xFF)

/**
 * The default dark scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=40-44;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
//private val darkScrim = android.graphics.Color.argb(0x80, 0x1b, 0x1b, 0x1b)

val lightScrim = Color.parseColor("#FFFFFF")  // Light scrim color
val darkScrim = Color.parseColor("#000000")
