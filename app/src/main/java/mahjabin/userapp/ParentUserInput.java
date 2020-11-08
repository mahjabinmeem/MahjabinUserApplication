package mahjabin.userapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ParentUserInput extends AppCompatActivity {
    private EditText firstNameText;
    private EditText lastNameText;
    private EditText streetText;
    private EditText cityText;
    private EditText stateText;
    private EditText zipText;
    private String mFirstName;
    private String mLastName;
    private String mStreet;
    private String mCity;
    private String mState;
    private String mZip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_user_input);

        Button addParentUserButton = findViewById(R.id.addUser);
        addParentUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast toast;

                firstNameText = findViewById(R.id.firstName);
                mFirstName = firstNameText.getText().toString().trim();
                lastNameText = findViewById(R.id.lastName);
                mLastName = lastNameText.getText().toString().trim();
                streetText = findViewById(R.id.street);
                mStreet = streetText.getText().toString().trim();
                cityText = findViewById(R.id.city);
                mCity = cityText.getText().toString().trim();
                stateText = findViewById(R.id.state);
                mState = stateText.getText().toString().trim();
                zipText = findViewById(R.id.zip);
                mZip = zipText.getText().toString().trim();

                Parent parentUser;
                if (!mFirstName.isEmpty() && !mLastName.isEmpty() && !mStreet.isEmpty() &&
                        !mCity.isEmpty() && !mState.isEmpty() && !mZip.isEmpty()) {
                    parentUser = new Parent();
                    parentUser.setmFirstName(mFirstName);
                    parentUser.setmLastName(mLastName);
                    parentUser.setmStreet(mStreet);
                    parentUser.setmCity(mCity);
                    parentUser.setmState(mState);
                    parentUser.setmZip(mZip);

                    DataBaseHelper dataBaseHelper = new DataBaseHelper(ParentUserInput.this);
                    boolean success = dataBaseHelper.addNewUser(parentUser);
                    if (success) {
                        toast = Toast.makeText(context, "Successfully Added", duration);
                        toast.show();
                    } else {
                        toast = Toast.makeText(context, "Failed Attempt", duration);
                        toast.show();
                    }

                } else {

                    toast = Toast.makeText(context, "Please enter valid inputs", duration);
                    toast.show();
                }

            }
        });

        Button viewAllUser = findViewById(R.id.viewAllUser);
        viewAllUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUserToList();
            }
        });

        Button updateParentUser = findViewById(R.id.updateParentUser);
        updateParentUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateParentUser();
            }
        });
    }

    private void updateParentUser() {
        Intent intent = new Intent(this, UpdateParentUser.class);
        startActivity(intent);
    }

    public void addUserToList() {
        Intent intent = new Intent(this, UserList.class);
        startActivity(intent);
    }
}
