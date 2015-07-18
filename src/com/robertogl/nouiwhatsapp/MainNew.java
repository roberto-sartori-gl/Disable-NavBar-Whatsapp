package com.robertogl.nouiwhatsapp; 
    
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

import android.content.res.XModuleResources;
import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodReplacement;
import android.content.res.XResources;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import android.widget.ImageButton;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.graphics.PorterDuff.Mode;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;

    public class MainNew implements IXposedHookZygoteInit, IXposedHookLoadPackage, IXposedHookInitPackageResources {
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
           
            if (Preferences.hasRemoveVoicePreference()){
            	
            resparam.res.setReplacement("com.whatsapp", "drawable", "input_mic_white_large", new XResources.DrawableLoader() {
    		    @Override
    		    public Drawable newDrawable(XResources res, int id) throws Throwable {	   	    	
    		    	
    		    	Drawable draw = (Drawable) res.getDrawable(R.drawable.e10c);
    		    	draw.setColorFilter(Color.TRANSPARENT, Mode.MULTIPLY);
    		        return draw;
    		    }
    	 
            });
            resparam.res.setReplacement("com.whatsapp", "drawable", "input_mic_white", new XResources.DrawableLoader() {
    		    @Override
    		    public Drawable newDrawable(XResources res, int id) throws Throwable {	   	    	
    		    	
    		    	Drawable draw = (Drawable) res.getDrawable(R.drawable.e10c);
    		    	draw.setColorFilter(Color.TRANSPARENT, Mode.MULTIPLY);
    		        return draw;
    		    }
    	 
            });
            }
            if (Preferences.hasRemoveCameraPreference()){
            resparam.res.setReplacement("com.whatsapp", "drawable", "input_cam", new XResources.DrawableLoader() {
    		    @Override
    		    public Drawable newDrawable(XResources res, int id) throws Throwable {	   	    	
    		    	
    		    	Drawable draw = (Drawable) res.getDrawable(R.drawable.e10c);
    		    	draw.setColorFilter(Color.TRANSPARENT, Mode.MULTIPLY);
    		        return draw;
    		    }
    	 
            });
            resparam.res.setReplacement("com.whatsapp", "drawable", "msg_status_cam", new XResources.DrawableLoader() {
    		    @Override
    		    public Drawable newDrawable(XResources res, int id) throws Throwable {	   	    	
    		    	
    		    	Drawable draw = (Drawable) res.getDrawable(R.drawable.e10c);
    		    	draw.setColorFilter(Color.TRANSPARENT, Mode.MULTIPLY);
    		        return draw;
    		    }
    	 
            });
            }
            
            if (Preferences.hasRemoveWhatsAppBarPreference()){
            resparam.res.setReplacement("com.whatsapp", "dimen", "tab_height", modRes.fwd(R.dimen.tab_height));
            }
            
            
            resparam.res.hookLayout("com.whatsapp", "layout", "conversation", new XC_LayoutInflated() {
                @Override
                public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                	if (Preferences.hasRemoveVoicePreference()){
                	ImageButton voiceButton = (ImageButton) liparam.view.findViewById(
                            liparam.res.getIdentifier("voice_note_btn", "id", "com.whatsapp"));
                	FrameLayout.LayoutParams frameLayoutParams = new FrameLayout.LayoutParams(0, 0);
                    voiceButton.setLayoutParams(frameLayoutParams);
                			}
                	if (Preferences.hasRemoveCameraPreference()){
                    ImageButton cameraButton = (ImageButton) liparam.view.findViewById(
                            liparam.res.getIdentifier("camera_btn", "id", "com.whatsapp"));
                    LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(0, 0);
                    cameraButton.setLayoutParams(linearLayoutParams);
                	}
                }
            });
        }
        @Override
        public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
            if (!lpparam.packageName.equals("com.whatsapp"))
            	return;
            if (Preferences.hasStickWhatsAppBarPreference()){
            findAndHookMethod("com.whatsapp.HomeActivity", lpparam.classLoader, "a", int.class, new XC_MethodReplacement() {
            	@Override
            	protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                    return null;
                }
                    });
            }
            
        }
    	}
	