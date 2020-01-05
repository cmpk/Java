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
  ｜      ├ body.ftl
  ｜      └ mail.properties   //実行環境に合わせて設定
  └ <properties project>
      └ *.jar

```

### mail.properties サンプル
```
username=test01@gmail.com
password=test01
charset=UTF-8
encoding=base64
host=smtp.gmail.com
port=587

from=test02@gmail.com
reply_to=test03@gmail.com
```

## UT について
main パッケージ以外のクラスについて、UTを実装済み  

## 注釈
(*1) テンプレートファイルを SJIS、ソースコードを UTF-8 にした際に、テンプレートに埋め込む値をソースコードにべた書きすると、べた書きした部分だけ文字化けが発生するため。  
（べた書き部分の文字コードをきちんと設定してあげればいけるとは思う）  
