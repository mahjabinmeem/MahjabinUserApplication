package mahjabin.userapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class UserList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        ListView listView1 = findViewById(R.id.userList);

        final DataBaseHelper dataBaseHelper1 = new DataBaseHelper(UserList.this);
        List<Parent> allUser = dataBaseHelper1.getAllUser();

        ArrayAdapter<Parent> parentUserAdapter = new ArrayAdapter<>(UserList.this, android.R.layout.simple_list_item_1, allUser);
        listView1.setAdapter(parentUserAdapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Parent clickedUser = (Parent) adapterView.getItemAtPosition(i);

                DataBaseHelper dataBaseHelper = new DataBaseHelper(UserList.this);
                dataBaseHelper.deleteUser(clickedUser);

                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast toast;
                toast = Toast.makeText(context, "Deleted.", duration);
                toast.show();
            }
        });

    }
}
