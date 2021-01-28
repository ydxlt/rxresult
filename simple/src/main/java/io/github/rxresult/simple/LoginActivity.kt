package io.github.rxresult.simple

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * @date 2021/1/25
 * @author ydxlt
 */
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun loginSuccess(v:View){
        setResult(Activity.RESULT_OK, Intent().also { it.putExtra("username","ydxlt") })
        finish()
    }

    fun loginError(v:View){
        setResult(Activity.RESULT_CANCELED)
        finish()
    }
}