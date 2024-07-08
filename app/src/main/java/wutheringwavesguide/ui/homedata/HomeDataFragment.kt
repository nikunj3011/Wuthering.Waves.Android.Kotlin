package wutheringwavesguide.ui.homedata

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import wutheringwavesguide.R
import wutheringwavesguide.databinding.FragmentEchoBinding
import wutheringwavesguide.databinding.FragmentHomeDataBinding
import wutheringwavesguide.ui.echos.EchoViewModel
import wutheringwavesguide.util.autoCleared

class HomeDataFragment : Fragment() {

    var binding by autoCleared<FragmentHomeDataBinding>()
    private val viewModel by viewModel<HomeDataViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = FragmentHomeDataBinding.inflate(
            inflater,
            container,
            false
        )
        binding = dataBinding

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.webViewIntroVideoHome.webViewClient = WebViewClient()
        binding.webViewIntroVideoHome.settings.javaScriptEnabled = true
        binding.webViewIntroVideoHome.loadData("<iframe width=\"380\" height=\"190\" src=\"https://www.youtube.com/embed/R8htow_6tRc?si=jNrcgc4MmC8IATSN&amp;start=2\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>",
            "text/html", "utf-8")
        binding.webViewIntroVideoHome.setBackgroundColor(Color.BLACK)

        binding.webViewIntroVideoHome.performClick()
//        binding.webViewIntroVideoHome.set = WebView(requireContext()).apply {
//            settings.javaScriptEnabled = true
//            settings.loadWithOverviewMode = true
//            settings.useWideViewPort = true
//            webViewClient = WebViewClient()
//        }
    }

    var player = """
    <!DOCTYPE html>
    <html>
    <body>
    <!-- 1. The <iframe> (and video player) will replace this <div> tag. -->
    <div id="player"></div>

    <script>
    // 2. This code loads the IFrame Player API code asynchronously.
    var tag = document.createElement('script');

    tag.src = "https://www.youtube.com/iframe_api";
    var firstScriptTag = document.getElementsByTagName('script')[0];
    firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

    // 3. This function creates an <iframe> (and YouTube player)
    //    after the API code downloads.
    var player;
    function onYouTubeIframeAPIReady() {
        player = new YT.Player('player', {
            height: '370',
            width: '190',
            videoId: 'R8htow_6tRc',
            playerVars: {
            'playsinline': 1,
            'autoplay': '1'
        },
            events: {
            'onReady': onPlayerReady,
            'onStateChange': onPlayerStateChange
        }
        });
    }

    // 4. The API will call this function when the video player is ready.
    function onPlayerReady(event) {
        event.target.playVideo();
    }

    // 5. The API calls this function when the player's state changes.
    //    The function indicates that when playing a video (state=1),
    //    the player should play for six seconds and then stop.
    var done = false;
    function onPlayerStateChange(event) {
        if (event.data == YT.PlayerState.PLAYING && !done) {
            setTimeout(stopVideo, 6000);
            done = true;
        }
    }
    function stopVideo() {
        player.stopVideo();
    }
    </script>
    </body>
    </html>;
    """
}