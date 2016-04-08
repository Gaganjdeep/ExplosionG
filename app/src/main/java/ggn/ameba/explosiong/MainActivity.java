package ggn.ameba.explosiong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import ggn.ameba.explosionglibrary.ExplosionField;

public class MainActivity extends AppCompatActivity
{
    private ExplosionField mExplosionField;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mExplosionField = ExplosionField.attach2Window(this);
        addListener(findViewById(R.id.root));
    }

    private void addListener(View root)
    {
        if (root instanceof ViewGroup)
        {
            ViewGroup parent = (ViewGroup) root;
            for (int i = 0; i < parent.getChildCount(); i++)
            {

                addListener(parent.getChildAt(i));
            }
        }
        else
        {
            root.setClickable(true);
            root.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (v.getId() != R.id.reset)
                    {
                        mExplosionField.explode(v);
                        v.setOnClickListener(null);
                    }
                }
            });
        }
    }


    public void reSet(View view)
    {
        Intent intnt=new Intent(MainActivity.this,MainActivity.class);
        startActivity(intnt);
        finish();
    }
}