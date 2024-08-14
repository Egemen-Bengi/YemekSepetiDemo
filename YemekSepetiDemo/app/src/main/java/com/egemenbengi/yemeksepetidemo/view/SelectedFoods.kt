package com.egemenbengi.yemeksepetidemo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.egemenbengi.yemeksepetidemo.adapter.FoodsRowAdapter
import com.egemenbengi.yemeksepetidemo.databinding.FragmentSelectedFoodsBinding
import com.egemenbengi.yemeksepetidemo.model.FoodsModel
import com.egemenbengi.yemeksepetidemo.model.sepetSingleton
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SelectedFoods : Fragment() {

    private var _binding: FragmentSelectedFoodsBinding? = null
    private val binding get() = _binding!!
    private var adapter = FoodsRowAdapter(arrayListOf())
    private lateinit var db: FirebaseFirestore
    private val foodsList : ArrayList<FoodsModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = Firebase.firestore
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSelectedFoodsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getDataFromFirebase()
        adapter = FoodsRowAdapter(foodsList)
        binding.recyclerSiparisID.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerSiparisID.adapter = adapter
        binding.btnSepeteGitId.setOnClickListener {
            val action = SelectedFoodsDirections.actionSelectedFoodsToSepet()
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun getDataFromFirebase(){
        binding.progressBar.visibility = View.VISIBLE

        arguments?.let {
            foodsList.clear()
            val categoryy = SelectedFoodsArgs.fromBundle(it).FoodCategory
            db.collection("Products")
                .whereEqualTo("category", categoryy)
                .get()
                .addOnSuccessListener { documents->
                    binding.progressBar.visibility = View.GONE
                    documents.forEach { documen->
                        val name = documen.get("name") as String
                        val price = documen.get("price") as String
                        val category  = documen.get("category") as String

                        val food = FoodsModel(name, price, category)
                        foodsList.add(food)
                    }
                    adapter.notifyDataSetChanged()
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}