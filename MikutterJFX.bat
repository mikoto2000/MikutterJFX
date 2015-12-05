set BATCH_PATH=%~dp0
set CLASSPATH="%BATCH_PATH%build\libs\MikutterJFX.jar;%BATCH_PATH%dependencies\jruby-complete-9.0.3.0.jar;%BATCH_PATH%dependencies\slf4j-api-1.7.12.jar;%BATCH_PATH%dependencies\logback-core-1.1.3.jar;%BATCH_PATH%dependencies\logback-classic-1.1.3.jar;%BATCH_PATH%src\main\resource"

cd %BATCH_PATH%
java -cp %CLASSPATH% jp.dip.oyasirazu.mikutterj.MikutterJFX
