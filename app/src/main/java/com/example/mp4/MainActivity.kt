package com.example.mp4

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.ContactsContract.CommonDataKinds.Im
import android.widget.ImageView
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {
    lateinit var mediaPlayer: MediaPlayer
    var totalTime:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mediaPlayer=MediaPlayer.create(this,R.raw.maari)
        mediaPlayer.setVolume(1f,1f)
        totalTime=mediaPlayer.duration

        val Img=findViewById<ImageView>(R.id.imageView)
        val Seekbar=findViewById<SeekBar>(R.id.seekBar)
        val Play=findViewById<ImageView>(R.id.imageView3)
        val Pause=findViewById<ImageView>(R.id.imageView2)
        val Stop=findViewById<ImageView>(R.id.imageView4)

        Play.setOnClickListener {
            mediaPlayer.start()
        }
        Pause.setOnClickListener {
            mediaPlayer.pause()
        }
        Stop.setOnClickListener {
            mediaPlayer?.stop()
            mediaPlayer.reset()
            mediaPlayer.release()
        }

        Seekbar.max=totalTime
        Seekbar.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if(p2){
                    mediaPlayer.seekTo(p1)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
        val handler=Handler()
        handler.postDelayed(object :Runnable{
            override fun run() {
                try{
                    Seekbar.progress=mediaPlayer.currentPosition
                    handler.postDelayed(this,1000)
                }catch (exeception:java.lang.Exception){
                    Seekbar.progress=0
                }
            }
        },0)
    }
}