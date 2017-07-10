package org.iptime.proncan.lockscreen;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity implements View.OnClickListener {

    ArrayList<String> todoList;
    ArrayAdapter<String> mAdapter;
    ListView todoListView;

    Button tg, kt, fb, tw, hs, sj, photo, add_todoList;
    EditText editText;

    void dialogView(int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("삭제");
        builder.setMessage("삭제하시겠습니까?");
        builder.setCancelable(true);        // 뒤로 버튼 클릭시 취소 가능 설정
        builder.setPositiveButton("O", new DialogInterface.OnClickListener() {
            int count = mAdapter.getCount();
            int checked;

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (count > 0) {
                    checked = todoListView.getCheckedItemPosition();
                    if (checked > -1 && checked < count) {
                        todoList.remove(checked);
                        todoListView.clearChoices();
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
        builder.setNegativeButton("X", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            dialogView(position);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_todoList:
                Log.d(TAG, "click add_todoList");
                todoList.add(editText.getText().toString());
                mAdapter.notifyDataSetChanged(); // 갱신
                break;
            case R.id.tg:

                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoList = new ArrayList<String>();
        mAdapter = new ArrayAdapter<String> (getApplicationContext(), R.layout.todolist_detail, R.id.todoList_text, todoList);

        tg = (Button) findViewById(R.id.tg);
        kt = (Button) findViewById(R.id.kt);
        fb = (Button) findViewById(R.id.fb);
        tw = (Button) findViewById(R.id.tw);
        hs = (Button) findViewById(R.id.hs);
        sj = (Button) findViewById(R.id.sj);
        photo = (Button) findViewById(R.id.photo);
        add_todoList = (Button) findViewById(R.id.add_todoList);
        editText = (EditText) findViewById(R.id.editText);
        todoListView = (ListView) findViewById(R.id.todoList);

        todoList.add("testdata");
        mAdapter.notifyDataSetChanged(); // 갱신

        add_todoList.setOnClickListener(this);
        mAdapter.notifyDataSetChanged();

        todoListView.setAdapter(mAdapter);
        todoListView.setOnItemClickListener(onItemClickListener);

    }
}
