package com.robertogl.nouiwhatsapp; 
 
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

import android.content.res.XModuleResources;
import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class Main implements IXposedHookZygoteInit, IXposedHookInitPackageResources,  IXposedHookLoadPackage {
    private static String MODULE_PATH = null;
    
    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {
        MODULE_PATH = startupParam.modulePath;
    }
    
    @Override
    public void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable {
        if (!resparam.packageName.equals("com.whatsapp"))
            return;

        XModuleResources modRes = XModuleResources.createInstance(MODULE_PATH, resparam.res);
        resparam.res.setReplacement("com.whatsapp", "dimen", "tab_height", modRes.fwd(R.dimen.tab_height));
    }
    @Override
    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("com.whatsapp"))
        	return;
        findAndHookMethod("com.whatsapp.HomeActivity", lpparam.classLoader, "a", int.class, new XC_MethodReplacement() {
        	@Override
        	protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                return null;
            }
                });
        
        
    }
	}