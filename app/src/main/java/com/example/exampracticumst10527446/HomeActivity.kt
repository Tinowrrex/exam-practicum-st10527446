package com.example.exampracticumst10527446

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {

    // Parallel arrays to store gear information with sample data
    private val gearNames = arrayOf("Tent", "Marshmallows", "Flashlight", "Sleeping Bag", "First Aid Kit", "Stove")
    private val gearCategories = arrayOf("Shelter", "Food", "Lighting", "Comfort", "Safety", "Cooking")
    private val gearQuantities = intArrayOf(1, 2, 3, 2, 1, 1)
    private val gearNotes = arrayOf(
        "Check for stakes",
        "Buy graham crackers too",
        "Bring extra batteries",
        "Rolled up tight",
        "Check expiration dates",
        "Gas canister full"
    )
    private val gearPackedStatus = booleanArrayOf(true, true, true, false, true, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnAddGear = findViewById<Button>(R.id.btnAddGear)
        val btnViewDetails = findViewById<Button>(R.id.btnViewDetails)
        val tvTotalPacked = findViewById<TextView>(R.id.tvTotalPacked)

        btnAddGear.setOnClickListener {
            Toast.makeText(this, "Add Gear clicked", Toast.LENGTH_SHORT).show()
        }

        btnViewDetails.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            // Passing parallel arrays to DetailActivity using constants
            intent.putExtra(DetailActivity.EXTRA_NAMES, gearNames)
            intent.putExtra(DetailActivity.EXTRA_CATEGORIES, gearCategories)
            intent.putExtra(DetailActivity.EXTRA_QUANTITIES, gearQuantities)
            intent.putExtra(DetailActivity.EXTRA_NOTES, gearNotes)
            startActivity(intent)
        }

        updateTotalPacked(tvTotalPacked)
    }

    private fun updateTotalPacked(textView: TextView) {
        var packedCount = 0
        // Using a loop with index to calculate total items packed using parallel arrays
        for (i in gearPackedStatus.indices) {
            if (gearPackedStatus[i]) {
                packedCount++
            }
        }
        textView.text = getString(R.string.total_packed, packedCount)
    }
}
