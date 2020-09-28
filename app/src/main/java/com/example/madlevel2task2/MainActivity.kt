package com.example.madlevel2task2

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val questions = arrayListOf<Questions>()
    private val questionsAdapter = QuestionsAdapter(questions)

    //create a binding object
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) //sets the activity layout resource file

        initViews()
    }

    private fun initViews() {
        // Initialize the recycler view with a linear layout manager, adapter
        binding.rvQuestions.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        binding.rvQuestions.adapter = questionsAdapter
        binding.rvQuestions.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )

        // Populate the questions list and notify the data set has changed.
        for (i in Questions.QUESTIONS.indices) {
            questions.add(Questions(Questions.QUESTIONS[i], Questions.ANSWERS[i]))
        }
        questionsAdapter.notifyDataSetChanged()
        createItemTouchHelper().attachToRecyclerView(rvQuestions)
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                if(direction == ItemTouchHelper.LEFT && !questions[position].answer) {
                    questions.removeAt(position)
                    questionsAdapter.notifyDataSetChanged()
                }
                else if(direction == ItemTouchHelper.LEFT && questions[position].answer){
                    Snackbar.make(rvQuestions, "Answer is incorrect", Snackbar.LENGTH_SHORT).show()
                    questionsAdapter.notifyDataSetChanged()
                }
               else if(direction == ItemTouchHelper.RIGHT && questions[position].answer){
                    questions.removeAt(position)
                    questionsAdapter.notifyDataSetChanged()
                }
                else {
                    Snackbar.make(rvQuestions, "Answer is incorrect", Snackbar.LENGTH_SHORT).show()
                    questionsAdapter.notifyDataSetChanged()
                }
            }

        }
        return ItemTouchHelper(callback)
    }
}