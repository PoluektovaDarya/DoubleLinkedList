package com.example.kusrovoi;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class ListOperationsActivity extends AppCompatActivity {
    private TextView listTextView;
    private EditText dataEditText;
    private Button sortButton;
    private Button addItemButton;
    private Button deleteNodeButton;
    private DoublyLinkedList list;
    private EditText numberEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_operations);
        listTextView = findViewById(R.id.listTextView);
        dataEditText = findViewById(R.id.dataEditText);
        sortButton = findViewById(R.id.sortButton);
        addItemButton = findViewById(R.id.addItemButton);
        deleteNodeButton = findViewById(R.id.deleteNodeButton);
        Button insertBeforeButton = findViewById(R.id.addNodeStartButton);
        Button insertAfterButton = findViewById(R.id.addNodeEndButton);
        numberEditText = findViewById(R.id.numberEditText);
        list = (DoublyLinkedList) getIntent().getSerializableExtra("doublyLinkedList");
        displayList();
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.sort();
                displayList();
            }
        });
        insertBeforeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputIndex = numberEditText.getText().toString().trim();
                String inpudData = dataEditText.getText().toString().trim();
                if (inputIndex.isEmpty() && inpudData.isEmpty()){
                    Toast.makeText(ListOperationsActivity.this, "Указатель или число не обнаружены", Toast.LENGTH_SHORT).show();
                } else {
                String data = dataEditText.getText().toString();
                String number = numberEditText.getText().toString();
                list.insertBefore(data, number);
                displayList();
                dataEditText.setText("");
                numberEditText.setText("");
            }}
        });
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputData = dataEditText.getText().toString().trim();
                if (!inputData.isEmpty()) {
                    String cleanData = inputData.replaceAll("\\s+", "");
                    list.append("" + cleanData);
                    displayList();
                    dataEditText.setText("");
                    numberEditText.setText("");
                } else {
                    Toast.makeText(ListOperationsActivity.this, "Число не обнаружено", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        insertAfterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputIndex = numberEditText.getText().toString().trim();
                String inpudData = dataEditText.getText().toString().trim();
                if (inputIndex.isEmpty() && inpudData.isEmpty()){
                    Toast.makeText(ListOperationsActivity.this, "Указатель или число не обнаружены", Toast.LENGTH_SHORT).show();
                } else {
                String data = dataEditText.getText().toString();
                String number = numberEditText.getText().toString();
                list.insertAfter(data, number);
                displayList();
                numberEditText.setText("");
                dataEditText.setText("");
            }}
        });
        deleteNodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = numberEditText.getText().toString().trim();

                if (!input.isEmpty()) {
                    list.deleteNode(input);
                    displayList();
                    numberEditText.setText("");
                    dataEditText.setText("");
                } else {
                    Toast.makeText(ListOperationsActivity.this, "Число не обнаружено", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void displayList() {
        StringBuilder stringBuilder = new StringBuilder();
        Node current = list.head;
        while (current != null) {
            stringBuilder.append(current.data).append(" ");
            current = current.next;
        }
        listTextView.setText(stringBuilder.toString());
    }
}
