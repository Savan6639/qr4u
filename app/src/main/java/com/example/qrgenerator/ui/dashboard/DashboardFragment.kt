package com.example.qrgenerator.ui.dashboard

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.PermissionRequest
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.test.core.app.ApplicationProvider
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.example.qrgenerator.R
import com.example.qrgenerator.Scan_result


class DashboardFragment : Fragment() {
        private lateinit var codeScanner: CodeScanner
    private val PERMISSION_REQUEST_CODE = 200

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View {
            return inflater.inflate(R.layout.fragment_dashboard, container, false)


        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            val scannerView = view.findViewById<CodeScannerView>(R.id.scanner_view)
            val activity = requireActivity()

            codeScanner = CodeScanner(activity, scannerView)
            if (checkPermission()) {

                codeScanner.decodeCallback = DecodeCallback {
                    activity.runOnUiThread {
                        val intent = Intent(requireContext(), Scan_result::class.java)
                        intent.putExtra("qr_scan", it.text)
                        startActivity(intent)
                    Toast.makeText(activity, it.text, Toast.LENGTH_LONG).show()
                    }

                }
                scannerView.setOnClickListener {
                    codeScanner.startPreview()
                }

            }else
            {
                ActivityCompat.requestPermissions(requireActivity() , arrayOf(Manifest.permission.CAMERA),PERMISSION_REQUEST_CODE)
            }
        }




    private fun checkPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;
    }

    override fun onResume() {
            super.onResume()
            codeScanner.startPreview()
        }

        override fun onPause() {
            codeScanner.releaseResources()
            super.onPause()
        }

        override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ){
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if (requestCode == PERMISSION_REQUEST_CODE){
                if (grantResults.isNotEmpty()&& grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(requireContext(),"Camera Permission Granted" ,Toast.LENGTH_SHORT)
                }
                else{
                    Toast.makeText(requireContext(),"Camera Permission Denied" ,Toast.LENGTH_SHORT)
                }
            }
        }


    }


