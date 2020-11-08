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

public class ChildUserList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_user_list);

        ListView listView = findViewById(R.id.childUserList);

        final DataBaseHelper dataBaseHelper1 = new DataBaseHelper(ChildUserList.this);
        List<Child> allChildUser = dataBaseHelper1.getAllChildUser();

        ArrayAdapter childUserAdapter = new ArrayAdapter<>(ChildUserList.this, android.R.layout.simple_list_item_1, allChildUser);
        listView.setAdapter(childUserAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Child clickedUser = (Child) adapterView.getItemAtPosition(i);

                DataBaseHelper dataBaseHelper = new DataBaseHelper(ChildUserList.this);
                dataBaseHelper.deleteChildUser(clickedUser);

                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast toast;
                toast = Toast.makeText(context, "Deleted", duration);
                toast.show();
            }
        });

    }
}
