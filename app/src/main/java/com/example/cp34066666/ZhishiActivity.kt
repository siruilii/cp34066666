package com.example.cp34066666;

import android.app.Activity
import android.os.Bundle
import android.widget.ListView

class ZhishiActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zhishi)

        val healthTips = listOf(
            HealthTip(R.drawable.image01, "Sitting for a long time is harmful, get up and move around regularly", "Sitting for a long time will cause many adverse effects on the body, such as increasing the risk of obesity, cardiovascular disease, diabetes and other chronic diseases. Research has found that every hour of sitting, getting up and moving for 5 to 10 minutes, such as stretching and taking a few steps, can effectively improve blood circulation, relieve muscle stiffness, and reduce health hazards caused by prolonged sitting."),
            HealthTip(R.drawable.image02, "Healthy diet, controlling sugar is key", "Excessive intake of sugar will lead to obesity, dental caries, elevated blood sugar, and increase the risk of diabetes. It is recommended to limit the daily intake of added sugar to no more than 50 grams, preferably below 25 grams. In daily diet, the intake of high sugar foods such as beverages, candies, and pastries should be reduced, and more fiber rich foods such as vegetables, fruits, and whole grains should be eaten to satisfy taste buds and maintain health."),
            HealthTip(R.drawable.image03, "Adequate sleep, repairing the body and mind", "Sleep is crucial for health, and adults should ensure 7 to 9 hours of sleep per day. Good sleep helps the body repair cells, consolidate memory, and regulate the immune system. Avoiding the use of electronic devices before bedtime, creating a quiet and comfortable sleeping environment, and cultivating a regular schedule can effectively improve sleep quality and allow the body and brain to get sufficient rest."),
            HealthTip(R.drawable.image04, "Regular physical examination, early detection and treatment", "Many diseases have no obvious symptoms in the early stages, and regular physical examinations can timely detect potential health problems, achieve early detection, diagnosis, and treatment, and reduce the harm of diseases. It is recommended to undergo a comprehensive physical examination once a year, including basic tests such as blood routine, electrocardiogram, and liver and kidney function. Depending on one's age, gender, family history, etc., targeted tests such as breast examination for women and prostate examination for men can also be added appropriately.")
        )

        val lvHealthTips = findViewById<ListView>(R.id.lv_health_tips)
        lvHealthTips.adapter = HealthTipAdapter(this, healthTips)
    }
}