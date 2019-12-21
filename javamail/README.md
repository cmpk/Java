# Apache FreeMarker の使用方法調査  

## 目的
* [Apache FreeMarker](https://freemarker.apache.org/) の使用方法を調査する  
* JavaMail を使用したメール送信方法を調査する  

## 前提条件
ソースコードと設定ファイルの文字コードは SJIS（MS932） とする。(*1)  

## フォルダ構成
```
<project root>
  ├ common
  └ main
```

## 実行時フォルダ構成
```
<root>
  ├ conf/
  ｜  └ *.ftl
  └ libs
      └ *.jar

```

## 注釈
(*1) テンプレートファイルを SJIS、ソースコードを UTF-8 にした際に、テンプレートに埋め込む値をソースコードにべた書きすると、べた書きした部分だけ文字化けが発生。
