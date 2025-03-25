## 集成数风科技安卓SDK

### 一、引入插件

#### 1. 配置项目级仓库
在项目 settings.gradle 或 build.gradle 添加：
```gradle
dependencyResolutionManagement {
    ...
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```
#### 2. 添加模块依赖
在模块的build.gradle中添加依赖
```gradle
dependencies {
    implementation 'com.github.gaowb-12:sfPrivacySDK:v0.0.1'
}
```


### 二、离线包集成

#### 1. 下载AAR文件
将远程AAR文件（如core-release.aar）放置于app/libs目录。
#### 2. 配置本地仓库
在模块的build.gradle中指定本地路径：
```gradle
   dependencies {
       implementation files('libs/core-release.aar')
   }
```
注意：此方式可能导致依赖丢失，需手动添加AAR的所有第三方依赖。


### 三、用法
```java
import com.example.sfprivatesdk.PrivacySDK;
import com.example.sfprivatesdk.PrivacyConfig;
import com.example.sfprivatesdk.PrivacyFormConfig;

// 初始化隐私政策配置
PrivacyConfig config = new PrivacyConfig(userId, code, version);
// 初始化隐私政策
PrivacySDK.initPrivacy(this,config);

// 初始化隐私政策表单配置
PrivacyFormConfig formConfig = new PrivacyFormConfig(userId, code);
// 初始化隐私政策表单
PrivacySDK.initPrivacyForm(this, formConfig);
```