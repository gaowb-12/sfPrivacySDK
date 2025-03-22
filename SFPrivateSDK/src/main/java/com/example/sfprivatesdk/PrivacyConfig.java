package com.example.sfprivatesdk;

public class PrivacyConfig extends PrivacyFormConfig{
    String version;
    public PrivacyConfig( String userId, String code, String version){
        super(userId, code);
        this.version = version;
    }
}
