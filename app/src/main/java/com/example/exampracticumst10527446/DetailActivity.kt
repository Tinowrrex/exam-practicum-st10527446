package com.example.exampracticumst10527446

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAMES = "EXTRA_NAMES"
        const val EXTRA_CATEGORIES = "EXTRA_CATEGORIES"
        const val EXTRA_QUANTITIES = "EXTRA_QUANTITIES"
        const val EXTRA_NOTES = "EXTRA_NOTES"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Receiving parallel arrays from HomeActivity using constants
        val names = intent.getStringArrayExtra(EXTRA_NAMES) ?: arrayOf()
        val categories = intent.getStringArrayExtra(EXTRA_CATEGORIES) ?: arrayOf()
        val quantities = intent.getIntArrayExtra(EXTRA_QUANTITIES) ?: intArrayOf()
        val notes = intent.getStringArrayExtra(EXTRA_NOTES) ?: arrayOf()

        val recyclerView = findViewById<RecyclerView>(R.id.rvGearDetails)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = GearAdapter(names, categories, quantities, notes)

        val btnBackToBase = findViewById<Button>(R.id.btnBackToBase)
        btnBackToBase.setOnClickListener {
            finish()
        }
    }

    class GearAdapter(
        private val names: Array<String>,
        private val categories: Array<String>,
        private val quantities: IntArray,
        private val notes: Array<String>
    ) : RecyclerView.Adapter<GearAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvName: TextView = view.findViewById(R.id.tvItemName)
            val tvCategory: TextView = view.findViewById(R.id.tvItemCategory)
            val tvQuantity: TextView = view.findViewById(R.id.tvItemQuantity)
            val tvNotes: TextView = view.findViewById(R.id.tvItemNotes)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gear_detail, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val context = holder.itemView.context
            
            // Accessing data using index across parallel arrays
            holder.tvName.text = names[position]
            holder.tvCategory.text = context.getString(R.string.item_category, categories[position])
            holder.tvQuantity.text = context.getString(R.string.item_quantity, quantities[position])
            holder.tvNotes.text = context.getString(R.string.item_notes, notes[position])
        }

        override fun getItemCount() = names.size
    }
}
