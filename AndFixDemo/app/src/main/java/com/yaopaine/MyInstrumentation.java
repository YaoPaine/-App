package com.yaopaine;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/9/18
 */
public class MyInstrumentation extends Instrumentation {

    public MyInstrumentation() {
        super();
    }

    private String TAG = "MyInstrumentation";
    private Instrumentation mBeforeReplace;
    private Context context;

    public MyInstrumentation(Instrumentation instrumentation, Context context) {
        this.mBeforeReplace = instrumentation;
        this.context = context;
    }

    public ActivityResult execStartActivity(
            Context who, IBinder contextThread, IBinder token, Activity target,
            Intent intent, int requestCode, Bundle options) {
        ComponentName componentName = target.getComponentName();
        Log.e(TAG, "execStartActivity: " + componentName.getClassName());
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            for (String key : bundle.keySet()) {
                Log.e(TAG, "execStartActivity:key " + key + "\tvalue: " + bundle.get(key));
            }
        }
        Log.e(TAG, "execStartActivity: " + "\n执行了startActivity, 参数如下: \n" + "who = [" + who + "], " +
                "\ncontextThread = [" + contextThread + "], \ntoken = [" + token + "], " +
                "\ntarget = [" + target + "], \nintent = [" + intent +
                "], \nrequestCode = [" + requestCode + "], \noptions = [" + options + "]");
        try {
            Method method = Instrumentation.class.getDeclaredMethod("execStartActivity",
                    Context.class, IBinder.class, IBinder.class, Activity.class, Intent.class, int.class, Bundle.class);
            method.setAccessible(true);
            return (ActivityResult) method.invoke(mBeforeReplace, who, contextThread, token, target, intent, requestCode, options);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "execStartActivity: hook失败了");
        return null;
    }


}
