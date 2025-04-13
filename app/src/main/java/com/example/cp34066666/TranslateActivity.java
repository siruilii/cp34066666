package com.example.cp34066666;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import cz.msebera.android.httpclient.Header;

public class TranslateActivity extends AppCompatActivity {

    private EditText inputEditText;
    private TextView resultTextView;
    private Button translateButton;
    private RadioGroup translationDirectionGroup;
    private String apikey = "44ab296591fcbed8b8a5b1989611f815"; // 请替换为您的 API 密钥

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);

        inputEditText = findViewById(R.id.inputEditText);
        resultTextView = findViewById(R.id.resultTextView);
        translateButton = findViewById(R.id.translateButton);
        translationDirectionGroup = findViewById(R.id.translationDirection);

        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = inputEditText.getText().toString();
                if (!inputText.isEmpty()) {
                    int checkedId = translationDirectionGroup.getCheckedRadioButtonId();
                    RadioButton selectedRadioButton = findViewById(checkedId);
                    String direction = "en"; // 默认为英译汉
                    if (selectedRadioButton != null) {
                        if (selectedRadioButton.getText().toString().equals("Chinese to English")) {
                            direction = "zh"; // 汉译英
                        }
                    }
                    performTranslation(inputText, direction);
                }
            }
        });

    }

    private void performTranslation(String inputText, String direction) {
        String apiurl = "http://api.niutrans.com/NiuTransServer/translation?from=" + direction + "&to=" + (direction.equals("en") ? "zh" : "en") + "&apikey=" + apikey;

        try {
            String encodedInput = URLEncoder.encode(inputText, "utf-8");
            apiurl += "&src_text=" + encodedInput;

            AsyncHttpClient client = new AsyncHttpClient();
            client.get(apiurl, new JsonHttpResponseHandler() {
//                @Override
                public <Header> void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        if (response.has("tgt_text")) {
                            resultTextView.setText(response.getString("tgt_text"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        resultTextView.setText("Error translating text.");
                    }
                }

//                @Override
                public <Header> void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    resultTextView.setText("Translation failed, please check your input or network connection.");
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            resultTextView.setText("Error encoding input text.");
        }
    }
}