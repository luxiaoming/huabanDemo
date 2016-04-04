package licola.demo.com.huabandemo.Module;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.Bind;
import licola.demo.com.huabandemo.R;
import licola.demo.com.huabandemo.Util.Logger;
import licola.demo.com.huabandemo.Base.BaseActivity;
import licola.demo.com.huabandemo.ImageDetail.ImageDetailActivity;
import licola.demo.com.huabandemo.bean.PinsAndUserEntity;

/**
 * Created by LiCola on  2016/03/20  12:00
 * 负责显示各个模块
 * 从Search模块跳转
 * 显示用Fragment展示UI
 */
public class ModuleActivity extends BaseActivity implements ModuleFragment.OnModuleFragmentInteractionListener {

    protected static final String TYPE_KEY = "TYPE_KEY";
    protected static final String TYPE_TITLE = "TYPE_TITLE";

    protected String mType;
    protected String mTitle;

    @Bind(R.id.fab_module)
    FloatingActionButton mFABModule;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_module;
    }

    @Override
    protected String getTAG() {
        return this.toString();
    }

    public static void launch(Activity activity,String title,String type) {
        Intent intent = new Intent(activity, ModuleActivity.class);
        intent.putExtra(TYPE_TITLE,title);
        intent.putExtra(TYPE_KEY,type);
        activity.startActivity(intent);
    }

    public static void launch(Activity activity,int flag) {
        Intent intent = new Intent(activity, ModuleActivity.class);
        intent.setFlags(flag);
        activity.startActivity(intent);
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, ModuleActivity.class);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_module);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTitle=getIntent().getStringExtra(TYPE_TITLE);
        mType=getIntent().getStringExtra(TYPE_KEY);
        setTitle(mTitle);

//        getFragmentManager().beginTransaction().replace(R.id.framelayout_module, ModuleFragment.newInstance(type,title)).commit();
        getSupportFragmentManager().
                beginTransaction().replace(R.id.framelayout_module,ModuleFragment.newInstance(mType,mTitle)).commit();

        mFABModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Logger.d(intent.toString());
    }

    @Override
    public void onClickItemImage(PinsAndUserEntity bean, View view) {
        ImageDetailActivity.launch(this,ImageDetailActivity.ACTION_MODULE);
    }

    @Override
    public void onClickItemText(PinsAndUserEntity bean, View view) {
        ImageDetailActivity.launch(this,ImageDetailActivity.ACTION_MODULE);
    }
}
