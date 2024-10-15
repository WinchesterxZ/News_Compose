import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.getDrawable
import com.example.newscompose.R
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    isLoaded: Boolean,
    onFinished: () -> Unit ={}
) {
    if (isLoaded) {
        val splashDuration = 3000L // Duration to display the splash in milliseconds
        LaunchedEffect(Unit) {
            delay(splashDuration)
            onFinished()
// Keep the splash visible for a fixed time
        }
    }
    val context = LocalContext.current

    // Display GIF in Compose (make sure to have Glide or Coil dependency)
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = rememberDrawablePainter(
                drawable = getDrawable(
                    context,
                    R.drawable.splash_gif
                )
            ), // Your GIF resource
            contentDescription = "Animated Icon",
            modifier = Modifier.fillMaxSize()
        )
    }
}

