package com.example.yan.calculator;

import android.os.StrictMode;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private TextView textView;
    private Button button1,button2,button3,button4,button5,button6,button7,button8,button9,button0;
    private Button add,cut,rid,divide;//加减乘除
    private Button result,point,clear;
    int pointCount=0;
    int option=0;//运算符状态
    boolean newdigital=true;//标记是否是新输入的数字
    boolean flag=true;
    double a=0.,b=0.;
    double sum=0.0;
    double sumtype=0;//判断输出的数是否有小数点
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.textView);
        button0=(Button)findViewById(R.id.btn0);
        button1=(Button)findViewById(R.id.btn1);
        button2=(Button)findViewById(R.id.btn2);
        button3=(Button)findViewById(R.id.btn3);
        button4=(Button)findViewById(R.id.btn4);
        button5=(Button)findViewById(R.id.btn5);
        button6=(Button)findViewById(R.id.btn6);
        button7=(Button)findViewById(R.id.btn7);
        button8=(Button)findViewById(R.id.btn8);
        button9=(Button)findViewById(R.id.btn9);
        add=(Button)findViewById(R.id.add);
        clear=(Button)findViewById(R.id.clear);
        cut=(Button)findViewById(R.id.cut);
        divide=(Button)findViewById(R.id.divide);
        rid=(Button)findViewById(R.id.rid);
        result=(Button)findViewById(R.id.btnresult);
        point=(Button)findViewById(R.id.btnpoint);

        button0.setOnClickListener(lisenter);
        button1.setOnClickListener(lisenter);
        button2.setOnClickListener(lisenter);
        button3.setOnClickListener(lisenter);
        button4.setOnClickListener(lisenter);
        button5.setOnClickListener(lisenter);
        button6.setOnClickListener(lisenter);
        button7.setOnClickListener(lisenter);
        button8.setOnClickListener(lisenter);
        button9.setOnClickListener(lisenter);
        button0.setOnClickListener(lisenter);
        add.setOnClickListener(lisenter);
        cut.setOnClickListener(lisenter);
        rid.setOnClickListener(lisenter);
        divide.setOnClickListener(lisenter);
        result.setOnClickListener(lisenter);
        point.setOnClickListener(lisenter);
        clear.setOnClickListener(lisenter);
    }
        View.OnClickListener lisenter=new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String s=textView.getText().toString();//获取文本框的内容
                Button btn=(Button)v;
                String t=(String)btn.getText();//获取按钮的字符
                //数字输入部分
                if(btn.getId()==R.id.btn0||btn.getId()==R.id.btn1||btn.getId()==R.id.btn2||btn.getId()==R.id.btn3
                        ||btn.getId()==R.id.btn4||btn.getId()==R.id.btn5||btn.getId()==R.id.btn6||
                        btn.getId()==R.id.btn7||btn.getId()==R.id.btn8||btn.getId()==R.id.btn9||(btn.getId()==R.id.btnpoint&&pointCount==0))
                {
                    if(btn.getId()==R.id.btnpoint)
                    {
                        if (s == null || s.equals(""))
                        {
                            s += "0" + btn.getText();
                        } else
                        {
                            s += btn.getText();
                        }
                        pointCount=1;//记录是否有小数点
                    }
                    else
                    {
                        s+=btn.getText();
                    }
                    textView.setText(s);
                }
                else if (btn.getId() == R.id.add || btn.getId() == R.id.divide || btn.getId() == R.id.cut || btn.getId() == R.id.rid)
                {
                if(s==null||s.equals(""))
                {
                    s="0";
                }
                if(option!=0)
                {
                    b=Double.valueOf(s);
                    switch(option)
                    {
                        case 1:
                            sum = a + b;
                            break;
                        case 2:
                            sum = a - b;
                            break;
                        case 3:
                            sum = a * b;
                            break;
                        case 4:
                            if (b == 0)
                            {
                                System.out.println("除数不能为0！");
                                textView.setText("");
                                break;
                            }
                            sum = a / b;
                            break;
                        default:
                            break;
                    }
                    a=sum;
                }
                if(option==0)
                {
                    a=Double.valueOf(s);
                }
                switch (btn.getId())
                {
                    case R.id.add:
                        option=1;
                        break;
                    case R.id.cut:
                        option=2;
                        break;
                    case R.id.rid:
                        option=3;
                        break;
                    case R.id.divide:
                        option=4;
                        break;
                    default:
                        break;
                }
                textView.setText("");
                }
                else
                {
                  if(btn.getId()==R.id.btnresult)
                  {
                      if(s==null||s.equals(""))
                      {
                          s="0";
                      }
                      b=Double.valueOf(s);
                      switch (option)
                      {
                          case 1:
                              sum=a+b;
                              break;
                          case 2:
                              sum=a-b;
                              break;
                          case 3:
                              sum=a*b;
                              break;
                          case 4:
                              if (b == 0)
                              {
                                  System.out.println("除数不能为0！");
                                  textView.setText("");
                                  break;
                              }
                              sum = a / b;
                              break;
                          default:
                              break;
                      }
                      sumtype=sum%1;//判断是否是小数
                      if(sumtype>0)
                      {
                          pointCount=1;
                      }
                      s=""+sum;//转换为字符串
                      if(sumtype==0)
                      {
                          int end=(s.toString()).lastIndexOf(".");
                          String str=(s.toString()).substring(0,end);
                          s=""+Integer.parseInt(str);
                          pointCount=0;
                      }
                      if(flag)
                      {
                          textView.setText(s);
                      }
                      a=Double.valueOf(s);
                      option=0;
                      flag=true;
                  }
                  else
                  {
                      //clear按钮
                      if(btn.getId()==R.id.clear)
                      {
                          textView.setText("");
                          pointCount=0;
                          option=0;
                          flag=true;
                      }
                  }
                }
            }
        };
}
