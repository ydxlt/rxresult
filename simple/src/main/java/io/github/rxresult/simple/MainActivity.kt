package io.github.rxresult.simple

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
//import lt.github.result.ActivityResultCallback
//import lt.github.result.Results
import lt.github.result.common.ActivityResult
import lt.github.rxresult2.RxResults

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun login(v: View){
//        Results.of(this)
//            .call(LoginActivity::class.java,object : ActivityResultCallback{
//                override fun onResult(activityResult: ActivityResult) {
//                    Toast.makeText(this@MainActivity,"login success: ${activityResult.data?.getStringExtra("username")}",Toast.LENGTH_SHORT).show()
//                }
//            })
        // RxResults V2
        RxResults.of(this)
            .justOk(LoginActivity::class.java)
            .map { result: ActivityResult ->
                result.data?.getStringExtra("username")?:"NONE"
            }
            .subscribe {
                Toast.makeText(this@MainActivity,"login success: $it",Toast.LENGTH_SHORT).show()
            }
        // RxResults V3
//        RxResults.of(this)
//            .justOk(LoginActivity::class.java)
////            .filter { it.resultCode == Activity.RESULT_OK }
//            .map { result: ActivityResult ->
//                result.data?.getStringExtra("username")?:"NONE"
//            }
//            .subscribe {
//                Toast.makeText(this@MainActivity,"login success: $it",Toast.LENGTH_SHORT).show()
//            }
    }
}