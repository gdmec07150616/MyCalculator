package cn.gdmec.lyc07150616.mycalculator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button calculatorButton;
    private EditText weightEditText;
    private RadioButton manweightBox;
    private RadioButton womanweightBox;
    private TextView resultTextView;
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculatorButton=(Button)findViewById(R.id.calculator);
        weightEditText=(EditText)findViewById(R.id.wegiht);
        manweightBox=(RadioButton)findViewById(R.id.man);
        womanweightBox=(RadioButton)findViewById(R.id.woman);
        resultTextView=(TextView)findViewById(R.id.result);
        registerEvent();
    }

    private  void registerEvent(){
        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!weightEditText.getText().toString().trim().equals("")){
                    if (manweightBox.isChecked()||womanweightBox.isChecked()){
                        Double weight=Double.parseDouble(weightEditText.getText().toString());
                        StringBuffer sb=new StringBuffer();
                        sb.append("--------------------------------评测结果------------------\n");
                        if (manweightBox.isChecked()){
                            sb.append("男的标准身高：");
                            Double result=evaluateHeight(weight,"男");
                            sb.append(result+"（厘米）");
                        }if (womanweightBox.isChecked()){
                            sb.append("女的标准身高：");
                            Double result=evaluateHeight(weight,"女");
                            sb.append((result+"（厘米）"));
                        }
                        resultTextView.setText(sb.toString());
                    }else {
                        showMessage("请选择性别！");
                    }
                }else {
                    showMessage("请输入体重！");
                }

            }
        });
    }
    private double evaluateHeight(double weight,String sex){
        double height;
        if (sex=="男"){
            height=170-(62-weight)/0.6;
        }else{
            height=158-(52-weight)/0.5;
        }
        return height;
    }
    private void showMessage(String message){
        AlertDialog alert=new AlertDialog.Builder(this).create();
        alert.setTitle("系统信息");
        alert.setMessage(message);
        alert.setButton(alert.BUTTON_NEGATIVE,"确定",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alert.show();
    }
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(Menu.NONE,1,Menu.NONE,"退出");
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case 1:finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
