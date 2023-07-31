package com.example.qrgenerator.ui.home
import android.annotation.SuppressLint

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*

import com.example.qrgenerator.Itemdetails_qrscan
import com.example.qrgenerator.R
import com.example.qrgenerator.qr_generate
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import com.rilixtech.widget.countrycodepicker.CountryCodePicker
import java.io.ByteArrayOutputStream


class HomeFragment  : androidx.fragment.app.Fragment() {

    lateinit var ccp: CountryCodePicker
    lateinit var layout_text: LinearLayout
    lateinit var layout_web: LinearLayout
    lateinit var layout_contact: LinearLayout
    lateinit var layout_wifi: LinearLayout
    lateinit var layout_phone: LinearLayout
    lateinit var layout_sms: LinearLayout
    lateinit var layout_insta: LinearLayout
    lateinit var layout_whatsapp: LinearLayout
    lateinit var linearLayout1: LinearLayout
    lateinit var linearLayout2: LinearLayout
    lateinit var linearLayout3: LinearLayout
    lateinit var linearLayout4: LinearLayout
    lateinit var linearLayout5: LinearLayout
    lateinit var linearLayout6: LinearLayout
    lateinit var linearLayout7: LinearLayout
    lateinit var linearLayout8: LinearLayout
    lateinit var textView9:TextView
    lateinit var textView12:TextView
    lateinit var textView13:TextView
    lateinit var textView14:TextView
    lateinit var button:Button
    lateinit var editText:EditText
    lateinit var editText_name:EditText
    lateinit var editText_phone:EditText
    lateinit var editText_email:EditText
    lateinit var editText_company:EditText
    lateinit var editText_job:EditText
    lateinit var editText_address:EditText
    lateinit var editText_other:EditText
    lateinit var editText_recipient:EditText
    lateinit var editText_msg:EditText
    lateinit var editText_no:EditText
    lateinit var editText_whatsApp:EditText
    lateinit var wpa: Button
    lateinit var wep: Button
    lateinit var none: Button
    lateinit var id:EditText
    lateinit var password:EditText
    lateinit var switch: Switch
    var i: Int = 0
    var text_value:String = ""
    var strwebSecurity = ""
    var Hiden_Network = "Disable"






    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        setupUI(view)
        return view
    }



    @SuppressLint("NotifyDataSetChanged", "ClickableViewAccessibility")

    private fun setupUI(view: View) {

        getActivity()?.getWindow()
            ?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        layout_web = view.findViewById(R.id.layout_web)
        layout_text = view.findViewById(R.id.layout_text)
        layout_contact = view.findViewById(R.id.layout_contact)
        layout_sms = view.findViewById(R.id.layout_sms)
        layout_wifi = view.findViewById(R.id.layout_wifi)
        layout_phone = view.findViewById(R.id.layout_phone)
        layout_insta = view.findViewById(R.id.layout_insta)
        layout_whatsapp = view.findViewById(R.id.layout_whatsapp)
        linearLayout1 = view.findViewById(R.id.linearLayout1)
        linearLayout2 = view.findViewById(R.id.linearLayout2)
        linearLayout3 = view.findViewById(R.id.linearLayout3)
        linearLayout4 = view.findViewById(R.id.linearLayout4)
        linearLayout5 = view.findViewById(R.id.linearLayout5)
        linearLayout6 = view.findViewById(R.id.linearLayout6)
        linearLayout7 = view.findViewById(R.id.linearLayout7)
        linearLayout8 = view.findViewById(R.id.linearLayout8)
        textView9 = view.findViewById(R.id.textView9)
        textView12 = view.findViewById(R.id.textView12)
        textView13 = view.findViewById(R.id.textView13)
        textView14 = view.findViewById(R.id.textView14)
        button = view.findViewById(R.id.generate_button)
        editText = view.findViewById(R.id.editTextTextPersonName3)
        editText_name = view.findViewById(R.id.editTextTextPersonName5)
        editText_phone = view.findViewById(R.id.editTextPhone2)
        editText_email = view.findViewById(R.id.editTextTextEmailAddress2)
        editText_company = view.findViewById(R.id.editTextTextPersonName6)
        editText_job = view.findViewById(R.id.editTextTextPersonName7)
        editText_address = view.findViewById(R.id.editTextTextPersonName8)
        editText_other = view.findViewById(R.id.editTextTextMultiLine)
        editText_recipient = view.findViewById(R.id.recipient)
        editText_msg = view.findViewById(R.id.PersonName7)
        editText_no = view.findViewById(R.id.PersonName3)
        editText_whatsApp = view.findViewById(R.id.editTextPhone)
        wpa = view.findViewById(R.id.button)
        wep = view.findViewById(R.id.button2)
        none = view.findViewById(R.id.button3)
        id = view.findViewById(R.id.PersonName5)
        switch = view.findViewById(R.id.switch1)

        password = view.findViewById(R.id.editTextTextPassword)

        ccp = view.findViewById(R.id.ccp)

        layout_text.setBackgroundResource(R.drawable.shape)
        layout_web.setBackgroundResource(R.drawable.shape_round)
        layout_contact.setBackgroundResource(R.drawable.shape_round)
        layout_wifi.setBackgroundResource(R.drawable.shape_round)
        layout_phone.setBackgroundResource(R.drawable.shape_round)
        layout_sms.setBackgroundResource(R.drawable.shape_round)
        layout_insta.setBackgroundResource(R.drawable.shape_round)
        layout_whatsapp.setBackgroundResource(R.drawable.shape_round)


        linearLayout2.setVisibility(View.GONE)
        linearLayout3.setVisibility(View.GONE)
        linearLayout4.setVisibility(View.GONE)
        linearLayout5.setVisibility(View.GONE)
        linearLayout6.setVisibility(View.GONE)
        linearLayout7.setVisibility(View.GONE)
        linearLayout8.setVisibility(View.GONE)

        text_value = "Text"
        val text_msg = "Text"
        val intent =
            Intent(requireContext(), qr_generate::class.java)
        intent.putExtra("msgValue", text_msg)



        layout_text.setOnClickListener() {

            i = 1

            text_value = "Text"
            editText = view.findViewById(R.id.editTextTextPersonName3)

            editText.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(mo_no : Editable) {}

                override fun beforeTextChanged(s: CharSequence, start: Int,
                                               count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence, start: Int,
                                           before: Int, count: Int) {

                    if (count > 0){
                        button.setBackgroundColor(resources.getColor(R.color.light_blue))
                    }else if (count <= 0){
                        button.setBackgroundColor(resources.getColor(R.color.gray))
                    }
                }
            })
            layout_text.setBackgroundResource(R.drawable.shape)
            layout_web.setBackgroundResource(R.drawable.shape_round)
            layout_contact.setBackgroundResource(R.drawable.shape_round)
            layout_wifi.setBackgroundResource(R.drawable.shape_round)
            layout_phone.setBackgroundResource(R.drawable.shape_round)
            layout_sms.setBackgroundResource(R.drawable.shape_round)
            layout_insta.setBackgroundResource(R.drawable.shape_round)
            layout_whatsapp.setBackgroundResource(R.drawable.shape_round)

            linearLayout2.setVisibility(View.GONE)
            linearLayout3.setVisibility(View.GONE)
            linearLayout4.setVisibility(View.GONE)
            linearLayout5.setVisibility(View.GONE)
            linearLayout6.setVisibility(View.GONE)
            linearLayout7.setVisibility(View.GONE)
            linearLayout8.setVisibility(View.GONE)
            linearLayout1.setVisibility(View.VISIBLE)


        }

        layout_web.setOnClickListener {

            i = 2
            text_value = "Website"
            editText = view.findViewById(R.id.editTextTextPersonName4)

            editText.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(mo_no : Editable) {}

                override fun beforeTextChanged(s: CharSequence, start: Int,
                                               count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence, start: Int,
                                           before: Int, count: Int) {
   Log.e( "" ,"value ==" + s )
                    if (s.length >= 1){
                        button.setBackgroundColor(resources.getColor(R.color.light_blue))
                    }else if (s.length == 0){
                        button.setBackgroundColor(resources.getColor(R.color.gray))
                    }
                }
            })

            layout_text.setBackgroundResource(R.drawable.shape_round)
            layout_web.setBackgroundResource(R.drawable.shape)
            layout_contact.setBackgroundResource(R.drawable.shape_round)
            layout_wifi.setBackgroundResource(R.drawable.shape_round)
            layout_phone.setBackgroundResource(R.drawable.shape_round)
            layout_sms.setBackgroundResource(R.drawable.shape_round)
            layout_insta.setBackgroundResource(R.drawable.shape_round)
            layout_whatsapp.setBackgroundResource(R.drawable.shape_round)

            linearLayout1.setVisibility(View.GONE)
            linearLayout3.setVisibility(View.GONE)
            linearLayout4.setVisibility(View.GONE)
            linearLayout5.setVisibility(View.GONE)
            linearLayout6.setVisibility(View.GONE)
            linearLayout7.setVisibility(View.GONE)
            linearLayout8.setVisibility(View.GONE)
            linearLayout2.setVisibility(View.VISIBLE)
        }

        layout_contact.setOnClickListener()
        {

            i = 3

                editText_phone.addTextChangedListener(object : TextWatcher {

                    override fun afterTextChanged(s: Editable) {}

                    override fun beforeTextChanged(
                        s: CharSequence, start: Int,
                        count: Int, after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence, start: Int,
                        before: Int, count: Int
                    ) {

                        if (s.length > 0) {
                            button.setBackgroundColor(resources.getColor(R.color.light_blue))
                        } else if (count <= 0) {
                            button.setBackgroundColor(resources.getColor(R.color.gray))
                        }
                    }
                })


            layout_text.setBackgroundResource(R.drawable.shape_round)
            layout_web.setBackgroundResource(R.drawable.shape_round)
            layout_contact.setBackgroundResource(R.drawable.shape)
            layout_wifi.setBackgroundResource(R.drawable.shape_round)
            layout_phone.setBackgroundResource(R.drawable.shape_round)
            layout_sms.setBackgroundResource(R.drawable.shape_round)
            layout_insta.setBackgroundResource(R.drawable.shape_round)
            layout_whatsapp.setBackgroundResource(R.drawable.shape_round)

            linearLayout1.setVisibility(View.GONE)
            linearLayout2.setVisibility(View.GONE)
            linearLayout4.setVisibility(View.GONE)
            linearLayout5.setVisibility(View.GONE)
            linearLayout6.setVisibility(View.GONE)
            linearLayout7.setVisibility(View.GONE)
            linearLayout8.setVisibility(View.GONE)
            linearLayout3.setVisibility(View.VISIBLE)
        }

        layout_wifi.setOnClickListener()
        {
            i = 4

           id.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(mo_no : Editable) {}

                override fun beforeTextChanged(s: CharSequence, start: Int,
                                               count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence, start: Int,
                                           before: Int, count: Int) {

                    if (count > 0){
                        button.setBackgroundColor(resources.getColor(R.color.light_blue))
                    }else if (count <= 0){
                        button.setBackgroundColor(resources.getColor(R.color.gray))
                    }
                }
            })
            layout_text.setBackgroundResource(R.drawable.shape_round)
            layout_web.setBackgroundResource(R.drawable.shape_round)
            layout_contact.setBackgroundResource(R.drawable.shape_round)
            layout_wifi.setBackgroundResource(R.drawable.shape)
            layout_phone.setBackgroundResource(R.drawable.shape_round)
            layout_sms.setBackgroundResource(R.drawable.shape_round)
            layout_insta.setBackgroundResource(R.drawable.shape_round)
            layout_whatsapp.setBackgroundResource(R.drawable.shape_round)

            linearLayout1.setVisibility(View.GONE)
            linearLayout2.setVisibility(View.GONE)
            linearLayout3.setVisibility(View.GONE)
            linearLayout5.setVisibility(View.GONE)
            linearLayout6.setVisibility(View.GONE)
            linearLayout7.setVisibility(View.GONE)
            linearLayout8.setVisibility(View.GONE)
            linearLayout4.setVisibility(View.VISIBLE)
        }

        switch.setOnClickListener {
            if (switch.isChecked){
                Hiden_Network = "Enable"
            }
            else{
                Hiden_Network = "Disable"
            }
        }

        layout_phone.setOnClickListener()
        {

            i = 5

            editText_no.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(mo_no : Editable) {}

                override fun beforeTextChanged(s: CharSequence, start: Int,
                                               count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence, start: Int,
                                           before: Int, count: Int) {

                    if (count > 0){
                        button.setBackgroundColor(resources.getColor(R.color.light_blue))
                    }else if (count <= 0){
                        button.setBackgroundColor(resources.getColor(R.color.gray))
                    }
                }
            })

            layout_text.setBackgroundResource(R.drawable.shape_round)
            layout_web.setBackgroundResource(R.drawable.shape_round)
            layout_contact.setBackgroundResource(R.drawable.shape_round)
            layout_wifi.setBackgroundResource(R.drawable.shape_round)
            layout_phone.setBackgroundResource(R.drawable.shape)
            layout_sms.setBackgroundResource(R.drawable.shape_round)
            layout_insta.setBackgroundResource(R.drawable.shape_round)
            layout_whatsapp.setBackgroundResource(R.drawable.shape_round)

            linearLayout1.setVisibility(View.GONE)
            linearLayout2.setVisibility(View.GONE)
            linearLayout3.setVisibility(View.GONE)
            linearLayout4.setVisibility(View.GONE)
            linearLayout6.setVisibility(View.GONE)
            linearLayout7.setVisibility(View.GONE)
            linearLayout8.setVisibility(View.GONE)
            linearLayout5.setVisibility(View.VISIBLE)
        }

        layout_sms.setOnClickListener()
        {
            i = 6
            editText_recipient.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(mo_no : Editable) {}

                override fun beforeTextChanged(s: CharSequence, start: Int,
                                               count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence, start: Int,
                                           before: Int, count: Int) {

                    if (count > 0){
                        button.setBackgroundColor(resources.getColor(R.color.light_blue))
                    }else if (count <= 0){
                        button.setBackgroundColor(resources.getColor(R.color.gray))
                    }
                }
            })

            layout_text.setBackgroundResource(R.drawable.shape_round)
            layout_web.setBackgroundResource(R.drawable.shape_round)
            layout_contact.setBackgroundResource(R.drawable.shape_round)
            layout_wifi.setBackgroundResource(R.drawable.shape_round)
            layout_phone.setBackgroundResource(R.drawable.shape_round)
            layout_sms.setBackgroundResource(R.drawable.shape)
            layout_insta.setBackgroundResource(R.drawable.shape_round)
            layout_whatsapp.setBackgroundResource(R.drawable.shape_round)

            linearLayout1.setVisibility(View.GONE)
            linearLayout2.setVisibility(View.GONE)
            linearLayout3.setVisibility(View.GONE)
            linearLayout4.setVisibility(View.GONE)
            linearLayout5.setVisibility(View.GONE)
            linearLayout7.setVisibility(View.GONE)
            linearLayout8.setVisibility(View.GONE)
            linearLayout6.setVisibility(View.VISIBLE)
        }

        layout_insta.setOnClickListener()
        {
            i = 7
            text_value = "Instagram"
            editText = view.findViewById(R.id.PersonName4)

            editText.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(mo_no : Editable) {}

                override fun beforeTextChanged(s: CharSequence, start: Int,
                                               count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence, start: Int,
                                           before: Int, count: Int) {

                    if (count >= 1){
                        button.setBackgroundColor(resources.getColor(R.color.light_blue))
                    }else if (count == 0){
                        button.setBackgroundColor(resources.getColor(R.color.gray))
                    }
                }
            })

            layout_text.setBackgroundResource(R.drawable.shape_round)
            layout_web.setBackgroundResource(R.drawable.shape_round)
            layout_contact.setBackgroundResource(R.drawable.shape_round)
            layout_wifi.setBackgroundResource(R.drawable.shape_round)
            layout_phone.setBackgroundResource(R.drawable.shape_round)
            layout_sms.setBackgroundResource(R.drawable.shape_round)
            layout_insta.setBackgroundResource(R.drawable.shape)
            layout_whatsapp.setBackgroundResource(R.drawable.shape_round)

            linearLayout1.setVisibility(View.GONE)
            linearLayout2.setVisibility(View.GONE)
            linearLayout3.setVisibility(View.GONE)
            linearLayout4.setVisibility(View.GONE)
            linearLayout5.setVisibility(View.GONE)
            linearLayout6.setVisibility(View.GONE)
            linearLayout8.setVisibility(View.GONE)
            linearLayout7.setVisibility(View.VISIBLE)
        }

        layout_whatsapp.setOnClickListener()
        {

            i = 8

            editText_whatsApp.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(mo_no : Editable) {}

                override fun beforeTextChanged(s: CharSequence, start: Int,
                                               count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence, start: Int,
                                           before: Int, count: Int) {

                    if (count > 0){
                        button.setBackgroundColor(resources.getColor(R.color.light_blue))
                    }else if (count <= 0){
                        button.setBackgroundColor(resources.getColor(R.color.gray))
                    }
                }
            })

            layout_text.setBackgroundResource(R.drawable.shape_round)
            layout_web.setBackgroundResource(R.drawable.shape_round)
            layout_contact.setBackgroundResource(R.drawable.shape_round)
            layout_wifi.setBackgroundResource(R.drawable.shape_round)
            layout_phone.setBackgroundResource(R.drawable.shape_round)
            layout_sms.setBackgroundResource(R.drawable.shape_round)
            layout_insta.setBackgroundResource(R.drawable.shape_round)
            layout_whatsapp.setBackgroundResource(R.drawable.shape)

            linearLayout1.setVisibility(View.GONE)
            linearLayout2.setVisibility(View.GONE)
            linearLayout3.setVisibility(View.GONE)
            linearLayout4.setVisibility(View.GONE)
            linearLayout5.setVisibility(View.GONE)
            linearLayout6.setVisibility(View.GONE)
            linearLayout7.setVisibility(View.GONE)
            linearLayout8.setVisibility(View.VISIBLE)
        }


        textView9.setOnClickListener {

            val editText: EditText = view.findViewById<EditText>(R.id.editTextTextPersonName4)
            editText.setText(editText.text.toString() + "http:/")
            editText.setSelection(editText.length())
        }
        textView12.setOnClickListener {

            val editText: EditText = view.findViewById<EditText>(R.id.editTextTextPersonName4)
            editText.setText(editText.text.toString() + "https://")
            editText.setSelection(editText.length())
        }
        textView13.setOnClickListener {

            val editText: EditText = view.findViewById<EditText>(R.id.editTextTextPersonName4)
            editText.setText(editText.text.toString() + "www.")
            editText.setSelection(editText.length())
        }
        textView14.setOnClickListener {

            val editText: EditText = view.findViewById<EditText>(R.id.editTextTextPersonName4)
            editText.setText(editText.text.toString() + ".com")
            editText.setSelection(editText.length())
        }


        wpa.setOnClickListener {
            wpa.setTextColor(Color.WHITE)
            wep.setTextColor(Color.BLACK)
            none.setTextColor(Color.BLACK)
            wpa.setBackgroundColor(resources.getColor(R.color.light_blue))
            wep.setBackgroundColor(resources.getColor(R.color.background_gray))
            none.setBackgroundColor(resources.getColor(R.color.background_gray))
            strwebSecurity = "WPA/WPA2"
        }
        wep.setOnClickListener {
            wep.setTextColor(Color.WHITE)
            wpa.setTextColor(Color.BLACK)
            none.setTextColor(Color.BLACK)
            wep.setBackgroundColor(resources.getColor(R.color.light_blue))
            wpa.setBackgroundColor(resources.getColor(R.color.background_gray))
            none.setBackgroundColor(resources.getColor(R.color.background_gray))
            strwebSecurity = "WEP"
        }
        none.setOnClickListener {
            none.setTextColor(Color.WHITE)
            wpa.setTextColor(Color.BLACK)
            wep.setTextColor(Color.BLACK)
            none.setBackgroundColor(resources.getColor(R.color.light_blue))
            wpa.setBackgroundColor(resources.getColor(R.color.background_gray))
            wep.setBackgroundColor(resources.getColor(R.color.background_gray))
            strwebSecurity = "None"
        }




    editText.addTextChangedListener(object : TextWatcher {

        override fun afterTextChanged(mo_no : Editable) {}

        override fun beforeTextChanged(s: CharSequence, start: Int,
                                       count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence, start: Int,
                                   before: Int, count: Int) {

            if (count > 0){
                button.setBackgroundColor(resources.getColor(R.color.light_blue))
            }else if (count <= 0){
                button.setBackgroundColor(resources.getColor(R.color.gray))
            }
        }
    })

            button.setOnClickListener {

                when (i) {
                    3 -> qr_contact()
                    5 -> qr_phone()
                    6 -> qr_sms()
                    8 -> qr_whatsapp()
                    4 -> qr_wifi()
                    else -> {
                        qrgenerate()
                    }
                }
            }
        }

    // convert Bitmap to string
    fun BitMapToString(bitmap: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }


    // QRcode generater for wifi
//    fun getQrCodeBitmap(ssid: String, password: String): Bitmap {
//
//
//    }
    fun qr_wifi(){

        val ssid = id.text.toString()
        val password = password.text.toString()
        if(ssid.isEmpty() && password.isEmpty())
        {
            Toast.makeText(context, "Enter some details ", Toast.LENGTH_SHORT).show()
        }
          else {
            val size = 512 //pixels
            val qrCodeContent = "WIFI_NAME :- " + ssid +"\n" + "Security :- " + strwebSecurity + "\n" + "Password :- " + password + "\n" + "Hidden Network :- " + Hiden_Network
            val hints = hashMapOf<EncodeHintType, Int>().also {
                it[EncodeHintType.MARGIN] = 1
            } // Make the QR code buffer border narrower
            val bits =
                QRCodeWriter().encode(
                    qrCodeContent,
                    BarcodeFormat.QR_CODE,
                    size,
                    size
                )
            val bmp1 = Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565)
            for (x in 0 until size) {
                for (y in 0 until size) {
                    bmp1.setPixel(
                        x,
                        y,
                        if (bits[x, y]) Color.BLACK else Color.WHITE
                    )
                }
            }
            val massage = BitMapToString(bmp1)
            val text_msg = "Wifi"
            val intent =
                Intent(requireContext(), qr_generate::class.java)
            intent.putExtra("qrCodeValue", massage)
            intent.putExtra("msgValue", text_msg)
            intent.putExtra("msg_hint", "Wi-Fi")

            startActivity(intent)



        }


    }
    fun qrgenerate(){
        val qr = editText.text.toString()
        val text_msg = qr
        if (qr.isEmpty()) {
            Toast.makeText(context, "Enter some details ", Toast.LENGTH_SHORT).show()
        } else {
            val writer = QRCodeWriter()
            try {

                val bitMatrix = writer.encode(qr, BarcodeFormat.QR_CODE, 512, 512)
                val width = bitMatrix.width
                val height = bitMatrix.height
                val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
                for (x in 0 until width) {
                    for (y in 0 until height) {
                        bmp.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                    }
                }

                val massage = BitMapToString(bmp)
                val intent =
                    Intent(requireContext(), qr_generate::class.java)
                intent.putExtra("qrCodeValue", massage)
                intent.putExtra("msgValue",text_msg)
                intent.putExtra("msg_hint",text_value )
                startActivity(intent)

                val scan =
                    Intent(requireContext(), Itemdetails_qrscan::class.java)
                scan.putExtra("msg_hint", text_value)


//               qrcode.setImageBitmap(bmp)
            } catch (e: WriterException) {
                e.printStackTrace()
            }

        }
    }

    fun qr_contact(){

      val qr1 = "Name:  " + editText_name.text.toString() + "\n"+"Phone No.:  " + editText_phone.text.toString()  +"\n"+"E-mail:" + editText_email.text.toString() +"\n"+
          "Company:"  + editText_company.text.toString() + "\n" +"Job title:  " + editText_job.text.toString() + "\n"+ "Address:  "+ editText_address.text.toString() + "\n"+"Other:  " + editText_other.text.toString()
        val text_msg = qr1

        if(editText_name.text.toString().isEmpty() && editText_phone.text.toString().isEmpty() && editText_email.text.toString().isEmpty()
            && editText_company.text.toString().isEmpty() && editText_job.text.toString().isEmpty() && editText_address.text.toString().isEmpty()
            && editText_other.text.toString().isEmpty())
            {
                Toast.makeText(context, "Enter some details ", Toast.LENGTH_SHORT).show()
            }
        else {
            val writer = QRCodeWriter()
            try {

                val bitMatrix = writer.encode(qr1, BarcodeFormat.QR_CODE, 512, 512)
                val width = bitMatrix.width
                val height = bitMatrix.height
                val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
                for (x in 0 until width) {
                    for (y in 0 until height) {
                        bmp.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                    }
                }

                val massage = BitMapToString(bmp)
                val intent =
                    Intent(requireContext(), qr_generate::class.java)
                intent.putExtra("qrCodeValue", massage)
                intent.putExtra("msgValue",text_msg)
                intent.putExtra("msg_hint","Contact" )
                startActivity(intent)

                val scan =
                    Intent(requireContext(), Itemdetails_qrscan::class.java)
                scan.putExtra("msg_hint", "Contact")
//               qrcode.setImageBitmap(bmp)
            } catch (e: WriterException) {
                e.printStackTrace()
            }

        }
    }
    fun qr_sms(){

        val qr2 = "Message to:  " + editText_recipient.text.toString() + "\n"+"Message:  " +editText_msg.text.toString()
        val text_msg = qr2
        if (editText_recipient.text.toString().isEmpty() && editText_msg.text.toString().isEmpty() ) {
            Toast.makeText(context, "Enter some details ", Toast.LENGTH_SHORT).show()
        } else {
            val writer = QRCodeWriter()
            try {

                val bitMatrix = writer.encode(qr2, BarcodeFormat.QR_CODE, 512, 512)
                val width = bitMatrix.width
                val height = bitMatrix.height
                val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
                for (x in 0 until width) {
                    for (y in 0 until height) {
                        bmp.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                    }
                }

                val massage = BitMapToString(bmp)
                val intent =
                    Intent(requireContext(), qr_generate::class.java)
                intent.putExtra("qrCodeValue", massage)
                intent.putExtra("msgValue",text_msg)
                intent.putExtra("msg_hint","SMS" )
                startActivity(intent)

                val scan =
                    Intent(requireContext(), Itemdetails_qrscan::class.java)
                scan.putExtra("msg_hint", "SMS")
//               qrcode.setImageBitmap(bmp)
            } catch (e: WriterException) {
                e.printStackTrace()
            }

        }
    }


    fun qr_phone(){
        val mo_no = editText_no.text.toString()
        val qr3 = "tel:+91 $mo_no"
        val text_msg = qr3
        if (editText_no.text.toString().isEmpty()) {
            Toast.makeText(context, "Enter some details ", Toast.LENGTH_SHORT).show()
        } else {

            val writer = QRCodeWriter()
            try {

                val bitMatrix = writer.encode(qr3, BarcodeFormat.QR_CODE, 512, 512)
                val width = bitMatrix.width
                val height = bitMatrix.height
                val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
                for (x in 0 until width) {
                    for (y in 0 until height) {
                        bmp.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                    }
                }

                val massage = BitMapToString(bmp)
                val intent =
                    Intent(requireContext(), qr_generate::class.java)
                intent.putExtra("qrCodeValue", massage)
                intent.putExtra("msgValue",text_msg)
                intent.putExtra("msg_hint","Phone" )
                startActivity(intent)

                val scan =
                    Intent(requireContext(), Itemdetails_qrscan::class.java)
                scan.putExtra("msg_hint", "Phone")
//               qrcode.setImageBitmap(bmp)
            } catch (e: WriterException) {
                e.printStackTrace()
            }

        }
    }
    fun qr_whatsapp(){
        val wp_no = editText_whatsApp.text.toString()
        val qr4 = " $wp_no"
        val text_msg = qr4
//        tel:+91
        if (editText_whatsApp.text.toString().isEmpty()) {
            Toast.makeText(context, "Enter some details ", Toast.LENGTH_SHORT).show()
        } else {
            val writer = QRCodeWriter()
            try {

                val bitMatrix = writer.encode(qr4, BarcodeFormat.QR_CODE, 512, 512)
                val width = bitMatrix.width
                val height = bitMatrix.height
                val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
                for (x in 0 until width) {
                    for (y in 0 until height) {
                        bmp.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                    }
                }

                val massage = BitMapToString(bmp)
                val intent = Intent(requireContext(), qr_generate::class.java)
                intent.putExtra("qrCodeValue", massage)
                intent.putExtra("msgValue",text_msg)
                intent.putExtra("msg_hint","WhatsApp" )
                startActivity(intent)

                val scan =
                    Intent(requireContext(), Itemdetails_qrscan::class.java)
                scan.putExtra("hint", "WhatsApp")

//               qrcode.setImageBitmap(bmp)
            } catch (e: WriterException) {
                e.printStackTrace()
            }

        }
    }


}

