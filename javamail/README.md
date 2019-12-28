# Apache FreeMarker を使用したメール作成とメール送信方法調査  

## 目的
* [Apache FreeMarker](https://freemarker.apache.org/) の使用方法を調査する  
* [Java Mail](https://javaee.github.io/javamail/) を使用したメール送信方法を調査する  

## 前提条件

* ソースコードと設定ファイルの文字コードは SJIS（MS932） とする。(*1)  
* GMail を利用する。
  * 対象Googleアカウントにて安全性の低いアプリへのアクセスを有効にしていること  
    [Google アカウントを管理] > [セキュリティ] > [安全性の低いアプリのアクセス]

## フォルダ構成
```
<root>
  ├ <this project>
  ｜  └ conf/
  ｜      └ *.ftl
  └ <properties project>
      └ *.jar

```

## 注釈
(*1) テンプレートファイルを SJIS、ソースコードを UTF-8 にした際に、テンプレートに埋め込む値をソースコードにべた書きすると、べた書きした部分だけ文字化けが発生。
