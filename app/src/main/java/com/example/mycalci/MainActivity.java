package com.example.mycalci;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView result,calc;
    MaterialButton button_AC,button_openbrac,button_closebrac,button_clear;
    MaterialButton button_zero,button_1,button_2,button_3,button_4,button_5,button_6,button_7,button_8,button_9;
    MaterialButton button_divide,button_mult,button_plus,button_minus;
    MaterialButton button_dot,button_equal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result=findViewById(R.id.result);
        calc=findViewById(R.id.calc);

        AssignId(button_AC,R.id.button_AC);
        AssignId(button_openbrac,R.id.button_openbrac);
        AssignId(button_closebrac,R.id.button_closebrac);
        AssignId(button_clear,R.id.button_clear);
        AssignId(button_divide,R.id.button_divide);
        AssignId(button_mult,R.id.button_mult);
        AssignId(button_minus,R.id.button_minus);
        AssignId(button_plus,R.id.button_plus);
        AssignId(button_equal,R.id.button_equal);
        AssignId(button_zero,R.id.button_zero);
        AssignId(button_1,R.id.button_1);
        AssignId(button_2,R.id.button_2);
        AssignId(button_3,R.id.button_3);
        AssignId(button_4,R.id.button_4);
        AssignId(button_5,R.id.button_5);
        AssignId(button_6,R.id.button_6);
        AssignId(button_7,R.id.button_7);
        AssignId(button_8,R.id.button_8);
        AssignId(button_9,R.id.button_9);
        AssignId(button_dot,R.id.button_dot);

    }

    void AssignId(MaterialButton btn, int id){
        MaterialButton b=btn;
        b=findViewById(id);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      MaterialButton button=(MaterialButton) view;
      String buttonText=button.getText().toString();
      String data=calc.getText().toString();
      if(buttonText.equals("AC")){
          calc.setText("");
          result.setText("0");
          return;
      }
      else if(buttonText.equals("=")){
          calc.setText(result.getText());
          result.setText("");
          return;
      }
      else if(buttonText.equals("C")){
          data=data.substring(0,data.length()-1);

      }
      else{
          data=data+buttonText;
      }

      calc.setText(data);

      String finalresult=getResult(data);

      if(!finalresult.equals("Err")){
          result.setText(finalresult);

      }

    }
    String getResult(String data){
        try{
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String Finalresult=context.evaluateString(scriptable,data, "Javascript",1,null).toString();
            if(Finalresult.endsWith(".0")){
                Finalresult=Finalresult.replace(".0","");
            }
            return Finalresult;
        }catch(Exception e){
            return "Err";
        }

    }
}