package us.mentro.hhscool;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Activity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        Intent intent = getIntent();
        String fio = intent.getStringExtra("fio");
        String birth = intent.getStringExtra("birth");
        String gender = intent.getStringExtra("gender");
        String position = intent.getStringExtra("position");
        String prize = intent.getStringExtra("prize");
        String phone = intent.getStringExtra("phone");
        String email = intent.getStringExtra("email");

        if (fio != null && !fio.equals("")) { ((TextView)findViewById(R.id.textView2)).setText(fio); }
        if (birth != null && !birth.equals("")) { ((TextView)findViewById(R.id.textView4)).setText(birth); }
        if (gender != null && !gender.equals("")) { ((TextView)findViewById(R.id.textView6)).setText(gender); }
        if (position != null && !position.equals("")) { ((TextView)findViewById(R.id.textView8)).setText(position); }
        if (prize != null && !prize.equals("")) { ((TextView)findViewById(R.id.textView10)).setText(prize); }
        if (phone != null && !phone.equals("")) { ((TextView)findViewById(R.id.textView12)).setText(phone); }
        if (email != null && !email.equals("")) { ((TextView)findViewById(R.id.textView14)).setText(email); }

        findViewById(R.id.replyBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("reply", ( (EditText) findViewById(R.id.replyText)).getText().toString() );
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity2, menu);
        return true;
    }
    
}
