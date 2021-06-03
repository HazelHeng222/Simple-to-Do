package sg.edu.ep.c346.id20029318.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edToDo;
    Button butAdd, butClear, butDel;
    ListView listToDo;
    Spinner spnTask;
    TextView txt;

    ArrayList alToDo; //declaring
    ArrayAdapter aaToDo; //declaring

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edToDo = findViewById(R.id.editNewToDo);
        butAdd = findViewById(R.id.butAdd);
        butClear = findViewById(R.id.butClear);
        butDel = findViewById(R.id.butDel);
        listToDo = findViewById(R.id.listToDo);
        spnTask = findViewById(R.id.spinner);
        txt = findViewById(R.id.textView); // another way to show message?

        alToDo = new ArrayList<String>(); //initialise arraylist
        aaToDo = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,alToDo);
        listToDo.setAdapter(aaToDo);


        butAdd.setEnabled(true);
        butDel.setEnabled(false);


        spnTask.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: //add tasks
                        butAdd.setEnabled(true);
                        butDel.setEnabled(false);
                        edToDo.setHint(getString(R.string.hintAdd));
                        break;
                    case 1: //delete tasks
                        butAdd.setEnabled(false);
                        butDel.setEnabled(true);
                        edToDo.setHint(getString(R.string.hintDel));
                        if (alToDo.isEmpty()) { // no items in arraylist
                            Toast.makeText(MainActivity.this,getString(R.string.txtNoTasks), Toast.LENGTH_SHORT).show(); // due to the keyboard, cannot see toast
                            txt.setText(getString(R.string.txtNoTasks)); // so test via set txt instead
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        butAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = edToDo.getText().toString();
                alToDo.add(task);
                aaToDo.notifyDataSetChanged();
            }
        });


        butClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i <= alToDo.size(); i ++ ) {
                    alToDo.clear(); // remove all items in arraylist
                    aaToDo.notifyDataSetChanged();
                }
            }
        });

        butDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int indx = Integer.parseInt(edToDo.getText().toString());
                if (edToDo.equals(null)) { // no index keyed in
                    txt.setText("Please enter valid index");
                }
                else {
                    if (indx > -1 && indx < alToDo.size()) { // if index is between 0 and final position of arraylist
                        alToDo.remove(indx);
                        aaToDo.notifyDataSetChanged();
                    }
                    else {
                        Toast.makeText(MainActivity.this,getString(R.string.txtWrongNum), Toast.LENGTH_SHORT).show();
                        txt.setText(getString(R.string.txtWrongNum));
                    }
                }
            }
        });

    }
}