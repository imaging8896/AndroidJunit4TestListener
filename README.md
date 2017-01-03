#Android instrumented test junit4 xml report generator

##Feature
1. Support android.support.test.runner.AndroidJUnitRunner
2. Support Junit4 test framework which android.support.test.runner.AndroidJUnitRunner supports
3. 'Bug' annotation

##How to use
1. Jar generate by comand ./gradlew createJar. Jar in the app/libs/xml-report-formater.jar
2. Copy xml-report-formater.jar to project
3. Add 'instrumentation' tag in AndroidManifest.xml

  ```
  <instrumentation
    android:name="android.support.test.runner.AndroidJUnitRunner"
    android:targetPackage="test.package">
        <meta-data
            android:name="listener"
            android:value="imaging.android.test.JUnitResultFormatterAsRunListener" />
  </instrumentation>
  ```
4. Run instrumented test with command(adb shell am instrument ...) 
5. Output in the /sdcard/report/ (each test class map to one 'className.xml')
6. Pull from device and use 'ant' to integrate test report(task junitreport)
  
##Note
* To inspect some tests as known bug you can mark test as 'Bug' annotation(should use along with ignore), it will count in test report. 

