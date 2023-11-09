package com.example.kusrovoi;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        createButton = findViewById(R.id.createButton);

        // Устанавливаем фильтр для ввода
        editText.setFilters(new InputFilter[]{new SpaceFilter()});

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editText.getText().toString().trim();

                if (input.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Список пуст", Toast.LENGTH_SHORT).show();
                    return;
                }

                DoublyLinkedList list = new DoublyLinkedList();

                // Используем сплит, чтобы разделить числа
                String[] elements = input.split("\\s+");
                for (String element : elements) {
                    list.append(element);
                }

                Intent intent = new Intent(MainActivity.this, ListOperationsActivity.class);
                intent.putExtra("doublyLinkedList", list);
                startActivity(intent);
            }
        });
    }

    // Фильтр для ввода, который разрешает только один пробел после числа
    public class SpaceFilter implements InputFilter {
        @Override
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            // Удаляем все лишние пробелы
            String filteredSource = source.toString().replaceAll("\\s+", " ");

            if (dest.length() > 0 && filteredSource.equals(" ") && dest.charAt(dest.length() - 1) >= '0' && dest.charAt(dest.length() - 1) <= '9') {
                // Если введен пробел после числа, заменяем его на один пробел
                return " ";
            }

            return filteredSource;
        }
    }
}
