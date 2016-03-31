## ButterKnife 使用说明 ##
[ButterKnife - GitHub][1]
> ButterKnife 项目项目简介:
> ButterKnife 是大牛 [Jake Wharton](https://github.com/JakeWharton) 在 GitHub 上开源的一个项目，该项目为 Android 提供 View 注入功能。
> 官方简介：
> Field and method binding for Android views which uses annotation processing to generate boilerplate code for you.
>   
> * Eliminate findViewById calls by using @Bind on fields.
> * Group multiple views in a list or array. Operate on all of them at once with actions, setters, or properties.
> * Eliminate anonymous inner-classes for listeners by annotating methods with @OnClick and others.
> * Eliminate resource lookups by using resource annotations on fields.
>  
> For documentation and additional information see the [website][2].

----------

### 简易集成指南 ###

1. Download  
	方式一 下载 jar 包：  
	[the latest JAR][3]   
	方式二 通过 Maven：  

		<dependency>
		  <groupId>com.jakewharton</groupId>
		  <artifactId>butterknife</artifactId>
		  <version>7.0.1</version>
		</dependency>

	**方式三 Gradle：**  
	compile 'com.jakewharton:butterknife:7.0.1'  
	其他：  
	For the SNAPSHOT version:  
	buildscript {  
	&nbsp;&nbsp;&nbsp;&nbsp;dependencies {  
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'  
	&nbsp;&nbsp;&nbsp;&nbsp;}  
	}  
	  
	apply plugin: 'com.neenbedankt.android-apt'  
	  
	dependencies {  
	&nbsp;&nbsp;&nbsp;&nbsp;compile 'com.jakewharton:butterknife:8.0.0-SNAPSHOT'  
	&nbsp;&nbsp;&nbsp;&nbsp;apt 'com.jakewharton:butterknife-compiler:8.0.0-SNAPSHOT'  
	}  
2. 在 Activity 的 onCreate() 方法中初始化  
	ButterKnife.bind(this);  
3. Eclipse用户使用示例  

		class ExampleActivity extends Activity {
		  @Bind(R.id.user) EditText username;
		  @Bind(R.id.pass) EditText password;
		
		  @BindString(R.string.login_error)
		  String loginErrorMessage;
		
		  @OnClick(R.id.submit) void submit() {
		    // TODO call server...
		  }
		
		  @Override public void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.simple_activity);
		    ButterKnife.bind(this);
		    // TODO Use fields...
		  }
		}
4. AndroidStudio/IDEA 用户使用示例  
	为 AndroidStudio 集成 [ButterKnife 插件][4]  
	使用示例    
	![img](https://github.com/avast/android-butterknife-zelezny/raw/master/img/zelezny_animated.gif)










[1]: https://github.com/JakeWharton/butterknife "ButterKnife"
[2]: http://jakewharton.github.com/butterknife/ "ButterKnife Doc"
[3]: https://search.maven.org/remote_content?g=com.jakewharton&a=butterknife&v=LATEST "the latest JAR"
[4]: https://github.com/avast/android-butterknife-zelezny