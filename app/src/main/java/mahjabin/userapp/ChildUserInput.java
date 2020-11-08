package mahjabin.userapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChildUserInput extends AppCompatActivity {
    private EditText childFirstNameText;
    private EditText childLastNameText;
    private EditText parentFirstNameText;
    private EditText parentLastNameText;
    private String childFirstName;
    private String childLastName;
    private String parentFirstName;
    private String parentLastName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_user_input);

        Button addChildUserButton = findViewById(R.id.addChildUser);
        addChildUserButton.setOnClickListener(new View.OnClickListener() {

            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast;

            @Override
            public void onClick(View view) {
                childFirstNameText = findViewById(R.id.childFirstName);
                childFirstName = childFirstNameText.getText().toString().trim();
                childLastNameText = findViewById(R.id.childLastName);
                childLastName = childLastNameText.getText().toString().trim();
                parentFirstNameText = findViewById(R.id.parentFirstName);
                parentFirstName = parentFirstNameText.getText().toString().trim();
                parentLastNameText = findViewById(R.id.parentLastName);
                parentLastName = parentLastNameText.getText().toString().trim();


                if (!childFirstName.isEmpty() && !childLastName.isEmpty() && !parentFirstName.isEmpty() &&
                        !parentLastName.isEmpty()) {
                    Child childUser = new Child();
                    childUser.setChildFirstName(childFirstName);
                    childUser.setChildLastName(childLastName);
                    childUser.setParentFirstName(parentFirstName);
                    childUser.setParentLastName(parentLastName);

                    DataBaseHelper dataBaseHelper = new DataBaseHelper(ChildUserInput.this);
                    boolean success = dataBaseHelper.addNewChildUser(childUser);
                    if (success) {
                        toast = Toast.makeText(context, "Success", duration);
                        toast.show();
                    } else {
                        toast = Toast.makeText(context, "Parent Doesn't Exist", duration);
                        toast.show();
                    }

                } else {

                    toast = Toast.makeText(context, "Please enter valid inputs", duration);
                    toast.show();
                }


            }
        });

        Button viewAllChildUser = findViewById(R.id.viewAllChildUser);
        viewAllChildUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUserToList();
            }
        });

        Button updateChildUser = findViewById(R.id.updateChildUser);
        updateChildUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateChildUser();
            }
        });
    }

    private void updateChildUser() {
        Intent intent = new Intent(this, UpdateChildUser.class);
        startActivity(intent);
    }

    public void addUserToList() {
        Intent intent = new Intent(this, ChildUserList.class);
        startActivity(intent);
    }

}
