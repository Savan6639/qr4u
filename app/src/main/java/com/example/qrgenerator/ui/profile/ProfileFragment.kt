package com.example.qrgenerator.ui.profile

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.qrgenerator.R
import com.example.qrgenerator.activity_create_history
import com.example.qrgenerator.activity_scan_history


class ProfileFragment : Fragment() {

    lateinit var layout_feedback: LinearLayout
    lateinit var layout_rateus: LinearLayout
    lateinit var layout_create_history: LinearLayout
    lateinit var layout_scan_history: LinearLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)
        setupUI(view)
        return view
    }

    @SuppressLint("NotifyDataSetChanged", "ClickableViewAccessibility")
    private fun setupUI(view: View) {

        getActivity()?.getWindow()?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        layout_feedback = view.findViewById(R.id.layout_feedback)
        layout_rateus = view.findViewById(R.id.layout_rateus)
        layout_create_history = view.findViewById(R.id.layout_create_history)
        layout_scan_history = view.findViewById(R.id.layout_scan_history)

        layout_feedback.setOnClickListener {

//            val reviewManager: ReviewManager = ReviewManagerFactory.create(requireContext())
//            val requestReviewTask: Task<ReviewInfo> = reviewManager.requestReviewFlow()
//
//            requestReviewTask.addOnCompleteListener { request ->
//                if (request.isSuccessful) {
//                    // Request succeeded and a ReviewInfo instance was received
//                    val reviewInfo: ReviewInfo = request.result
//                    val launchReviewTask: Task<*> = reviewManager.launchReviewFlow(requireActivity(), reviewInfo)
//                    launchReviewTask.addOnCompleteListener { _ ->
//                        // The review has finished, continue your app flow.
//                    }
//                } else {
//                    // Request failed
//                }
//
//
//            }

            showDialog_feedback()
        }
        layout_rateus.setOnClickListener {

            showDialog_rateus()

        }
        layout_create_history.setOnClickListener {

            val intent =
                Intent(requireContext(), activity_create_history::class.java)
            startActivity(intent)
        }
        layout_scan_history.setOnClickListener {

            val intent =
                Intent(requireContext(), activity_scan_history::class.java)
            startActivity(intent)
        }
    }

    private fun showDialog_feedback() {

        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_feedback_dialog)

        val submit = dialog.findViewById(R.id.submit) as Button
        val cancle = dialog.findViewById(R.id.cancle) as TextView
        submit.setOnClickListener {
            val to: String = "raviuchadadiya255@gmail.com"
            val subject: String = "Feedback"
            val text_feedback : EditText = dialog.findViewById(R.id.text_feedback)
            val message: String = text_feedback.getText().toString()


            val email = Intent(Intent.ACTION_SEND)
            email.putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
            email.putExtra(Intent.EXTRA_SUBJECT, subject)
            email.putExtra(Intent.EXTRA_TEXT, message)

            //need this to prompts email client only

            //need this to prompts email client only
            email.type = "message/rfc822"

            startActivity(Intent.createChooser(email, "Choose an Email client : Gmail"))
            dialog.dismiss()
        }
        cancle.setOnClickListener { dialog.dismiss() }
        dialog.show()

    }
    private fun showDialog_rateus() {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_rateus_dialog)

        val rbstar : RatingBar
        rbstar = dialog.findViewById(R.id.ratingBar)




        val rate = dialog.findViewById(R.id.rate) as Button

        rate.setOnClickListener {
            val msg = rbstar.rating.toString()
            Toast.makeText(requireContext(),
                "Rating is: "+msg, Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        dialog.show()

    }




}