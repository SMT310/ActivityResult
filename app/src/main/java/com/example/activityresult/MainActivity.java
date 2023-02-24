package com.example.activityresult;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textResult;
    private EditText editNum1;
    private EditText editNum2;
    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            Intent data = result.getData();
            if(result != null && result.getResultCode() == RESULT_OK){
                int result1 = data.getIntExtra("result", 0);
                textResult.setText("" + result1);
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textResult = findViewById(R.id.text_view_result);
        editNum1 = findViewById((R.id.edit_text_number1));
        editNum2 = findViewById((R.id.edit_text_number2));
        Button buttonOpenActivity2 = findViewById(R.id.button_open_activity2);
        buttonOpenActivity2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(editNum1.getText().toString().equals("") || editNum2.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Please insert numbers", Toast.LENGTH_SHORT).show();
                }else {
                    int number1 = Integer.parseInt(editNum1.getText().toString());
                    int number2 = Integer.parseInt(editNum2.getText().toString());

                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("number1", number1);
                    intent.putExtra("number2", number2);
                     startForResult.launch(intent);
                }
            }
        });
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == 1){
//            if (resultCode == RESULT_OK){
//                int result = data.getIntExtra("result", 0);
//                textResult.setText("" + result);
//            }
//            if(resultCode == RESULT_CANCELED){
//                textResult.setText("Nothing Selected");
//            }
//        }
//    }
}