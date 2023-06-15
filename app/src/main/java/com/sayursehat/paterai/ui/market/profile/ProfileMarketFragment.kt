package com.sayursehat.paterai.ui.market.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.sayursehat.paterai.R
import com.sayursehat.paterai.databinding.FragmentProfileMarketBinding
import com.sayursehat.paterai.ui.auth.AuthenticationActivity

class ProfileMarketFragment : Fragment() {

    private var _binding: FragmentProfileMarketBinding? = null
    private val binding get() = _binding
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileMarketBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gso = GoogleSignInOptions.DEFAULT_SIGN_IN

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        auth = Firebase.auth

        val currentUser = auth.currentUser
        binding?.apply {
            Glide.with(requireActivity()).load(currentUser?.photoUrl)
                .circleCrop().into(imgProfileUser)
            tvProfileName.text = currentUser?.displayName
            tvProfileEmail.text = currentUser?.email
            btnProfileLogOut.setOnClickListener {
                auth.signOut()
                googleSignInClient.signOut()
                googleSignInClient.revokeAccess()
                val intent = Intent(activity, AuthenticationActivity::class.java)
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