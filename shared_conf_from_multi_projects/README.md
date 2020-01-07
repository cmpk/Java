*準備中*

# 課題
log4j 2 の設定ファイルについて、eclipseから実行する際はクラスパスに格納フォルダを指定することで実行できた。  

＜build.gradle＞  
```
dependencies {
   ...
    // 設定ファイルの位置をクラスパスとして追加
    runtime files('../../conf')
   ...
}
```

しかし、jar から実行すると、クラスパスを追加していても設定ファイルを見つけることができなかった。  

＜build.gradle＞
```
jar {
    manifest {
        attributes 'Main-Class': 'pj1.Main'

        // 依存ライブラリをクラスパスに指定
        attributes 'Class-Path': configurations.runtime.collect{it.name.endsWith('jar') ? it.name : '../../' + it.name}.join(' ')
    }
    ...

```
→ ダメ

＜実行時クラスパス指定＞
```
java -cp ..\..\conf -jar ...
```
→ ダメ


このため、javaソースコード内で直接ファイルを指定した。  


なお、実行時オプション指定では可能。  
```
java -Dlog4j.configurationFile=..\..\conf\log4j2.xml -jar ...
```
