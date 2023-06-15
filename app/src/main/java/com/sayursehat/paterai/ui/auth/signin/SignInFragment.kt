package com.sayursehat.paterai.ui.auth.signin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sayursehat.paterai.R
import com.sayursehat.paterai.databinding.FragmentSignInBinding
import com.sayursehat.paterai.model.User
import com.sayursehat.paterai.ui.market.MarketActivity

class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        auth = Firebase.auth
        db = Firebase.firestore

        binding?.btnSignInGoogle?.setOnClickListener {
            googleSignIn()
        }
    }

    private fun googleSignIn() {
        val intent = googleSignInClient.signInIntent
        launcherIntentGoogleSignIn.launch(intent)
    }

    private val launcherIntentGoogleSignIn = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        when (result.resultCode) {
            Activity.RESULT_OK -> {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    account.idToken?.let {
                        firebaseAuthWithGoogle(it)
                    }

                } catch (_: ApiException) {

                }
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val collectionRef = db.collection("users")
                    collectionRef.whereEqualTo(FieldPath.documentId(), auth.uid)
                        .get()
                        .addOnSuccessListener { result ->
                            if (result.isEmpty) {
                                auth.uid?.let {
                                    val data = User(it)
                                    collectionRef.document(it).set(data)
                                }
                            }
                        }
                        .addOnFailureListener { exception ->

                        }
                    val intent = Intent(activity, MarketActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}