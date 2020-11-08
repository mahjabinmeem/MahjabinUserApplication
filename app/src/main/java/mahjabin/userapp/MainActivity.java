package mahjabin.userapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.parentButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addParentUserInput();
            }
        });
        Button button2 = findViewById(R.id.childButton);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addChildUserInput();
            }
        });
    }

    public void addParentUserInput() {
        Intent intent1 = new Intent(this, ParentUserInput.class);
        startActivity(intent1);
    }

    public void addChildUserInput() {
        Intent intent2 = new Intent(this, ChildUserInput.class);
        startActivity(intent2);
    }
}
