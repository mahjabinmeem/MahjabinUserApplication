package mahjabin.userapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateParentUser extends AppCompatActivity {

    private EditText updateFirstNameText;
    private EditText updateLastNameText;
    private EditText updateStreetText;
    private EditText updateCityText;
    private EditText updateStateText;
    private EditText updateZipText;
    private String updateFirstName;
    private String updateLastName;
    private String updateStreet;
    private String updateCity;
    private String updateState;
    private String updateZip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_parent_user);

        Button updateParentUserBtn = findViewById(R.id.updateParentBtn);
        updateParentUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast toast;

                updateFirstNameText = findViewById(R.id.updateParentFirstName);
                updateFirstName = updateFirstNameText.getText().toString().trim();
                updateLastNameText = findViewById(R.id.updateParentLastName);
                updateLastName = updateLastNameText.getText().toString().trim();
                updateStreetText = findViewById(R.id.updateStreet);
                updateStreet = updateStreetText.getText().toString().trim();
                updateCityText = findViewById(R.id.updateCity);
                updateCity = updateCityText.getText().toString().trim();
                updateStateText = findViewById(R.id.updateState);
                updateState = updateStateText.getText().toString().trim();
                updateZipText = findViewById(R.id.updateZip);
                updateZip = updateZipText.getText().toString().trim();

                if (!updateFirstName.isEmpty() && !updateLastName.isEmpty() && !updateStreet.isEmpty() &&
                        !updateCity.isEmpty() && !updateState.isEmpty() && !updateZip.isEmpty()) {

                    DataBaseHelper dataBaseHelper = new DataBaseHelper(UpdateParentUser.this);
                    boolean success = dataBaseHelper.updateParentUser(updateFirstName, updateLastName, updateStreet, updateCity, updateState, updateZip);
                    if (success) {
                        toast = Toast.makeText(context, "Success", duration);
                        toast.show();
                    } else {
                        toast = Toast.makeText(context, "Failed", duration);
                        toast.show();
                    }

                } else {

                    toast = Toast.makeText(context, "Please enter valid inputs", duration);
                    toast.show();
                }


            }
        });

    }
}


