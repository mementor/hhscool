package us.mentro.hhscool;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Activity1 extends Activity implements View.OnClickListener, DatePickerDialog.OnDateSetListener, View.OnFocusChangeListener {

    private String TAG = "hhscool";
    private String [] genders;

    private String selectedGender = "";
    private int myYear = 1986;
    private int myMonth = 11;
    private int myDay = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);

        this.genders = getResources().getStringArray(R.array.ganders);

        Spinner spinner = (Spinner) findViewById(R.id.data_gender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ganders, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedGender = genders[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        findViewById(R.id.data_birth).setOnFocusChangeListener(this);
        findViewById(R.id.button).setOnClickListener(this);

        findViewById(R.id.btn_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((EditText) findViewById(R.id.data_fio)).setText("Кубарь Роман Юрьевич");
                ((EditText) findViewById(R.id.data_birth)).setText("10.11.1986");
                ((EditText) findViewById(R.id.data_position)).setText("Программист");
                ((EditText) findViewById(R.id.data_prize)).setText("100500");
                ((EditText) findViewById(R.id.data_phone)).setText("+79299029910");
                ((EditText) findViewById(R.id.data_email)).setText("me@mentro.us");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity1, menu);
        return true;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        this.myYear = year;
        this.myMonth = month+1;
        this.myDay = day;
        TextView birth = (TextView) findViewById(R.id.data_birth);
        birth.setText(String.format("%02d.%02d.%04d",myDay,myMonth,myYear));
        Log.d(TAG, "bd: " + String.format("%02d.%02d.%04d", myDay, myMonth, myYear));
    }

    @Override
    public void onClick(View view) {
        if (view.equals(findViewById(R.id.button))) {
            Log.d(TAG, "Lets send resume");

            EditText fio = (EditText) findViewById(R.id.data_fio);

            Intent intent = new Intent(this, Activity2.class);
            intent.putExtra("fio", fio.getText().toString());
            intent.putExtra("birth", ((EditText) findViewById(R.id.data_birth)).getText().toString());
            intent.putExtra("gender", selectedGender);
            intent.putExtra("position", ((EditText) findViewById(R.id.data_position)).getText().toString());
            intent.putExtra("prize", ((EditText) findViewById(R.id.data_prize)).getText().toString());
            intent.putExtra("phone", ((EditText) findViewById(R.id.data_phone)).getText().toString());
            intent.putExtra("email", ((EditText) findViewById(R.id.data_email)).getText().toString());

            startActivityForResult(intent, 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null || resultCode != RESULT_OK) {return;}
        String reply = data.getStringExtra("reply");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(reply).setPositiveButton(getResources().getString(R.string.OK), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ;
            }
        })
        .setTitle(getResources().getString(R.string.replyTitle))
        .create()
        .show();
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        Log.d(TAG, "b: "+b);
        if (b) {
            Log.d(TAG, "Construction dialog");
            DatePickerDialog dp = new DatePickerDialog(view.getContext(), this, myYear,myMonth-1, myDay);
            dp.show();
        }
    }
}
