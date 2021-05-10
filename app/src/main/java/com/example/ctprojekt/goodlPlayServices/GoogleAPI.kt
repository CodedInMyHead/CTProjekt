package com.example.ctprojekt.goodlPlayServices

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ctprojekt.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailabilityLight


class GoogleAPI : AppCompatActivity() {
    lateinit var tv:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_a_p_i)
        tv = findViewById(R.id.tvBaum)

        if (isGooglePlayservicesAvailable()){
            tv.text = "connected"
        } else{
            tv.text = "not connected"
        }
    }
    private fun isGooglePlayservicesAvailable():Boolean{
        val googleApiAvailabilityLight:GoogleApiAvailabilityLight = GoogleApiAvailabilityLight.getInstance()
        var status: Int = googleApiAvailabilityLight.isGooglePlayServicesAvailable(this)
        return status == ConnectionResult.SUCCESS
    }
    private val mGoogleSignInClient: GoogleSignInClient? = null

    override fun onResume() {
        super.onResume()
        //signInSilently()
    }
    /*
    private fun signInSilently() {
        val signInOption =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN) // Add the APPFOLDER scope for Snapshot support.
                .requestScopes(Drive.SCOPE_APPFOLDER)
                .build()
        val signInClient = GoogleSignIn.getClient(this, signInOption)
        signInClient.silentSignIn().addOnCompleteListener(
            this
        ) { task ->
            if (task.isSuccessful) {
                onConnected(task.result)
            } else {
                // Player will need to sign-in explicitly using via UI
            }
        }
    }

     */
}