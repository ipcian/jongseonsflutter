package com.ipcian.jongseonsflutter

import android.animation.ObjectAnimator
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity

@Suppress("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // XML 레이아웃을 'R' 없이 동적 로드
        val inflater = LayoutInflater.from(this)
        val splashView = inflater.inflate(
            resources.getIdentifier("activity_splash", "layout", packageName),
            null
        )

        // 동적 설정한 레이아웃을 현재 ContentView로 지정
        setContentView(splashView)

        // 스플래시 화면에서 기본 애니메이션 설정
        splashView?.let { view ->
            // Fade In 애니메이션 (0 → 1 투명도)
            ObjectAnimator.ofFloat(view, "alpha", 0f, 1f).apply {
                duration = 1500 // 1.5초 동안 지속
                start()
            }

            // 딜레이 후 Fade Out 및 MainActivity로 전환
            Handler(Looper.getMainLooper()).postDelayed({
                // Fade Out 애니메이션 (1 → 0 투명도)
                ObjectAnimator.ofFloat(view, "alpha", 1f, 0f).apply {
                    duration = 1500 // 1.5초 동안 지속
                    start()
                }

                // 애니메이션 종료 후 MainActivity로 전환
                Handler(Looper.getMainLooper()).postDelayed({
                    val options = ActivityOptions.makeSceneTransitionAnimation(this@SplashActivity)
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java), options.toBundle())
                    finish() // 현재 액티비티 종료
                }, 1500) // Fade Out 동안 대기
            }, 2000) // 스플래시 화면 대기 시간 (2초)
        }
    }
}