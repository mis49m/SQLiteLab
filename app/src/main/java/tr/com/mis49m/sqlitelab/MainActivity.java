package tr.com.mis49m.sqlitelab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvCount;
    EditText etID, etName, etPhone;
    Button btnInsert, btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //-- read ui references
        tvCount = (TextView) findViewById(R.id.tv_count);
        etID = (EditText) findViewById(R.id.txt_id);
        etName = (EditText) findViewById(R.id.txt_name);
        etPhone = (EditText) findViewById(R.id.txt_phone);
        etPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        btnInsert = (Button) findViewById(R.id.btn_insert);
        btnUpdate = (Button) findViewById(R.id.btn_update);
        btnDelete = (Button) findViewById(R.id.btn_delete);

        isUpdateForm(false);
    }

    public void insert(View view){

        clearValues();
    }

    public void getContact(View view){

        isUpdateForm(true);
    }

    public void update(View view){


        clearValues();
        isUpdateForm(false);

    }

    private void clearValues(){
        etID.setText("");
        etName.setText("");
        etPhone.setText("");

        isUpdateForm(false);
    }

    private void isUpdateForm(boolean isUpdate){
        btnInsert.setEnabled(!isUpdate);
        btnUpdate.setEnabled(isUpdate);
        btnDelete.setEnabled(isUpdate);
    }

    private void showMessage(String value){
        Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
    }

}
