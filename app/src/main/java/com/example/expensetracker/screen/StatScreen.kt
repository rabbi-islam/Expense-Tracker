package com.example.expensetracker.screen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.expensetracker.R
import com.example.expensetracker.utils.Util
import com.example.expensetracker.viewmodel.StatViewModel
import com.example.expensetracker.viewmodel.StatViewModelFactory
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

@Composable
fun StatScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.left_chevron),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .clickable {
                            navController.navigate("/home")
                        },
                    colorFilter = ColorFilter.tint(Color.Black)
                )
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Center),
                    text = "Statistics",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Image(
                    painter = painterResource(id = R.drawable.dots_menu),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterEnd),
                    colorFilter = ColorFilter.tint(Color.Black)
                )
            }
        }
    ) { paddingValues ->
        val viewModel =
            StatViewModelFactory(navController.context).create(StatViewModel::class.java)
        val dataState = viewModel.entries.collectAsState(initial = emptyList())
        val topEntries = viewModel.topEntry.collectAsState(initial = emptyList())
        Column(modifier = Modifier.padding(paddingValues)) {
            val entries = viewModel.getEntriesForChart(dataState.value)
            LineChart(entries)
            Spacer(modifier = Modifier.height(16.dp))
            TransactionList(modifier = Modifier, list = topEntries.value, "Top Spending")
        }
    }
}

@Composable
fun LineChart(entries: List<Entry>) {
    val context = LocalContext.current
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        factory = {
            val view = LayoutInflater.from(context).inflate(R.layout.stats_line_chart, null)
            view
        }
    ) { view ->
        val lineChart = view.findViewById<LineChart>(R.id.lineChart)
        val dataSet = LineDataSet(entries, "Expenses").apply {
            color = android.graphics.Color.parseColor("#FF2F7E79")
            valueTextColor = android.graphics.Color.BLACK
            lineWidth = 3f
            axisDependency = YAxis.AxisDependency.RIGHT
            setDrawFilled(true)
            mode = LineDataSet.Mode.CUBIC_BEZIER
            valueTextSize = 12f
            valueTextColor = android.graphics.Color.parseColor("#FF2F7E79")
            val drawable = ContextCompat.getDrawable(context,R.drawable.chart_gradient)
            drawable?.let {
                fillDrawable  = it
            }

        }
        lineChart.xAxis.valueFormatter =
            object :com.github.mikephil.charting.formatter.ValueFormatter(){
                override fun getFormattedValue(value: Float): String {
                    return Util.formatDateForChart(value.toLong())
                }
            }

        lineChart.data = LineData(dataSet)
        lineChart.axisLeft.isEnabled = false
        lineChart.axisRight.isEnabled = false
        lineChart.axisLeft.setDrawGridLines(false)
        lineChart.axisRight.setDrawGridLines(false)
        lineChart.xAxis.setDrawGridLines(false)
        lineChart.xAxis.setDrawAxisLine(false)
        lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        lineChart.invalidate()

    }
}


















