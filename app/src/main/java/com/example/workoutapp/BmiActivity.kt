package com.example.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.workoutapp.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BmiActivity : AppCompatActivity() {

    companion object{
        private const val METRIC_UNITS_VIEW ="METRIC_UNITS_VIEW"
        private const val US_UNITS_VIEW ="US_UNITS_VIEW"
    }

    private var binding : ActivityBmiBinding?=null
    private var currentVisibleView :String = "METRICS_UNITS_VIEW"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBmiActivity)
        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "CALCULATE BMI"
        }
        binding?.toolbarBmiActivity?.setNavigationOnClickListener {
            onBackPressed()
        }
        makeVisibleMetricUnitsView()

        binding?.radioGroup?.setOnCheckedChangeListener{_,checkedId:Int ->
            if(checkedId == R.id.rbMetricUnits){
                makeVisibleMetricUnitsView()
            }else{
                makeVisibleUSUnitsView()
            }

        }

        binding?.btncalculate?.setOnClickListener {
                  calculateUnits()
        }
    }

    private fun validateMetricUnits():Boolean {
        var isValid = true
        if(binding?.etMetricUnitHeight?.text.toString().isEmpty())
            isValid = false
        else if(binding?.etMetricUnitWeight?.text.toString().isEmpty())
            isValid = false

        return isValid
    }

    private fun calculateUnits(){
        if(currentVisibleView == METRIC_UNITS_VIEW){
            if(validateMetricUnits()){

                val heightValue : Float = binding?.etMetricUnitHeight.toString().toFloat()/100

                val weightValue :Float = binding?.etMetricUnitWeight.toString().toFloat()

                val bmi = weightValue/(heightValue*heightValue)
                displayBMIResult(bmi)

            }else
                Toast.makeText(this@BmiActivity,"Enter height and weight", Toast.LENGTH_SHORT).show()
        }else{
            if(validateUSUnits()){
                val HeightFeet : String = binding?.etUSMetricUnitHeightFeet?.text.toString()
                val HeightInch : String = binding?.etMetricUnitHeightInch?.text.toString()
                val USWeightValue : Float = binding?.etUSMetricUnitWeight?.text.toString().toFloat()

                val heightValue = HeightInch.toFloat() + HeightFeet.toFloat() *12

                val bmi = 703 * (USWeightValue / (heightValue * heightValue))

                displayBMIResult(bmi)
            }else{
                Toast.makeText(this@BmiActivity,"Enter height and weight", Toast.LENGTH_SHORT).show()
            }


        }
    }

    private fun validateUSUnits():Boolean {
        var isValid = true
        when {
            binding?.etMetricUnitHeightInch?.text.toString().isEmpty() -> {
            isValid = false
        }
            binding?.etUSMetricUnitWeight?.text.toString().isEmpty() -> {
                isValid = false
            }
            binding?.etUSMetricUnitHeightFeet?.text.toString().isEmpty() ->{
                isValid = false
            }
        }
        return isValid
    }


    private fun makeVisibleMetricUnitsView(){
        currentVisibleView = METRIC_UNITS_VIEW
        binding?.tilMetricUnitHeight?.visibility = View.VISIBLE
        binding?.tilMetricUnitWeight?.visibility = View.VISIBLE

        binding?.tilUSMetricUnitWeight?.visibility = View.GONE
        binding?.tilMetricUnitHeightFeet?.visibility = View.GONE
        binding?.tilMetricUnitHeightInch?.visibility = View.GONE

        binding?.etMetricUnitHeightInch?.text!!.clear()
        binding?.etUSMetricUnitWeight?.text!!.clear()
        binding?.etUSMetricUnitHeightFeet?.text!!.clear()

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE
    }

    private fun makeVisibleUSUnitsView(){
        currentVisibleView = US_UNITS_VIEW
        binding?.tilUSMetricUnitWeight?.visibility = View.VISIBLE
        binding?.tilMetricUnitHeightInch?.visibility = View.VISIBLE
        binding?.tilMetricUnitHeightFeet?.visibility = View.VISIBLE

        binding?.tilMetricUnitHeight?.visibility = View.INVISIBLE
        binding?.tilMetricUnitWeight?.visibility = View.INVISIBLE

        binding?.etMetricUnitHeight?.text!!.clear()
        binding?.etMetricUnitWeight?.text!!.clear()

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE
    }
    private fun displayBMIResult(bmi : Float){
        val bmiLabel : String
        val bmiDescription : String

        if(bmi.compareTo(15f)<=0){
            bmiLabel = "Very severely underweight"
            bmiDescription = "You need to take care of yourself and  eat more"
        }
        else if(bmi.compareTo(15f)>0 && bmi.compareTo(16f) <=0){
            bmiLabel = "Severely underweight"
            bmiDescription = "Start doing some exercises and eat more!!"
        }
        else if(bmi.compareTo(16f)>0 && bmi.compareTo(18.5f) <=0){
            bmiLabel = "Little underweight"
            bmiDescription = "You can do it just a little more hard work and you will be in the best shape of your life"
        }
        else if(bmi.compareTo(18.5f)>0 && bmi.compareTo(25f) <=0){
            bmiLabel = "Normal"
            bmiDescription = "Congratulations,it's good to see a fit person"
        }
        else if(bmi.compareTo(25f)>0 && bmi.compareTo(30f) <=0){
            bmiLabel = "Overweight"
            bmiDescription = "It's seems that you have been eating much,now it's time to get up and do some work"
        }
        else if(bmi.compareTo(30f)>0 && bmi.compareTo(35f) <=0){
            bmiLabel = "Obese || (Moderately obese)"
            bmiDescription = "The zoo needs a cute panda,maybe you could help them :)"
        }
        else {
            bmiLabel = "Severely OBESE"
            bmiDescription = "You need to do a lot of hard work,or maybe try your luck in another lifetime :/"
        }

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2,RoundingMode.HALF_EVEN).toString()
        binding?.llDisplayBMIResult?.visibility= View.VISIBLE

        binding?.BMIValue?.text = bmiValue
        binding?.BMIDescription?.text = bmiDescription
        binding?.BMIType?.text = bmiLabel
    }

}