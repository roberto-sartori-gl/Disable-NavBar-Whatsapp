package com.robertogl.nouiwhatsapp; 
    
import android.graphics.Color;
import de.robv.android.xposed.XSharedPreferences;

public class Preferences {
    // This current package.
    private static final String PACKAGE_NAME = "com.robertogl.nouiwhatsapp";

    // Our single instance of an XSharedPreferences.
    private static XSharedPreferences instance = null;
    private static XSharedPreferences getInstance() {
        if (instance == null) {
            instance = new XSharedPreferences(PACKAGE_NAME);
            instance.makeWorldReadable();
        } else {
            instance.reload();
        }
        
        return instance;
    }
    
    public static boolean hasRemoveCameraPreference() {
        return getInstance().getBoolean("whatsapp_remove_action_shortcuts_camera", true);
    }

    public static boolean hasRemoveVoicePreference() {
        return getInstance().getBoolean("whatsapp_remove_action_shortcuts_voice", true);
    }
    
    public static boolean hasRemoveWhatsAppBarPreference() {
        return getInstance().getBoolean("whatsapp_remove_bar", true);
    }
    
    public static boolean hasStickWhatsAppBarPreference() {
        return getInstance().getBoolean("whatsapp_stick_bar", true);
    }
    
    public static boolean hasRemoveCallQuick() {
        return getInstance().getBoolean("whatsapp_quick_call", true);
    }


}
	