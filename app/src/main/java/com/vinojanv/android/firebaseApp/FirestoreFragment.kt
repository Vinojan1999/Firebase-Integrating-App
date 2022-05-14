package com.vinojanv.android.firebaseApp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.vinojanv.android.firebaseApp.databinding.FragmentFirestoreBinding
import com.vinojanv.android.firebaseApp.model.Client

class FirestoreFragment : Fragment() {

    private var _binding: FragmentFirestoreBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirestoreBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addButton.setOnClickListener {

            val first = binding.fnameEditText.text.toString()
            val last = binding.lnameEditText.text.toString()
            val age = binding.ageEditText.text.toString().toInt()
            val lat = binding.latEditText.text.toString().toFloat()
            val lgt = binding.lngEditText.text.toString().toFloat()

            val client = Client(first, last, age, lat, lgt)

            db.collection("clients").document("$first $last").set(client)
                .addOnSuccessListener {
                    Log.d(TAG, "Client $first $last added")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding Client", e)
                }

            binding.fnameEditText.setText("")
            binding.lnameEditText.setText("")
            binding.ageEditText.setText("")
            binding.latEditText.setText("")
            binding.lngEditText.setText("")

            findNavController().navigate(R.id.action_FirestoreFragment_to_ClientListFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}