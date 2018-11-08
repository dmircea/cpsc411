package cpsc411.app1

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_volume_and_area.*
import kotlin.math.pow
import kotlin.math.sqrt

class VolumeAndArea : AppCompatActivity(){


    protected lateinit var myStrArr : Array<String>
    protected lateinit var option : TextView
    protected lateinit var spin : Spinner

    protected var volume : Double = 0.0
    protected var area : Double = 0.0

    protected var pos : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volume_and_area)

        myStrArr = resources.getStringArray(R.array.figure_array)
        option = seeOption
        spin = optionSpin

        //  optionSpin listeners will be implemented

        spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                option.text = myStrArr[position]
                pos = position
                connectorFunction()

            }

        }
        heightText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}  //  NOTHING HAPPENS HERE

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {} //  NOTHING HAPPENS HERE

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                connectorFunction()
            }

        })
        lengthText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}  //  NOTHING HAPPENS HERE

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {} //  NOTHING HAPPENS HERE

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                connectorFunction()
            }

        })
        widthText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}  //  NOTHING HAPPENS HERE

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {} //  NOTHING HAPPENS HERE

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                connectorFunction()
            }

        })
        radiusText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}  //  NOTHING HAPPENS HERE

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {} //  NOTHING HAPPENS HERE

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                connectorFunction()
            }

        })


    }

    fun connectorFunction()
    {
        if(pos == 0)
        {
            radiusText.visibility = View.GONE
            widthText.visibility = View.VISIBLE
            lengthText.visibility = View.VISIBLE
            heightText.visibility = View.VISIBLE
            prism()
        }
        if(pos == 1)
        {
            radiusText.visibility = View.GONE
            widthText.visibility = View.VISIBLE
            lengthText.visibility = View.VISIBLE
            heightText.visibility = View.VISIBLE
            pyramid()
        }
        if(pos == 2)
        {
            radiusText.visibility = View.VISIBLE
            widthText.visibility = View.GONE
            lengthText.visibility = View.GONE
            heightText.visibility = View.GONE
            sphere()
        }
        if(pos == 3)
        {
            radiusText.visibility = View.VISIBLE
            widthText.visibility = View.GONE
            lengthText.visibility = View.GONE
            heightText.visibility = View.VISIBLE
            cylinder()
        }
        if(pos == 4)
        {
            radiusText.visibility = View.VISIBLE
            widthText.visibility = View.GONE
            lengthText.visibility = View.GONE
            heightText.visibility = View.VISIBLE
            cone()
        }
    }


    //      ----------------------------------------------------------------------
    //      BELOW ARE THE FUNCTIONS USED TO GET VALUES AND CALL MATH FUNCTIONS
    //      ----------------------------------------------------------------------

    fun prism() {
        if(!widthText.text.isEmpty() && !lengthText.text.isEmpty() && !heightText.text.isEmpty())
        {
            var wd = widthText.text.toString().toDouble()
            var lg = lengthText.text.toString().toDouble()
            var ht = heightText.text.toString().toDouble()
            volume = volumePrism(wd,lg,ht)
            area = areaPrism(wd,lg,ht)
            volumeView.text = getString(R.string.volumeAnswer,volume)
            areaView.text = getString(R.string.areaAnswer,area)
        }
    }


    fun pyramid() {
        if(!widthText.text.isEmpty() && !heightText.text.isEmpty() && !lengthText.text.isEmpty())
        {
            var wd = widthText.text.toString().toDouble()
            var lg = lengthText.text.toString().toDouble()
            var ht = heightText.text.toString().toDouble()
            volume = volumeSquarePyr(wd,lg,ht)
            area = areaSquarePyr(wd,lg,ht)
            volumeView.text = getString(R.string.volumeAnswer,volume)
            areaView.text = getString(R.string.areaAnswer,area)
        }
    }

    fun sphere()
    {
        if(!radiusText.text.isEmpty())
        {
            var r = radiusText.text.toString().toDouble()
            volume = volumeSphere(r)
            area = areaSphere(r)
            volumeView.text = getString(R.string.volumeAnswer,volume)
            areaView.text = getString(R.string.areaAnswer,area)
        }
    }

    fun cylinder()
    {
        if(!radiusText.text.isEmpty() && !heightText.text.isEmpty())
        {
            var r = radiusText.text.toString().toDouble()
            var h = heightText.text.toString().toDouble()
            volume = volumeCylinder(r,h)
            area = areaCylinder(r,h)
            volumeView.text = getString(R.string.volumeAnswer,volume)
            areaView.text = getString(R.string.areaAnswer,area)
        }
    }

    fun cone()
    {
        if(!radiusText.text.isEmpty() && !heightText.text.isEmpty())
        {
            var r = radiusText.text.toString().toDouble()
            var h = heightText.text.toString().toDouble()
            volume = volumeCone(r,h)
            area = areaCone(r,h)
            volumeView.text = getString(R.string.volumeAnswer,volume)
            areaView.text = getString(R.string.areaAnswer,area)
        }
    }



    //      --------------------------------------------------
    //      BELOW ARE THE MATHEMATICAL CALCULATION FUNCTIONS
    //      --------------------------------------------------

    fun volumePrism(width : Double, length : Double, height : Double): Double
    {
        return width * length * height
    }
    fun volumeSquarePyr(width : Double, length : Double, height : Double): Double
    {
        return volumePrism(width,length,height) / 3
    }
    fun volumeSphere(radius : Double) : Double
    {
        return 4 / 3 * radius.pow(3) * 3.14
    }
    fun volumeCylinder(radius: Double,height: Double) : Double
    {
        return 3.14 * radius.pow(2) * height
    }
    fun volumeCone(radius: Double, height: Double) : Double
    {
        return volumeCylinder(radius,height) / 3
    }



    fun areaPrism(width : Double, length : Double, height : Double) : Double
    {
        return 2 * width * length + 2 * width * height + 2 * length * height
    }
    fun areaSquarePyr(width : Double, length : Double, height : Double) : Double
    {
        var slantLengthW = sqrt((width / 2).pow(2) + height.pow(2))
        var slantLengthL = sqrt((length / 2).pow(2) + height.pow(2))

        return width * length + width * slantLengthL + length * slantLengthW
    }
    fun areaSphere(radius : Double) : Double
    {
        return 4 * radius.pow(2) * 3.14
    }
    fun areaCylinder(radius: Double,height: Double) : Double
    {
        return 2 * 3.14 * radius * height + 2 * 3.14 * radius.pow(2)
    }
    fun areaCone(radius: Double, height: Double) : Double
    {
        return 3.14 * radius * (radius + sqrt(height.pow(2) + radius.pow(2)))
    }





}
