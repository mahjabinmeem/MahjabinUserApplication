package mahjabin.userapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateChildUser extends AppCompatActivity {

    private EditText updateChildFirstNameText;
    private EditText updateChildLastNameText;
    private EditText updateParentFirstNameText;
    private EditText updateParentLastNameText;
    private String updateChildFirstName;
    private String updateChildLastName;
    private String updateParentFirstName;
    private String updateParentLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_child_user);

        Button updateChildUserBtn = findViewById(R.id.updateChildUserBtn);
        updateChildUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast toast;

                updateChildFirstNameText = findViewById(R.id.updateChildFirstName);
                updateChildFirstName = updateChildFirstNameText.getText().toString().trim();
                updateChildLastNameText = findViewById(R.id.updateChildLastName);
                updateChildLastName = updateChildLastNameText.getText().toString().trim();
                updateParentFirstNameText = findViewById(R.id.updateParentFirstName);
                updateParentFirstName = updateParentFirstNameText.getText().toString().trim();
                updateParentLastNameText = findViewById(R.id.updateParentLastName);
                updateParentLastName = updateParentLastNameText.getText().toString().trim();

                if (!updateChildFirstName.isEmpty() && !updateChildLastName.isEmpty() && !updateParentFirstName.isEmpty() &&
                        !updateParentLastName.isEmpty()) {

                    DataBaseHelper dataBaseHelper = new DataBaseHelper(UpdateChildUser.this);
                    boolean success = dataBaseHelper.updateChildUser(updateChildFirstName, updateChildLastName);
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
