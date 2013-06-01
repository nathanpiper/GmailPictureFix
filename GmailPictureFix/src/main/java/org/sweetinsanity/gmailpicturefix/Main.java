package org.sweetinsanity.gmailpicturefix;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class Main implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("com.google.android.gm"))
            return;
    	ClassLoader classLoader = lpparam.classLoader;
		
        XposedHelpers.findAndHookMethod(
			"com.google.android.gm.persistence.Persistence", //class
			classLoader,	//classLoader
			"getDisplayImagesFromSender",	//func
			android.content.Context.class, String.class, //param
			XC_MethodReplacement.returnConstant(Boolean.valueOf("true"))
        });
    }
}
