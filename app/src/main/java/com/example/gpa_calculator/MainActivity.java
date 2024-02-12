package com.example.gpa_calculator;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText course1Grade, course2Grade, course3Grade, course4Grade, course5Grade;
    TextView displayGpa;
    Button computeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        course1Grade = findViewById(R.id.course1_grade);
        course2Grade = findViewById(R.id.course2_grade);
        course3Grade = findViewById(R.id.course3_grade);
        course4Grade = findViewById(R.id.course4_grade);
        course5Grade = findViewById(R.id.course5_grade);

        displayGpa = findViewById(R.id.display_gpa);
        computeButton = findViewById(R.id.compute_button);

        computeButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                computeGPA();
                computeButton.setText("Clear Form");
            }
        });

        // Restore button text to default when any input field is focused
        View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    computeButton.setText("Compute GPA");
                }
            }
        };

        course1Grade.setOnFocusChangeListener(onFocusChangeListener);
        course2Grade.setOnFocusChangeListener(onFocusChangeListener);
        course3Grade.setOnFocusChangeListener(onFocusChangeListener);
        course4Grade.setOnFocusChangeListener(onFocusChangeListener);
        course5Grade.setOnFocusChangeListener(onFocusChangeListener);
    }

    @SuppressLint({"DefaultLocale,SetTextI18n"})
    private void computeGPA() {
        String course1GradeStr = course1Grade.getText().toString().trim();
        String course2GradeStr = course2Grade.getText().toString().trim();
        String course3GradeStr = course3Grade.getText().toString().trim();
        String course4GradeStr = course4Grade.getText().toString().trim();
        String course5GradeStr = course5Grade.getText().toString().trim();

        if (TextUtils.isEmpty(course1GradeStr) || TextUtils.isEmpty(course2GradeStr) ||
                TextUtils.isEmpty(course3GradeStr) || TextUtils.isEmpty(course4GradeStr) ||
                TextUtils.isEmpty(course5GradeStr)) {
            displayGpa.setText("Please fill in all fields");
            return;
        }

        double totalGrade = convertGradeToNumeric(course1GradeStr) + convertGradeToNumeric(course2GradeStr) +
                convertGradeToNumeric(course3GradeStr) + convertGradeToNumeric(course4GradeStr) +
                convertGradeToNumeric(course5GradeStr);

        double gpa = totalGrade / 5.0;

        displayGpa.setText(String.format("%.2f", gpa));

        if (gpa < 2.0) {
            displayGpa.setBackgroundColor(Color.RED);
        } else if (gpa >= 2.0 && gpa < 3.0) {
            displayGpa.setBackgroundColor(Color.YELLOW);
        } else {
            displayGpa.setBackgroundColor(Color.GREEN);
        }

    }

    private double convertGradeToNumeric(String grade) {
        // Convert grade to numeric value, e.g., A=4.0, B=3.0, etc.
        switch (grade.toUpperCase()) {
            case "A":
                return 4.0;
            case "B":
                return 3.0;
            case "C":
                return 2.0;
            case "D":
                return 1.0;
            default:
                return 0.0;
        }
    }
}