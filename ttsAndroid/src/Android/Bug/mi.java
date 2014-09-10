/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Android.Bug;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 *
 * @author Sankar
 */
public class mi extends Activity implements OnClickListener {

//    private EditText inputText;
//    private Button speakButton;
//    private Button openinbox;
//    private Button customtext;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        View openinbox =  findViewById(R.id.open_inbox);
        openinbox.setOnClickListener(this);
        View customtext =  findViewById(R.id.custom_text);
        customtext.setOnClickListener(this);

//        inputText = (EditText) findViewById(R.id.input_text);
//        speakButton = (Button) findViewById(R.id.speak_button);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.open_inbox:
                Intent j = new Intent(this, inbox.class);
                startActivity(j);
                break;
            case R.id.custom_text:
                Intent i = new Intent(this, Custom.class);
                startActivity(i);
                break;
// More buttons go here (if any) ...
        }
    }

}
