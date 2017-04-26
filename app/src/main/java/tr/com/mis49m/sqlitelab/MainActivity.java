package tr.com.mis49m.sqlitelab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvCount;
    EditText etID, etName, etPhone;
    Button btnInsert, btnUpdate, btnDelete;

    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHandler = new DatabaseHandler(MainActivity.this);

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

        Contact contact = new Contact();
        contact.setName(etName.getText().toString());
        contact.setPhoneNumber(etPhone.getText().toString());

        long r = databaseHandler.insertContact(contact);

        clearValues();
    }

    public void getContact(View view){

        int id = Integer.valueOf( etID.getText().toString());
        Contact contact = databaseHandler.getContact(id);

        etName.setText(contact.getName());
        etPhone.setText(contact.getPhoneNumber());

        isUpdateForm(true);
    }

    public void update(View view){

        Contact contact = new Contact();
        contact.setName(etName.getText().toString());
        contact.setPhoneNumber(etPhone.getText().toString());
        contact.setId( Integer.valueOf(etID.getText().toString()) );

        databaseHandler.update(contact);

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
