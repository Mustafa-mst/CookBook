package com.elifgulay.cookbook.ui.fragment.helperFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import androidx.databinding.DataBindingUtil
import com.elifgulay.cookbook.R
import com.elifgulay.cookbook.databinding.FragmentYoutubeBinding
import com.elifgulay.cookbook.util.warningSnackBar
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YoutubeFragment : BottomSheetDialogFragment() {
    private lateinit var binding:FragmentYoutubeBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //Bind xml and fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_youtube, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Method to run youtube videos of recipes
        arguments?.let {
            val url=YoutubeFragmentArgs.fromBundle(it).youtubeId
            try {
                var urlArray=url.split("=")
                var youtubePlayer=binding.youtubePlayer
                viewLifecycleOwner.lifecycle.addObserver(youtubePlayer)
                youtubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(urlArray.get(1),0f)
                    }
                })
            }catch (e:Exception){
                requireView().warningSnackBar("Something's wrong")
            }


        }

    }

}