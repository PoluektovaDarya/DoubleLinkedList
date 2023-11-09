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

        // Get the DoublyLinkedList object from the previous activity
        list = (DoublyLinkedList) getIntent().getSerializableExtra("doublyLinkedList");

        // Display the current state of the list
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
                String data = dataEditText.getText().toString();
                String number = numberEditText.getText().toString();
                list.insertBefore(data, number);
                displayList();
                dataEditText.setText("");
                numberEditText.setText("");
            }
        });
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = dataEditText.getText().toString();
                list.append(data);
                displayList();
                dataEditText.setText("");
                numberEditText.setText("");
            }
        });

        // Добавленный код для кнопки "Назад"
        Button backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Вызываем метод finish() для завершения текущей активности
                finish();
            }
        });


        insertAfterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = dataEditText.getText().toString();
                String number = numberEditText.getText().toString();
                list.insertAfter(data, number);
                displayList();
                numberEditText.setText("");
                dataEditText.setText("");
            }
        });

        deleteNodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = numberEditText.getText().toString().trim();

                if (!input.isEmpty()) {
                    String[] numbers = input.split("\\s+");

                    if (numbers.length == 3) {
                        try {
                            int prevNode = Integer.parseInt(numbers[0]);
                            int currentNode = Integer.parseInt(numbers[1]);
                            int nextNode = Integer.parseInt(numbers[2]);

                            list.deleteMiddleNode(String.valueOf(prevNode), String.valueOf(currentNode), String.valueOf(nextNode));
                            displayList();
                            numberEditText.setText("");
                            dataEditText.setText("");
                        } catch (NumberFormatException e) {
                            // Используем метод showToast из DoublyLinkedList
                            list.showToast("Неверный ввод. Введите действительные числа, разделенные пробелами.");
                        }
                    } else {
                        // Используем метод showToast из DoublyLinkedList
                        list.showToast("Пожалуйста, введите три числа, разделенные пробелами.");
                    }
                } else {
                    // Используем метод showToast из DoublyLinkedList
                    list.showToast("Пожалуйста, введите три числа, разделенные пробелами.");
                }
            }
        });



        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = dataEditText.getText().toString();
                list.append(data);
                displayList();
                dataEditText.setText("");
                numberEditText.setText("");
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

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
