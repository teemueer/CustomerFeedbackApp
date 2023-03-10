package com.example.customerfeedbackapp.screens.owner

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.customerfeedbackapp.fonts.ptserif_bold
import com.example.customerfeedbackapp.models.Feedback
import com.example.customerfeedbackapp.screens.customer.ProductViewModel
import com.example.customerfeedbackapp.ui.theme.*
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.DefaultValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener

@Composable
fun ChartView(productViewModel: ProductViewModel, navController: NavController) {
    Column(modifier = Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = productViewModel.currentItem2.title!!,
            fontSize = 20.sp,
            fontFamily = ptserif_bold,
            fontWeight = FontWeight.Bold
        )

        Crossfade(targetState = productViewModel.currentItem2.feedback) { feedbackData ->
            AndroidView(factory = { context ->
                BarChart(context).apply {
				
					// fit chart to whole screen
                    layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                    )
					
                    this.description.isEnabled = false
                    this.legend.isEnabled = false

                    this.xAxis.isEnabled = true
					
					// hide unnecessary lines
                    this.axisLeft.isEnabled = false
                    this.axisRight.isEnabled = false
                    this.axisLeft.axisMinimum = 0f

					// set x-axis to our rating levels 1-5
                    this.xAxis.setLabelCount(5)
                    this.xAxis.valueFormatter =
                        IndexAxisValueFormatter(listOf("1", "2", "3", "4", "5"))
						
					// formatting x-axis
                    this.xAxis.position = XAxis.XAxisPosition.BOTTOM
                    this.xAxis.setCenterAxisLabels(false)
                    this.xAxis.setDrawAxisLine(false)
                    this.xAxis.setDrawGridLines(false)
                    this.xAxis.textSize = 18f

					// space for the bottom menu
                    this.extraBottomOffset = 20f

					// listener for clicking different bars on the chart for navigation to feedback view
                    this.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                        override fun onValueSelected(e: Entry?, h: Highlight?) {
                            productViewModel.currentFeedbackSelected = e?.x?.toInt()!! + 1
                            navController.navigate("OwnerFeedbackView")
                        }

                        override fun onNothingSelected() {}
                    })
                }
            },
                modifier = Modifier.wrapContentSize(),
                update = { updateBarChartWithData(it, feedbackData) }
            )
        }
    }
}

fun updateBarChartWithData(chart: BarChart, data: List<Feedback>) {
    val entries = ArrayList<BarEntry>()

	// sums of different ratings
    val ratings = mutableListOf(0f, 0f, 0f, 0f, 0f)
    for (i in data.indices) {
        ratings[data[i].rating - 1] += 1f
    }

	// insert data
    for ((i, e) in ratings.withIndex())
        entries.add(BarEntry(i.toFloat(), e))
    val ds = BarDataSet(entries, "Feedback")

	// make values whole
    ds.valueFormatter = DefaultValueFormatter(0)

	// colors for different bars
    ds.colors = arrayListOf(
        chartColor1.toArgb(),
        chartColor2.toArgb(),
        chartColor3.toArgb(),
        chartColor4.toArgb(),
        chartColor5.toArgb(),
    )

	// create the chart
    val d = BarData(ds)
    chart.data = d
    chart.data.setValueTextSize(12f)
    chart.invalidate()
}